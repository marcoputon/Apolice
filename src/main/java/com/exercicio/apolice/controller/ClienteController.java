package com.exercicio.apolice.controller;

import com.exercicio.apolice.entity.Cliente;
import com.exercicio.apolice.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;


    @PostMapping("/clientes")
    public ResponseEntity<Cliente> criar (@RequestBody Cliente cliente, @RequestParam String cep) {
        log.info("criar() - Criando cliente: {}", cliente);

        Cliente clienteSalvo = clienteService.cadastrar(cliente, cep);

        log.info("criar() - Finalizado criação de cliente: {}", clienteSalvo);
        return ResponseEntity.ok(clienteSalvo);
    }

    
    @GetMapping("/clientes")
    public List<Cliente> listar () {
        return clienteService.buscarTodos();
    }


    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> pesquisar(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPeloId(id);
        return ResponseEntity.ok(cliente);
    }

}
