package com.via.agencia_viagens.service;

import com.via.agencia_viagens.model.*;
import com.via.agencia_viagens.model.DTO.CompraDTO;
import com.via.agencia_viagens.repository.ClienteRepository;
import com.via.agencia_viagens.repository.CompraRepository;
import com.via.agencia_viagens.repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CompraService {

    @Autowired
    private TransporteRepository transporteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CompraRepository compraRepository;

    public void executarCommand(Long compraId, CompraCommand command) {
        Compra compra = buscarPorId(compraId);
        command.executar();
        compraRepository.save(compra);
    }

    public Compra criarCompraViaDTO(CompraDTO dto) {
        Transporte ida = transporteRepository.findById(dto.getTransporteIdaId())
                .orElseThrow(() -> new RuntimeException("Transporte de ida não encontrado"));
        if (ida.getQuantidadeLugares() <= 0) throw new RuntimeException("Sem lugares disponíveis para ida");

        Transporte volta = null;
        if (dto.getTransporteVoltaId() != null) {
            volta = transporteRepository.findById(dto.getTransporteVoltaId())
                    .orElseThrow(() -> new RuntimeException("Transporte de volta não encontrado"));
            if (volta.getQuantidadeLugares() <= 0) throw new RuntimeException("Sem lugares disponíveis para volta");
        }

        Compra compra = Compra.builder()
                .cliente(clienteRepository.findById(dto.getClienteId()).orElseThrow())
                .transporteIda(ida)
                .transporteVolta(volta)
                .dataIda(dto.getDataIda())
                .dataVolta(dto.getDataVolta())
                .numeroPassagem(dto.getNumeroPassagem())
                .status(Compra.StatusCompra.AGUARDANDO_PAGAMENTO)
                .hospedagem(dto.getHospedagem())
                .descricao("Passagem de ida" + (volta != null ? " e volta" : ""))
                .possuiGuia(dto.isPossuiGuia())
                .possuiSeguro(dto.isPossuiSeguro())
                .build();

        // Preço base
        BigDecimal preco = new BigDecimal("500.00");

        if (dto.isPossuiGuia()) {
            compra = new GuiaTuristicoDecorator(compra, "GUIA-123").compra;
            preco = preco.add(new BigDecimal("150.00"));
        }

        if (dto.isPossuiSeguro()) {
            compra = new SeguroViagemDecorator(compra).compra;
            preco = preco.add(new BigDecimal("200.00"));
        }

        compra.setPreco(preco);
        compra.setDescricao(compra.getDescricaoDetalhada());

        // Salva a compra
        Compra salva = compraRepository.save(compra);

        // Reduz a quantidade de lugares
        ida.setQuantidadeLugares(ida.getQuantidadeLugares() - 1);
        transporteRepository.save(ida);

        if (volta != null) {
            volta.setQuantidadeLugares(volta.getQuantidadeLugares() - 1);
            transporteRepository.save(volta);
        }

        return salva;
    }


    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id).orElse(null);
    }
}