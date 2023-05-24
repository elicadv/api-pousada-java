package com.apipousada.pousadacompletateste.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.apipousada.pousadacompletateste.model.ReservaModel;

@Repository

public interface ReservaRepository extends JpaRepository <ReservaModel, Long>{

    List<ReservaModel> findByPessoaId(Long pessoaId);

}

