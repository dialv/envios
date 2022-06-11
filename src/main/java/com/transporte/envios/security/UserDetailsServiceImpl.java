package com.transporte.envios.security;


import com.transporte.envios.model.Usuario;
import com.transporte.envios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

//sirve para autenticarme contra la base de datos
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);
        return new AppUserDetailsModel(usuario);
    }
}
