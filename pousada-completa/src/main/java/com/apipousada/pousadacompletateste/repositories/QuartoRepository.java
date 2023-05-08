package com.apipousada.pousadacompletateste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apipousada.pousadacompletateste.model.QuartoModel;

public interface QuartoRepository extends JpaRepository <QuartoModel, Long>{
    
}
