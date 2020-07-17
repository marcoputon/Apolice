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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
public class ClienteController {

    @Autowired
    private ClienteService cadastroDeCliente;

    @Autowired
    private EnderecoService cadastroDeEndereco;

    @PostMapping("/clientes")
    public Cliente criar (@RequestBody Cliente cliente, @RequestParam String cep) {
        log.info("criar() - Criando cliente: {}", cliente);

        Cliente clienteSalvo = cadastroDeCliente.salvar(cliente);

        RestTemplate restTemplate = new RestTemplate();
        EnderecoDto enderecoDto = restTemplate.getForObject("http://viacep.com.br/ws/" + cep + "/json/", EnderecoDto.class);

        if (enderecoDto != null) {
            Endereco endereco = enderecoDto.toEntity();
            endereco.setCliente(clienteSalvo);
            cadastroDeEndereco.salvar(endereco);
        }

        log.info("criar() - Finalizado criação de cliente: {}", clienteSalvo);
        return clienteSalvo;
    }

    @GetMapping("/clientes")
    public List<Cliente> listar () {
        return cadastroDeCliente.buscarTodos();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> pesquisar(@PathVariable Long id) {
        Optional<Cliente> fabricante = cadastroDeCliente.buscarPeloId(id);
        return ResponseEntity.of(fabricante);
    }

}
