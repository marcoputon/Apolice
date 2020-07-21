package com.exercicio.apolice.service;

import com.exercicio.apolice.entity.Pagamento;
import com.exercicio.apolice.entity.Parcela;
import com.exercicio.apolice.exception.PagamentoException;
import com.exercicio.apolice.repository.ParcelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParcelaService {
    @Autowired
    private ParcelaRepository parcelaRepository;

    @Transactional
    public Parcela salvar(Parcela parcela) {
        return parcelaRepository.save(parcela);
    }

    @Transactional
    public Parcela pagar(Long id) {
        Optional<Parcela> parcelaOptional = parcelaRepository.findById(id);
        if (!parcelaOptional.isPresent()) {
            throw new PagamentoException("A parcela com id " + id + " não foi encontrada");
        }

        Parcela parcela = parcelaOptional.get();
        parcela.setPago(true);
        return parcelaRepository.save(parcela);
    }

    public List<Parcela> buscarParcelasVencidas() {
        return new ArrayList<>(parcelaRepository.encontrarParcelasVencidas());
    }
}
