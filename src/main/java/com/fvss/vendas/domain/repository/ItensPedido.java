package com.fvss.vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fvss.vendas.domain.entity.ItemPedido;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer>{
    
}
