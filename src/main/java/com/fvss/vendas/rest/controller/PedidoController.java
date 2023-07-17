package com.fvss.vendas.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fvss.vendas.domain.entity.ItemPedido;
import com.fvss.vendas.domain.entity.Pedido;
import com.fvss.vendas.domain.enums.StatusPedido;
import com.fvss.vendas.rest.dto.AtualizacaoStatusDTO;
import com.fvss.vendas.rest.dto.InformacoesPedidoDTO;
import com.fvss.vendas.rest.dto.InfromacaoItemPedidoDTO;
import com.fvss.vendas.rest.dto.PedidoDTO;
import com.fvss.vendas.service.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    private PedidoService service;

    public PedidoController(PedidoService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service.obterPedidoCompleto(id)
                        .map(p -> converter(p))
                        .orElseThrow(() -> 
                                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizarStatus(id, StatusPedido.valueOf(novoStatus));
    }
    
    private InformacoesPedidoDTO converter(Pedido pedido){
       return InformacoesPedidoDTO
                    .builder()
                    .codigo(pedido.getId())
                    .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .cpf(pedido.getCliente().getCpf())
                    .nomeCliente(pedido.getCliente().getNome())
                    .total(pedido.getTotal())
                    .status(pedido.getStatus().name())
                    .itens(converter(pedido.getItens()))
                    .build();
    }

    private List<InfromacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(itens.isEmpty()){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InfromacaoItemPedidoDTO
                    .builder()
                    .descricaoProduto(item.getProduto().getDescricao())
                    .precoUnitario(item.getProduto().getPreco())
                    .quantidade(item.getQuantidade())
                    .build()
        ).collect(Collectors.toList());
    }
}
