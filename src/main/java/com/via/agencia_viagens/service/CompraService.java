package com.via.agencia_viagens.service;

import com.via.agencia_viagens.model.*;
import com.via.agencia_viagens.repository.CompraRepository;
import com.via.agencia_viagens.repository.TransporteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final TransporteRepository transporteRepository;
    private final ClienteService clienteService;

    public CompraService(CompraRepository compraRepository,
                         TransporteRepository transporteRepository,
                         ClienteService clienteService) {
        this.compraRepository = compraRepository;
        this.transporteRepository = transporteRepository;
        this.clienteService = clienteService;
    }

    public List<Transporte> listarTodosTransportes() {
        return transporteRepository.findAll();
    }

    public Compra criarCompra(Long clienteId, Long transporteIdaId, Long transporteVoltaId,
                              LocalDate dataIda, LocalDate dataVolta,
                              String hospedagem, boolean possuiSeguro, boolean possuiGuia,
                              String destinoNacional, String destinoInternacional)
    {

        Cliente cliente = clienteService.procurarId(clienteId);
        Transporte transporteIda = transporteRepository.findById(transporteIdaId)
                .orElseThrow(() -> new RuntimeException("Transporte de ida não encontrado"));

        Transporte transporteVolta = transporteVoltaId != null ?
                transporteRepository.findById(transporteVoltaId).orElse(null) : null;

        // Cria a compra base
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setTransporteIda(transporteIda);
        compra.setTransporteVolta(transporteVolta);
        compra.setDataIda(dataIda);
        compra.setDataVolta(dataVolta);
        compra.setHospedagem(hospedagem);
        compra.setStatus(Compra.StatusCompra.AGUARDANDO_PAGAMENTO);

        // Aplica estratégia de preço
        BigDecimal precoBase = new BigDecimal("500.00");
        CalculoPrecoStrategy strategy = CalculoPrecoStrategyFactory.criarEstrategia(dataIda);
        BigDecimal preco = strategy.calcularPreco(precoBase);

        // Aplica serviços adicionais
        if (possuiGuia) {
            compra.setNumeroGuia("GUIA-" + UUID.randomUUID().toString().substring(0, 8));
            preco = preco.add(new BigDecimal("150.00"));
        }

        if (possuiSeguro) {
            compra.setSeguroAtivo(true);
            preco = preco.add(new BigDecimal("200.00"));
        }

        compra.setPreco(preco);

        StringBuilder descricao = new StringBuilder();

        if (destinoNacional != null && !destinoNacional.isBlank()) {
            descricao.append("Destino Nacional: ").append(destinoNacional);
        } else if (destinoInternacional != null && !destinoInternacional.isBlank()) {
            descricao.append("Destino Internacional: ").append(destinoInternacional);
        }

        compra.setDescricao(descricao.toString()); // descrição parcial, o restante vem com seguro/guia


        compra.setDescricao(compra.getDescricaoDetalhada());

        return compraRepository.save(compra);
    }

    public List<Compra> listarComprasPorCliente(Long clienteId) {
        return compraRepository.findByClienteId(clienteId);
    }

    public void confirmarCompra(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
        compra.setStatus(Compra.StatusCompra.CONCLUIDA);
        compraRepository.save(compra);
    }

    public void cancelarCompra(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

        if (compra.possuiSeguro()) {
            compra.setStatus(Compra.StatusCompra.CANCELADA);
            compra.setSeguroAtivo(false);
            compraRepository.save(compra);
        } else {
            throw new IllegalStateException("Esta compra não possui seguro e não pode ser cancelada");
        }
    }

    public String obterContatoGuia(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

        return compra.contatoGuia();
    }
}