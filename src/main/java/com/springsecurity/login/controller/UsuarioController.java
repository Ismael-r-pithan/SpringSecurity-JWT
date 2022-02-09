package com.springsecurity.login.controller;


import com.springsecurity.login.model.Usuario;
import com.springsecurity.login.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    @Cacheable(value = "listaDeUsuarios")
    public ResponseEntity<Object> buscarTodos () {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }



    @PostMapping
    @CacheEvict(value = "listaDeUsuarios", allEntries = true)
    public void salvar(@RequestBody Usuario usuario) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String senha = bcrypt.encode(usuario.getSenha());
        usuario.setSenha(senha);
        usuarioRepository.save(usuario);
    }
    


}
