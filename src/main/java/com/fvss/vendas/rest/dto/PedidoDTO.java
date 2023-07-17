package com.fvss.vendas.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fvss.vendas.validation.NotEmptyList;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * {
    "cliente" : 1,
    "total" : 100,
    "itens" : [
        {
            "produto" : 1,
            "quantidade" : 10
        }
    ]
}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Informe o código do cliente.")
    private Integer cliente;
    @NotNull(message = "Campo total do pedido é obrigratório.")
    private BigDecimal total;
    @NotEmptyList(message = "Pedidio não pode ser realizado sem itens.")
    private List<ItemPedidoDTO> itens;
}
