package com.via.agencia_viagens.repository;

import com.via.agencia_viagens.model.TransporteBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransporteRepository extends JpaRepository<TransporteBase, Long> {
}
