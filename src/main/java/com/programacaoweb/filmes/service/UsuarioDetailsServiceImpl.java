package com.programacaoweb.filmes.service;

import com.programacaoweb.filmes.domain.Usuario;
import com.programacaoweb.filmes.repository.UsuarioRepository;
import com.programacaoweb.filmes.security.UsuarioDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder encoder;

    public UsuarioDetailsServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository= usuarioRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username)
                .map(UsuarioDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não Encontrado"));
    }
}
