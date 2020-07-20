package com.exercicio.apolice.service;

import com.exercicio.apolice.dto.EnderecoDto;
import com.exercicio.apolice.entity.Cliente;
import com.exercicio.apolice.entity.Endereco;
import com.exercicio.apolice.repository.ClienteRepository;
import com.exercicio.apolice.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public EnderecoDto consultaCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, EnderecoDto.class);
    }
}