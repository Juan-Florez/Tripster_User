package com.unal.tripster_user_ms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.*;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        validarUsuario(usuario);
        String email = usuario.getEmail();
        if (usuarioRepository.existsByEmail(email)) {
            throw new DuplicateKeyException("El correo electrónico ya está en uso.");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    //@Override
    //public Usuario obtenerUsuarioPorNombre(String nombre) {
    //  return usuarioRepository.findByNombre(nombre);
    //}

    @Override
    public Usuario obtenerUsuarioPorEmail(String id) {
        return usuarioRepository.findByEmail(id);
    }

    @Override
    public Usuario actualizarUsuario(String email, Usuario updateUsuario) {
        Usuario existingUser = usuarioRepository.findByEmail(email);
        String email2 = updateUsuario.getEmail();
        if (existingUser == null) {
            throw new NoSuchElementException("Usuario no encontrado");
        }
        else if (usuarioRepository.existsByEmail(email2) && !email2.equals(email)) {
            throw new DuplicateKeyException("El correo electrónico ya está en uso.");
        }else if (!usuarioRepository.existsByEmail(email2) &&!email2.equals(email)){
            usuarioRepository.deleteById(email);
        }

        // Realiza la actualización

        return usuarioRepository.save(updateUsuario);
    }

    @Override
    public void borrarUsuario(String id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NoSuchElementException("Usuario no encontrado");
        }
    }

    private void validarUsuario(Usuario usuario) {
        // Verificar que el email sea un email válido (puedes usar expresiones regulares)
        if (!isValidEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }

        // Verificar que el rol sea user o admin
        if (!isValidRole(usuario.getRol())) {
            throw new IllegalArgumentException("El rol no es válido.");
        }

        // Verificar que los campos no estén vacíos
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty() ||
                usuario.getClave() == null || usuario.getClave().isEmpty() ||
                usuario.getBirthday() == null || usuario.getBirthday().isEmpty() ||
                usuario.getEmail() == null || usuario.getEmail().isEmpty()
        ) {
            throw new IllegalArgumentException("No se completaron los campos obligatorios");
        }

        // Verificar límites de caracteres (puedes ajustar estos límites según tus necesidades)
        if (usuario.getNombre().length() > 20 ||
                usuario.getApellido().length() > 20 ||
                usuario.getDireccion().length() > 100 ||
                usuario.getTelefono().length() > 15 ) {
            throw new IllegalArgumentException("Se superó el límite de caracteres en uno o más campos.");
        }
    }

    private boolean isValidEmail(String email) {
        // Expresión regular para validar direcciones de correo electrónico
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        // Verificar si la cadena coincide con la expresión regular
        return matcher.matches();
    }

    private boolean isValidRole(String role) {
        return "user".equals(role) || "admin".equals(role);
    }
}
