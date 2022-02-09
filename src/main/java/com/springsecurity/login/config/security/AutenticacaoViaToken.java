package com.springsecurity.login.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springsecurity.login.model.Usuario;
import com.springsecurity.login.repository.UsuarioRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


public class AutenticacaoViaToken extends OncePerRequestFilter {

    private UsuarioRepository usuarioRepository;

    private TokenService tokenService;

    public AutenticacaoViaToken(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);

        boolean valido = tokenService.isTokenValid(token);

        if (valido) {
            autenticarCliente(token);
        }

        filterChain.doFilter(request, response);
            
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
    
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario ,null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
    }
    
}
