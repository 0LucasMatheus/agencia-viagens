package com.via.agencia_viagens.service;

import com.via.agencia_viagens.model.Transporte;
import com.via.agencia_viagens.repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository transporteRepository;

    public Transporte salvarTransporte(Transporte transporte) {
        return transporteRepository.save(transporte);
    }

    public List<Transporte> listarTodos() {
        return transporteRepository.findAll();
    }
}
