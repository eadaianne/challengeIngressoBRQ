package com.brq.challenge.usuario;
import java.time.LocalDateTime;
import java.util.Date;

public record DadosCadastroUsuario(String cpf, String email, String nome_completo, String senha, String apelido,
                                   String data_nascimento, String celular, LocalDateTime data_cadastro, String genero, Date data_atualizacao,
                                   Endereco endereco) {

}