package com.brq.challenge.controllers;

import com.brq.challenge.usuario.DadosCadastroUsuario;
import com.brq.challenge.usuario.Usuario;
import com.brq.challenge.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/challengebrq/v1/usuarios")
public class UsuarioController {

        @Autowired
        private UsuarioRepository UsuarioRepository;



        @PostMapping
        public void CadastrarUsuario(@RequestBody DadosCadastroUsuario dados) {
                UsuarioRepository.save(new Usuario(dados));

        }

        @GetMapping
        public List<UsuarioResumo> listar() {
                List<Usuario> usuarios = UsuarioRepository.findAll();
                return UsuarioMapper.toDtoResumo(usuarios);
        }

        @GetMapping("/{id}")
        public Usuario detalharUsuario(@PathVariable String id) {
                return UsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não existe!"));
        }
}