package com.via.agencia_viagens.service;

import com.via.agencia_viagens.model.*;
import com.via.agencia_viagens.repository.TransporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransporteService {

    private final TransporteRepository transporteRepository;

    public TransporteService(TransporteRepository transporteRepository) {
        this.transporteRepository = transporteRepository;
    }

    public Transporte salvarTransporte(Transporte transporte) {
        return transporteRepository.save(transporte);
    }

    public List<Transporte> listarTodos() {
        return transporteRepository.findAll();
    }

    public void deletarTransporte(Long id) {
        transporteRepository.deleteById(id);
    }

    public Transporte buscarPorId(Long id) {
        return transporteRepository.findById(id).orElse(null);
    }

    public Transporte criarOnibus(String descricao, int quantidadeLugares) {
        Onibus onibus = new Onibus();
        onibus.setDescricao(descricao);
        onibus.setQuantidadeLugares(quantidadeLugares);
        return transporteRepository.save(onibus);
    }

    public Transporte criarAviao(String descricao, int quantidadeLugares) {
        Aviao aviao = new Aviao();
        aviao.setDescricao(descricao);
        aviao.setQuantidadeLugares(quantidadeLugares);
        return transporteRepository.save(aviao);
    }
}