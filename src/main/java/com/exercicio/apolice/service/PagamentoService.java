package com.exercicio.apolice.service;

import com.exercicio.apolice.entity.Cliente;
import com.exercicio.apolice.entity.Pagamento;
import com.exercicio.apolice.repository.ClienteRepository;
import com.exercicio.apolice.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;


    @Transactional
    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }
}
