package com.brq.challenge.controllers;

import com.brq.challenge.usuario.DadosCadastroUsuario;
import com.brq.challenge.usuario.Usuario;
import com.brq.challenge.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

                //dados.data_cadastro = LocalDateTime.now();
                //estudar depois

                if(dataNasc.isAfter(dataAtual)){
                        throw new RuntimeException("Data inválida.");
                }
                Usuario user = new Usuario(dados);

                switch(user.getGenero()){
                        case M -> user.setSexo("1");
                        case F -> user.setSexo("2");
                        case B -> user.setSexo("3");
                        case N -> user.setSexo("4");
                }

                user.setDataCadastro(LocalDateTime.now());
                //repository.save(new Usuario(dados));
                repository.save(user);
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