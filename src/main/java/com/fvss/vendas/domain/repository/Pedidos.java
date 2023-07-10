package com.fvss.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fvss.vendas.domain.entity.Cliente;
import com.fvss.vendas.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{
    
    List<Pedido>findByCliente(Cliente cliente);
    
}
