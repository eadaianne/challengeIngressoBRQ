package com.brq.challenge.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

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

//    @Enumerated(EnumType.STRING)
//    private Genero genero;

//    @Enumerated(EnumType.ORDINAL)
    private String genero;

    public LocalDateTime getData_cadastro() {
        return data_cadastro;
    }

    public void setDataCadastro(LocalDateTime data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    //@CreationTimestamp
    private LocalDateTime data_cadastro;

    public LocalDateTime getData_atualizacao() {
        return data_atualizacao;
    }

    public void setDataAtualizacao(LocalDateTime data_atualizacao) {
        this.data_atualizacao = data_atualizacao;
    }
    //@UpdateTimestamp
    private LocalDateTime data_atualizacao;

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
        this.data_cadastro = LocalDateTime.now();
        this.endereco = new Endereco(dados.endereco());
    }

    public void setGenero(String value) {
        this.genero = value;
    }
}
