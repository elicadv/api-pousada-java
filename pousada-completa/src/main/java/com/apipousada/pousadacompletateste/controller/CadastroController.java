package com.apipousada.pousadacompletateste.controller;

import java.security.InvalidParameterException;
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

import com.apipousada.pousadacompletateste.model.CadastroModel;
import com.apipousada.pousadacompletateste.model.InvalidLoginException;
import com.apipousada.pousadacompletateste.model.LoginResponse;
import com.apipousada.pousadacompletateste.service.CadastroService;

@RestController
@RequestMapping("/pousada")
public class CadastroController {
    
    @Autowired
    private CadastroService cadastroService;

    @GetMapping
    public List<CadastroModel> listar() {
        return cadastroService.listarPessoas();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody CadastroModel loginRequest) {
    try {
        String token = cadastroService.login(loginRequest);
        return ResponseEntity.ok(new LoginResponse(token));
    } catch (InvalidLoginException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }


    /*@PostMapping
    public CadastroModel criar(@RequestBody CadastroModel cadastro) {
        return cadastroService.salvar(cadastro);
    }*/

    @PostMapping("/cadastro")
    public ResponseEntity<Object> criar(@RequestBody CadastroModel cadastro) {
        try {
            CadastroModel novoCadastro = cadastroService.salvar(cadastro);
            return ResponseEntity.ok(novoCadastro);
        } catch (InvalidParameterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*@DeleteMapping("/{id}")
    public void deleteCadastro(@PathVariable Long id) {
        cadastroService.deleteCadastro(id);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCadastro(@PathVariable Long id) {
    boolean isDeleted = cadastroService.deleteCadastro(id);

    if (isDeleted) {
        return ResponseEntity.ok("Cadastro deletado com sucesso.");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar cadastro.");
    }
}

   @PutMapping("/{id}")
    public CadastroModel updateCadastro(@PathVariable Long id, @RequestBody CadastroModel cadastroModel) {
    return cadastroService.updateCadastro(id, cadastroModel);
}
}
