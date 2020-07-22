package com.exercicio.apolice.service;

import com.exercicio.apolice.dto.EnderecoDto;
import com.exercicio.apolice.entity.Cliente;
import com.exercicio.apolice.entity.Endereco;
import com.exercicio.apolice.exception.ClienteInexistenteException;
import com.exercicio.apolice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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


    public Cliente buscarPeloId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow( () -> new ClienteInexistenteException("O cliente de id " + id + "não foi encontrado"));
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
