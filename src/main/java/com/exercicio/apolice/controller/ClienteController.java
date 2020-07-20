package com.exercicio.apolice.controller;

import com.exercicio.apolice.dto.EnderecoDto;
import com.exercicio.apolice.entity.Cliente;
import com.exercicio.apolice.entity.Endereco;
import com.exercicio.apolice.service.ClienteService;
import com.exercicio.apolice.service.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
public class ClienteController {

    @Autowired
    private ClienteService cadastroDeCliente;

    @Autowired
    private EnderecoService cadastroDeEndereco;

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> criar (@RequestBody Cliente cliente, @RequestParam String cep) {
        log.info("criar() - Criando cliente: {}", cliente);

        Cliente clienteSalvo = cadastroDeCliente.cadastrar(cliente, cep);

        log.info("criar() - Finalizado criação de cliente: {}", clienteSalvo);
        return ResponseEntity.ok(clienteSalvo);
    }

    @GetMapping("/clientes")
    public List<Cliente> listar () {
        return cadastroDeCliente.buscarTodos();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> pesquisar(@PathVariable Long id) {
        Optional<Cliente> cliente = cadastroDeCliente.buscarPeloId(id);
        return ResponseEntity.of(cliente);
    }

}
