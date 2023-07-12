package com.fvss.vendas.service;

import com.fvss.vendas.domain.entity.Pedido;
import com.fvss.vendas.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
