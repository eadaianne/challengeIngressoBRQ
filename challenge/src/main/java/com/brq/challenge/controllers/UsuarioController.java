package com.brq.challenge.controllers;

import com.brq.challenge.usuario.DadosCadastroUsuario;
import com.brq.challenge.usuario.Usuario;
import com.brq.challenge.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/challengebrq/v1/usuarios")
public class UsuarioController {

        @Autowired
        private final UsuarioRepository repository;
        public UsuarioController(UsuarioRepository repository){
                this.repository = repository;
        }

        @PostMapping
        public void CadastrarUsuario(@RequestBody DadosCadastroUsuario dados) {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dataNasc = LocalDate.parse(dados.data_nascimento(), formato);
                LocalDate dataAtual = LocalDate.now();
                if(dataNasc.isAfter(dataAtual)){
                        throw new RuntimeException("Data inválida.");
                }
                repository.save(new Usuario(dados));
        }

        @GetMapping
        public List<UsuarioResumo> listar() {
                List<Usuario> usuarios = repository.findAll();
                return UsuarioMapper.toDtoResumo(usuarios);
        }

        @GetMapping("/{id}")
        public Usuario detalharUsuario(@PathVariable String id) {
                return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não existe!"));
        }
}