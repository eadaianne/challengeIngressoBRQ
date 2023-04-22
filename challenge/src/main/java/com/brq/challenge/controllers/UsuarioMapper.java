package com.brq.challenge.controllers;

import com.brq.challenge.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {
    private UsuarioMapper(){}
    public static List<UsuarioResumo> toDtoResumo(List<Usuario> usuarios) {
        List<UsuarioResumo> usuariosResumo = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioResumo usuarioResumo = new UsuarioResumo();
            usuarioResumo.setId(usuario.getId());
            usuarioResumo.setCpf(usuario.getCpf());
            usuarioResumo.setEmail(usuario.getEmail());
            usuarioResumo.setNome_completo(usuario.getNome_completo());
            usuariosResumo.add(usuarioResumo);
        }
        return usuariosResumo;
    }
}
