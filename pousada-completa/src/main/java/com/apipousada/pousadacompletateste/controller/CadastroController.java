package com.apipousada.pousadacompletateste.controller;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{login}")
    public CadastroModel buscarPorLogin(@PathVariable String login) {
    return cadastroService.buscarPorLogin(login);
    }

    @GetMapping("/usuario/{id}")
    public CadastroModel obterPorId(@PathVariable Long id) {
    return cadastroService.obterPorId(id);
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

    @PostMapping("/cadastro")
    public ResponseEntity<Object> criar(@RequestBody CadastroModel cadastro) {
        try {
            CadastroModel novoCadastro = cadastroService.salvar(cadastro);
            return ResponseEntity.ok(novoCadastro);
        } catch (InvalidParameterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


   @DeleteMapping("/cadastro/{id}")
   public ResponseEntity<Object> deletar(@PathVariable Long id) {
    cadastroService.deletarCadastro(id);
    return ResponseEntity.ok().build();
}


   @PutMapping("/cadastro/{id}")
    public CadastroModel updateCadastro(@PathVariable Long id, @RequestBody CadastroModel cadastroModel) {
    return cadastroService.updateCadastro(id, cadastroModel);
}
}
