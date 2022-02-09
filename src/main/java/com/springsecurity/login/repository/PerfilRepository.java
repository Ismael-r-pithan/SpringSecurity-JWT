package com.springsecurity.login.repository;

import com.springsecurity.login.model.Perfil;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{
    
}
