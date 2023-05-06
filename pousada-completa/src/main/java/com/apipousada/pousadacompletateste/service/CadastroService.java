package com.apipousada.pousadacompletateste.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apipousada.pousadacompletateste.model.CadastroModel;
import com.apipousada.pousadacompletateste.model.InvalidLoginException;
import com.apipousada.pousadacompletateste.repositories.CadastroRepository;
import com.apipousada.pousadacompletateste.util.ValidadorCPF;
import com.apipousada.pousadacompletateste.util.ValidadorTelefone;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class CadastroService {

List<CadastroModel> cadastros = new ArrayList<>(); // declaração da variável

    
    @Autowired
    private CadastroRepository cadastroRepository;

    /*public CadastroModel salvar(CadastroModel cadastro) {
        return cadastroRepository.save(cadastro);
    }*/
    
     public CadastroModel salvar(CadastroModel cadastro) throws InvalidParameterException {

        String cpf = cadastro.getCpf();

        // Verifica se já existe um registro com o mesmo nome, CPF e telefone
        Optional<CadastroModel> cadastroExistente = cadastroRepository.findByNomeAndCpfAndTelefone(cadastro.getNome(), cadastro.getCpf(), cadastro.getTelefone());
        if (cadastroExistente.isPresent()) {
            throw new InvalidParameterException("Já existe um cadastro com o mesmo nome, CPF e telefone.");
        }

        // Verifica se já existe um registro com o mesmo CPF.
        Optional<CadastroModel> cpfExistente = cadastroRepository.findByCpf(cpf);
        if(cpfExistente.isPresent()) {
          throw new InvalidParameterException("Já existe um cadastro com o mesmo CPF.");
        }

        // Valida o CPF.
        if (!ValidadorCPF.validar(cadastro.getCpf())) {
            throw new InvalidParameterException("CPF inválido");
        }

        // Valida o telefone.
        if (!ValidadorTelefone.validar(cadastro.getTelefone())) {
            throw new InvalidParameterException("Telefone inválido");
        }

        // Verificar se o login já está em uso
        Optional<CadastroModel> loginExistente = cadastroRepository.findByLogin(cadastro.getLogin());
        if (loginExistente.isPresent()) {
        throw new InvalidParameterException("O login já está em uso.");
        }


        // Validar a senha (por exemplo, verificar se tem pelo menos 8 caracteres)
        String senha = cadastro.getSenha();
        if (senha == null || senha.length() < 8) {
        throw new InvalidParameterException("A senha deve ter pelo menos 8 caracteres.");
        }

        // Criptografa a senha antes de salvar o usuário
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(cadastro.getSenha());
        cadastro.setSenha(senhaCriptografada);
        
        return cadastroRepository.save(cadastro);
    }

    public List<CadastroModel> listarPessoas() {
        return cadastroRepository.findAll();
    }

    public CadastroModel getCadastroById(Long id) {
        return null;
    }

    /*public boolean deleteCadastro(Long id) {
        cadastroRepository.deleteById(id);
    }*/


    public boolean deleteCadastro(Long id) {
        Optional<CadastroModel> cadastroOptional = cadastroRepository.findById(id);
    
        if (cadastroOptional.isPresent()) {
            cadastroRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public CadastroModel updateCadastro(Long id, CadastroModel cadastroModel) {
        Optional<CadastroModel> optionalCadastro = cadastroRepository.findById(id);
        if (optionalCadastro.isPresent()) {
            CadastroModel cadastroExistente = optionalCadastro.get();
            cadastroExistente.setNome(cadastroModel.getNome());
            cadastroExistente.setCpf(cadastroModel.getCpf());
            cadastroExistente.setTelefone(cadastroModel.getTelefone());
            cadastroExistente.setEndereco(cadastroModel.getEndereco());
            return cadastroRepository.save(cadastroExistente);
        } else {
            return null;
        }
    
}

    public String login(CadastroModel loginRequest) throws InvalidLoginException{
        String username = loginRequest.getLogin();
        String password = loginRequest.getSenha();

        Optional<CadastroModel> cadastro = cadastroRepository.findByLogin(username);
        if (!cadastro.isPresent()) {
            throw new InvalidLoginException("Login ou senha inválidos");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, cadastro.get().getSenha())) {
            throw new InvalidLoginException("Login ou senha inválidos");
        }
        // gera um token de autenticação (exemplo)
        String token = UUID.randomUUID().toString();
        return token;

    }

    /*public CadastroModel obterPorId(Long id) {
        return null;
    }*/
    public CadastroModel obterPorId(Long id) {
        Optional<CadastroModel> usuarioOptional = cadastroRepository.findById(id);
        if(usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        } else {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
    }

    /*public CadastroModel buscarPorLogin(String login) {
        return null;
    }*/
    /*public CadastroModel buscarPorLogin(String login) {
        for (CadastroModel cadastro : cadastros) {
            if (cadastro.getLogin().equals(login)) {
                return cadastro;
            }
        }
        return null; // retorna null se o login não for encontrado
    }*/
    public CadastroModel buscarPorLogin(String login) {
        Optional<CadastroModel> usuarioOptional = cadastroRepository.findByLogin(login);
        if(usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        } else {
            throw new RuntimeException("Usuário não encontrado com login: " + login);
        }
    }

}


