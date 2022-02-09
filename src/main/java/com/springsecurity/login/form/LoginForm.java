package com.springsecurity.login.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginForm {
    
    private String nome;

    private String senha;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(nome, senha);
    }

}
