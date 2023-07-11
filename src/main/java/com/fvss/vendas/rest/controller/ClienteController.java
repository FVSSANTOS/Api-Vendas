package com.fvss.vendas.rest.controller;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fvss.vendas.domain.entity.Cliente;
import com.fvss.vendas.domain.repository.Clientes;

@Controller
@RequestMapping
public class ClienteController {
    
    private Clientes clientes;

    public ClienteController(Clientes clientes){
        this.clientes = clientes;
    }
   
    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);

        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente){
       Cliente clienteSalvo = clientes.save(cliente);
       return ResponseEntity.ok(clienteSalvo);
    }

}
