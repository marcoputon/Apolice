package com.exercicio.apolice.service;

import com.exercicio.apolice.dto.EnderecoDto;
import com.exercicio.apolice.entity.Cliente;
import com.exercicio.apolice.entity.Endereco;
import com.exercicio.apolice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPeloId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente cadastrar (Cliente cliente, String cep) {
        Cliente clienteSalvo = this.salvar(cliente);
        EnderecoDto enderecoDto = enderecoService.consultaCep(cep);

        if (enderecoDto != null) {
            Endereco endereco = enderecoDto.paraEntidade();
            endereco.setCliente(clienteSalvo);
            Endereco enderecoSalvo = enderecoService.salvar(endereco);

            List<Endereco> enderecos = new ArrayList<>();
            enderecos.add(enderecoSalvo);
            clienteSalvo.setEnderecos(enderecos);
        }
        return clienteSalvo;
    }
}
