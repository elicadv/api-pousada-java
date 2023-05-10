package com.apipousada.pousadacompletateste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apipousada.pousadacompletateste.model.ReservaModel;

public interface ReservaRepository extends JpaRepository <ReservaModel, Long>{
    
}

