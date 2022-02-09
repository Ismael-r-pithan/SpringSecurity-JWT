package com.springsecurity.login.controller;


import com.springsecurity.login.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public ResponseEntity<Object> buscarTodos () {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }


}
