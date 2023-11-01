package com.unal.tripster_user_ms;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findByNombre(String nombre);

    boolean existsByEmail(String email);

    Usuario findByEmail(String email);
}

