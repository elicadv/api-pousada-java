package com.apipousada.pousadacompletateste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apipousada.pousadacompletateste.model.QuartoModel;
import com.apipousada.pousadacompletateste.service.QuartoService;


@RestController
@RequestMapping("/pousada/quarto")
public class QuartoController {

    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public List<QuartoModel> listar() {
        return quartoService.listarQuarto();
    }

    @PostMapping
    public ResponseEntity<QuartoModel> criar(@RequestBody QuartoModel cadastroQuarto) {
        QuartoModel novoQuarto = quartoService.salvar(cadastroQuarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoQuarto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuarto(@PathVariable Long id) {
        boolean isDeleted = quartoService.deleteQuarto(id);

        if (isDeleted) {
            return ResponseEntity.ok("Quarto deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar quarto.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuartoModel> updateQuarto(@PathVariable Long id, @RequestBody QuartoModel quartoModel) {
        QuartoModel quartoAtualizado = quartoService.updateQuarto(id, quartoModel);
        if (quartoAtualizado != null) {
            return ResponseEntity.ok(quartoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}