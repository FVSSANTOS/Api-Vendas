package com.fvss.vendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fvss.vendas.domain.entity.UserModel;
import com.fvss.vendas.domain.repository.Users;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    Users users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserModel user = users.findByUserName(username).orElseThrow(() -> 
       new UsernameNotFoundException("Usuário não encontrado"));
       return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }
    
}
