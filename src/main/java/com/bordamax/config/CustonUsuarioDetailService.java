package com.bordamax.config;

import com.bordamax.entity.Usuario;
import com.bordamax.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by fire on 03/04/18.
 */

@Component
public class CustonUsuarioDetailService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustonUsuarioDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = Optional.ofNullable(usuarioRepository.findByIdentificacao(username)).orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado!"));
        List<GrantedAuthority> listAdmin = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "USER");
        return new org.springframework.security.core.userdetails.User(user.getIdentificacao(),
                user.getSenha(),user.getAdmin() ? listAdmin : listAdmin);
    }
}
