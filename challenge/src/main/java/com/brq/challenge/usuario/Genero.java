package com.brq.challenge.usuario;

public enum Genero {
    M("1"), //MASCULINO//
    F("2"), //FEMININO//
    B("3"), //NÃO-BINÁRIO//
    N("4") //PREFIRO NÃO INFORMAR//
    ;

    private final String numero;

    Genero(String numero) {
        this.numero = numero;
    }

    public String getNumero(){
        return numero;
    }
}
