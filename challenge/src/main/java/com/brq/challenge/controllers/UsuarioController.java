package com.brq.challenge.controllers;

import com.brq.challenge.usuario.DadosCadastroUsuario;
import com.brq.challenge.usuario.Usuario;
import com.brq.challenge.usuario.UsuarioRepository;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/challengebrq/v1/usuarios")
public class UsuarioController {

        @Autowired
        private final UsuarioRepository repository;
        public UsuarioController(UsuarioRepository repository){
                this.repository = repository;
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public Usuario CadastrarUsuario(@RequestBody DadosCadastroUsuario dados) {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dataNasc = LocalDate.parse(dados.data_nascimento(), formato);
                LocalDate dataAtual = LocalDate.now();

                //dados.data_cadastro = LocalDateTime.now();
                //estudar depois

                if(dataNasc.isAfter(dataAtual)){
                        throw new RuntimeException("Data inválida.");
                }
                Usuario user = new Usuario(dados);

                switch(user.getGenero().toUpperCase()){
                        case "M" -> user.setGenero("1");
                        case "F" -> user.setGenero("2");
                        case "B" -> user.setGenero("3");
                        case "N" -> user.setGenero("4");
                }

                user.setDataCadastro(LocalDateTime.now());
                //repository.save(new Usuario(dados));
                repository.save(user);
                return user;
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

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deletar (@PathVariable String id){
                var user = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário inexistente."));
                repository.deleteById(id);
        }

        @PutMapping ("/{id}/senhas")
        public void alterarSenha (@PathVariable String id, @RequestBody String senha_atual){
                var user = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário inexistente."));

        }

        @PatchMapping ("/{id}")
        @ResponseBody
        public Usuario atualizar (@PathVariable String id, @RequestBody Map<String, Object> dadosAtualizacao){
                Usuario usuarioAtualizacao = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não existe."));

                var dataNascAtt = dadosAtualizacao.get("data_nascimento");

                if(dataNascAtt != null){
                        LocalDate dataAtual = LocalDate.now();
                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate dataNasc = LocalDate.parse(dataNascAtt.toString(), formato);
                        if(dataNasc.isAfter(dataAtual)){
                                throw new RuntimeException("Data inválida.");
                        }
                }

                var genero = dadosAtualizacao.get("genero");

                if(genero != null) {
                        switch (genero.toString().toUpperCase()) {
                                case "M" -> dadosAtualizacao.put("genero", "1");
                                case "F" -> dadosAtualizacao.put("genero", "2");
                                case "B" -> dadosAtualizacao.put("genero", "3");
                                case "N" -> dadosAtualizacao.put("genero", "4");
                        }
                }

                usuarioAtualizacao.setDataAtualizacao(LocalDateTime.now());

                merge(dadosAtualizacao, usuarioAtualizacao);
                repository.save(usuarioAtualizacao);

                return usuarioAtualizacao;
        }

        private void merge (Map<String, Object> dadosAtt, Usuario userAtt){
                dadosAtt.forEach((nomePropriedade, valorPropriedade) -> {
                        Field field = ReflectionUtils.findField(Usuario.class, nomePropriedade);
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, userAtt, valorPropriedade);
                });
        }



}