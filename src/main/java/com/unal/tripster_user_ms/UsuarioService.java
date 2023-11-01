package com.unal.tripster_user_ms;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> obtenerUsuarios();
    //Usuario obtenerUsuarioPorNombre(String nombre);
    Usuario actualizarUsuario(String email, Usuario usuario);
    Usuario obtenerUsuarioPorEmail(String id);
    void borrarUsuario(String id);
}
