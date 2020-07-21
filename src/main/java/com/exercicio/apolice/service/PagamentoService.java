package com.exercicio.apolice.service;

import com.exercicio.apolice.entity.Pagamento;
import com.exercicio.apolice.entity.Parcela;
import com.exercicio.apolice.exception.PagamentoException;
import com.exercicio.apolice.repository.PagamentoRepository;
import com.exercicio.apolice.repository.ParcelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ParcelaService parcelaService;


    @Transactional
    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public Parcela pagarParcela(Long idParcela) {
        return parcelaService.pagar(idParcela);
    }

    public Pagamento pagar(Long idPagamento) {
        Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(idPagamento);
        if (!pagamentoOptional.isPresent()) {
            throw new PagamentoException("O pagamento com id " + idPagamento + "não foi encontrado");
        }

        Pagamento pagamento = pagamentoOptional.get();
        List<Parcela> parcelas = pagamento.getParcelas();
        for (Parcela p : parcelas) {
            p.setPago(true);
            parcelaService.pagar(p.getId());
        }

        return pagamento;
    }
}
