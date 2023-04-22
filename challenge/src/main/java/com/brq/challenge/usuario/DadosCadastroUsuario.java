package com.brq.challenge.usuario;
import java.time.LocalDate;
import java.util.Date;

import static java.time.LocalTime.now;

public record DadosCadastroUsuario(String cpf, String email, String nome_completo, String senha, String apelido,
                                   String data_nascimento, String celular,LocalDate data_cadastro, Genero genero, Date data_atualizacao,
                                   Endereco endereco) {

}