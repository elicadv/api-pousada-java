package com.apipousada.pousadacompletateste.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apipousada.pousadacompletateste.model.QuartoModel;
@Repository
public interface QuartoRepository extends JpaRepository <QuartoModel, Long>{

    //List<QuartoModel> findByDescricao(String descricao);

    @Query("SELECT q FROM QuartoModel q WHERE LOWER(q.descricao) = LOWER(:descricao)")
    List<QuartoModel> findByDescricaoIgnoreCase(@Param("descricao") String descricao);
}   

    

