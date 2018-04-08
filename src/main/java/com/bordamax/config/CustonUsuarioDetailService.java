package com.bordamax.config;

import com.bordamax.entity.Usuario;
import com.bordamax.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by fire on 03/04/18.
 */

@Repository
@Transactional
public class CustonUsuarioDetailService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustonUsuarioDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = Optional.ofNullable(usuarioRepository.findByIdentificacao(username)).orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado!"));
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true, usuario.getAuthorities());
    }
}



