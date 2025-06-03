package com.via.agencia_viagens.service;

import com.via.agencia_viagens.model.*;
import com.via.agencia_viagens.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final TransporteRepository transporteRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public CompraService(CompraRepository compraRepository,
                         TransporteRepository transporteRepository,
                         ClienteRepository clienteRepository) {
        this.compraRepository = compraRepository;
        this.transporteRepository = transporteRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Transporte> listarTodosTransportes() {
        return transporteRepository.findAll();
    }

    public Compra criarCompra(Long clienteId, Long transporteIdaId, Long transporteVoltaId,
                              LocalDate dataIda, LocalDate dataVolta,
                              String hospedagem, boolean possuiSeguro, boolean possuiGuia) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Transporte transporteIda = transporteRepository.findById(transporteIdaId)
                .orElseThrow(() -> new RuntimeException("Transporte de ida não encontrado"));

        Transporte transporteVolta = transporteVoltaId != null ?
                transporteRepository.findById(transporteVoltaId)
                        .orElseThrow(() -> new RuntimeException("Transporte de volta não encontrado")) :
                null;

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setTransporteIda(transporteIda);
        compra.setTransporteVolta(transporteVolta);
        compra.setDataIda(dataIda);
        compra.setDataVolta(dataVolta);
        compra.setStatus(Compra.StatusCompra.AGUARDANDO_PAGAMENTO);
        compra.setHospedagem(hospedagem);
        compra.setPossuiSeguro(possuiSeguro);
        compra.setPossuiGuia(possuiGuia);

        // Cálculo básico do preço (pode ser aprimorado)
        BigDecimal preco = BigDecimal.valueOf(500.00);
        if (possuiGuia) preco = preco.add(BigDecimal.valueOf(150.00));
        if (possuiSeguro) preco = preco.add(BigDecimal.valueOf(200.00));
        compra.setPreco(preco);

        compra.setDescricao(compra.getDescricaoDetalhada());

        return compraRepository.save(compra);
    }

    public List<Compra> listarComprasPorCliente(Long clienteId) {
        return compraRepository.findByClienteId(clienteId);
    }

    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id).orElse(null);
    }
}