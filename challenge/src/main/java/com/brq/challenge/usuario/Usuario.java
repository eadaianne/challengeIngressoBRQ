package com.brq.challenge.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

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
    @CPF
    @NotBlank
    private String cpf;

    @Column(unique = true)
    @NotBlank
    @Length(max=50)
    @Email
    private String email;

    @NotBlank
    @Length(min=2, max=100)
    private String nome_completo;

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @NotBlank
    @Length(max=100)
    private String senha;

    @Length(min=1, max=20)
    private String apelido;

    @NotBlank

    private String data_nascimento;

    @NotBlank
    private String celular;

    @NotBlank
    @Length(max=2)
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
    @NotNull
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
