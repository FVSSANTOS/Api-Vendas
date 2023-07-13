package com.fvss.vendas.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfromacaoItemPedidoDTO {
    private String descricaoProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
