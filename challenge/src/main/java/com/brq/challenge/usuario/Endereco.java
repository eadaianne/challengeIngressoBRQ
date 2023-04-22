package com.brq.challenge.usuario;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public final class Endereco {
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;

    public Endereco(Endereco endereco) {
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.estado = endereco.estado();
        this.pais = endereco.pais();
        this.cep = endereco.cep();
    }

    public String logradouro() {
        return logradouro;
    }

    public String numero() {
        return numero;
    }

    public String bairro() {
        return bairro;
    }

    public String cidade() {
        return cidade;
    }

    public String estado() {
        return estado;
    }

    public String pais() {
        return pais;
    }

    public String cep() {
        return cep;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Endereco) obj;
        return Objects.equals(this.logradouro, that.logradouro) &&
                Objects.equals(this.numero, that.numero) &&
                Objects.equals(this.bairro, that.bairro) &&
                Objects.equals(this.cidade, that.cidade) &&
                Objects.equals(this.estado, that.estado) &&
                Objects.equals(this.pais, that.pais) &&
                Objects.equals(this.cep, that.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logradouro, numero, bairro, cidade, estado, pais, cep);
    }

    @Override
    public String toString() {
        return "Endereco[" +
                "logradouro=" + logradouro + ", " +
                "numero=" + numero + ", " +
                "bairro=" + bairro + ", " +
                "cidade=" + cidade + ", " +
                "estado=" + estado + ", " +
                "pais=" + pais + ", " +
                "cep=" + cep + ']';
    }

}
