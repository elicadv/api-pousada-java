package com.apipousada.pousadacompletateste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apipousada.pousadacompletateste.model.QuartoModel;
import com.apipousada.pousadacompletateste.repositories.QuartoRepository;


@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    public List<QuartoModel> listarQuarto() {
        return quartoRepository.findAll();
    }

    public QuartoModel salvar(QuartoModel cadastroQuarto) {
        return quartoRepository.save(cadastroQuarto);
    }

    public boolean deleteQuarto(Long id) {
        Optional<QuartoModel> cadastroOptional = quartoRepository.findById(id);

        if (cadastroOptional.isPresent()) {
            quartoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public QuartoModel updateQuarto(Long id, QuartoModel quartoModel) {
        Optional<QuartoModel> optionalQuarto = quartoRepository.findById(id);
        if (optionalQuarto.isPresent()) {
            QuartoModel quartoExistente = optionalQuarto.get();
            quartoExistente.setDescricao(quartoModel.getDescricao());
            quartoExistente.setPreco(quartoModel.getPreco());
            quartoExistente.setDescricaoDetalhada(quartoModel.getDescricaoDetalhada());
            quartoExistente.setQuantidadeDisponivel(quartoModel.getQuantidadeDisponivel());
            quartoExistente.setImagem(quartoModel.getImagem());
            return quartoRepository.save(quartoExistente);
        } else {
            return null;
        }
    }
}
