package com.via.agencia_viagens.service;

import com.via.agencia_viagens.model.TransporteBase;
import com.via.agencia_viagens.repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository transporteRepository;

    public TransporteBase salvarTransporte(TransporteBase transporte) {
        return transporteRepository.save(transporte);
    }

    public List<TransporteBase> listarTodos() {
        return transporteRepository.findAll();
    }
}
