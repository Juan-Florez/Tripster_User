package com.unal.tripster_user_ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;


import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService userService;
    @Autowired
    public UsuarioController(UsuarioService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return userService.crearUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return userService.obtenerUsuarios();
    }

    @GetMapping("/{nombre}")
    public Usuario obtenerUsuarioPorNombre(@PathVariable String nombre) {
        return userService.obtenerUsuarioPorNombre(nombre);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        return userService.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void borrarUsuario(@PathVariable String id) {
        userService.borrarUsuario(id);
    }

    //Manejo de excepciones

    // Manejo de excepciones personalizado para Conflict (409)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateKeyException.class)
    public String handleConflict(Exception ex) {
        return ex.getMessage();
    }

    // Manejo de excepciones personalizado para BadRequest (400)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleBadRequest(Exception ex) {
        return ex.getMessage();
    }

    // Manejo de excepciones personalizado para NotFound (404)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFound(Exception ex) {
        return ex.getMessage();
    }

    // Manejo de excepciones personalizado para InternalServerError (500)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleInternalServerError(Exception ex) {
        return "Se ha producido un error interno en el servidor.";
    }
}

