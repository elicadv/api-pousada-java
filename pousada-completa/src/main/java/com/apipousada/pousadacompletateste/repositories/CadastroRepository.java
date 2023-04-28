package com.apipousada.pousadacompletateste.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apipousada.pousadacompletateste.model.CadastroModel;


public interface CadastroRepository extends JpaRepository <CadastroModel, Long>{

    Optional<CadastroModel> findByNomeAndCpfAndTelefone(String nome, String cpf, String telefone);

    Optional<CadastroModel> findByCpf(String cpf);

    Optional<CadastroModel> findByLogin(String login);

}

