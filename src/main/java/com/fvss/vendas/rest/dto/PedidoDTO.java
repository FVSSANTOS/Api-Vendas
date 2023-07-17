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
    @NotNull(message = "{campo.codigo-cliente.obrigratorio}")
    private Integer cliente;
    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;
    @NotEmptyList(message = "{campo.itens-pedido.obrigatorio}")
    private List<ItemPedidoDTO> itens;
}
