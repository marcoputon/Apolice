package com.exercicio.apolice.service;

import com.exercicio.apolice.dto.EnderecoDto;
import com.exercicio.apolice.entity.Endereco;
import com.exercicio.apolice.entity.Parcela;
import com.exercicio.apolice.repository.EnderecoRepository;
import com.exercicio.apolice.repository.ParcelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class ParcelaService {
    @Autowired
    private ParcelaRepository parcelaRepository;

    @Transactional
    public Parcela salvar(Parcela parcela) {
        return parcelaRepository.save(parcela);
    }
}
