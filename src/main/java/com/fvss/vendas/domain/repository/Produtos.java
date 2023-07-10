package com.fvss.vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fvss.vendas.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{
    
}
