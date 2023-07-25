package com.fvss.vendas.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fvss.vendas.domain.entity.UserModel;

@Repository
public interface Users extends JpaRepository<UserModel, Integer>{
    
    Optional<UserModel> findByUserName(String username);
}
