package com.brq.challenge.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "usuario")
@Entity(name = "usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String nome_completo;
    private String senha;
    private String apelido;
    private String data_nascimento;
    private String celular;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private LocalDateTime data_cadastro = LocalDateTime.parse(LocalDateTime.now().toString());
    private Date data_atualizacao;

    @Embedded
    private Endereco endereco;

    public Usuario(DadosCadastroUsuario dados) {
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.nome_completo = dados.nome_completo();
        this.senha = dados.senha();
        this.apelido = dados.apelido();
        this.data_nascimento = dados.data_nascimento();
        this.celular = dados.celular();
        this.genero = dados.genero();
        this.data_cadastro = dados.data_cadastro();
        this.endereco = new Endereco(dados.endereco());
    }
}
