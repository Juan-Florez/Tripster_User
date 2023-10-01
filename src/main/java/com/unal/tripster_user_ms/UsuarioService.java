package com.unal.tripster_user_ms;

import java.util.List;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> obtenerUsuarios();
    Usuario obtenerUsuarioPorNombre(String nombre);
    Usuario actualizarUsuario(String email, Usuario usuario);
    void borrarUsuario(String id);
}
