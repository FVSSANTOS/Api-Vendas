package com.fvss.vendas.service;

import java.util.Optional;

import com.fvss.vendas.domain.entity.Pedido;
import com.fvss.vendas.domain.enums.StatusPedido;
import com.fvss.vendas.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizarStatus(Integer id, StatusPedido statusPedido);
}
