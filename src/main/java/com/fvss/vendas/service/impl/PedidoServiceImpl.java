package com.fvss.vendas.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fvss.vendas.domain.entity.Cliente;
import com.fvss.vendas.domain.entity.ItemPedido;
import com.fvss.vendas.domain.entity.Pedido;
import com.fvss.vendas.domain.entity.Produto;
import com.fvss.vendas.domain.enums.StatusPedido;
import com.fvss.vendas.domain.repository.Clientes;
import com.fvss.vendas.domain.repository.ItensPedido;
import com.fvss.vendas.domain.repository.Pedidos;
import com.fvss.vendas.domain.repository.Produtos;
import com.fvss.vendas.exception.RegraNegocioException;
import com.fvss.vendas.rest.dto.ItemPedidoDTO;
import com.fvss.vendas.rest.dto.PedidoDTO;
import com.fvss.vendas.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{
    

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItensPedido itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        repository.save(pedido);
        List<ItemPedido> itensPedidos = converterItems(pedido,dto.getItens());
        itensPedidoRepository.saveAll(itensPedidos);
        pedido.setItens(itensPedidos);
        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens");
        }

        return itens
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                    .findById(idProduto)
                    .orElseThrow(
                        () -> new RegraNegocioException("Código de produto inválido: "+idProduto
                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }
}
