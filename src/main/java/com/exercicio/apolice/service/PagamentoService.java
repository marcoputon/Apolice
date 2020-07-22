package com.exercicio.apolice.service;

import com.exercicio.apolice.dto.ApoliceCadastroDto;
import com.exercicio.apolice.entity.Apolice;
import com.exercicio.apolice.entity.Pagamento;
import com.exercicio.apolice.entity.Parcela;
import com.exercicio.apolice.exception.PagamentoException;
import com.exercicio.apolice.exception.PagamentoInexistenteException;
import com.exercicio.apolice.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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


    public Pagamento buscarPagamento(Long idPagamento) {
        final String mensagem = "O pagamento de id " + idPagamento + " não foi encontrado";
        return pagamentoRepository.findById(idPagamento)
                .orElseThrow( () -> new PagamentoInexistenteException(mensagem));
    }


    public Parcela pagarParcela(Long idParcela) {
        Parcela parcela = parcelaService.pagar(idParcela);
        Pagamento pagamento = parcela.getPagamento();

        if (parcelasVencidas(pagamento.getId()).isEmpty()) {
            pagamento.setAtrasado(false);
            pagamentoRepository.save(pagamento);
        }
        return parcela;
    }


    @Transactional
    public Pagamento pagar(Long idPagamento) {
        Pagamento pagamento = pagamentoRepository.findById(idPagamento)
                .orElseThrow( () -> new PagamentoException("O pagamento com id " + idPagamento + "não foi encontrado"));

        List<Parcela> parcelas = pagamento.getParcelas();
        for (Parcela p : parcelas) {
            p.setPago(true);
            parcelaService.pagar(p.getId());
        }

        pagamento.setAtrasado(false);
        pagamentoRepository.save(pagamento);

        return pagamento;
    }


    public List<Parcela> parcelasVencidas(Long idPagamento) {
        return parcelaService.buscarParcelasVencidasPorPagamento(idPagamento);
    }


    public void criarPagamento(Apolice novaApolice, ApoliceCadastroDto apoliceDto) {
        Pagamento novoPagamento = new Pagamento();
        novoPagamento.setAtrasado(false);
        novoPagamento.setTotal(apoliceDto.getValor());
        novoPagamento.setQuantidadeParcelas(apoliceDto.getNumParcelas());
        novoPagamento.setApolice(novaApolice);
        novoPagamento = this.salvar(novoPagamento);

        novoPagamento.setParcelas(new ArrayList<>());
        List<Parcela> parcelas = parcelaService.criarParcelas(novoPagamento);
        for (Parcela p : parcelas) {
            novoPagamento.getParcelas().add(
                parcelaService.salvar(p)
            );
        }

        novaApolice.setPagamento(novoPagamento);
    }
}
