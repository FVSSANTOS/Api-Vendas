package com.fvss.vendas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fvss.vendas.domain.entity.Usuario;
import com.fvss.vendas.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;


@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("usuário não encontrado na base de dados!"));
        
       String[] roles = usuario.isAdmin() ? new String[] {"ADMIN","USER"} : new String[] {"USER"};

       return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
