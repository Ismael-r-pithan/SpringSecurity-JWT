package com.springsecurity.login.repository;

import java.util.Optional;

import com.springsecurity.login.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
   Optional<Usuario> findByNome(String nome);
}
