package com.exercicio.apolice.service;

import com.exercicio.apolice.entity.Pagamento;
import com.exercicio.apolice.entity.Parcela;
import com.exercicio.apolice.exception.PagamentoException;
import com.exercicio.apolice.repository.ParcelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        Parcela parcela = parcelaRepository.findById(id)
            .orElseThrow( () -> new PagamentoException("A parcela com id " + id + " não foi encontrada"));

        parcela.setPago(true);
        return parcelaRepository.save(parcela);
    }


    public List<Parcela> buscarParcelasVencidas() {
        return new ArrayList<>(parcelaRepository.encontrarParcelasVencidas());
    }


    public List<Parcela> buscarParcelasVencidasPorPagamento(Long idPagamento) {
        return new ArrayList<>(parcelaRepository.encontrarParcelasVencidasPorPagamento(idPagamento));
    }


    public List<Parcela> criarParcelas(Pagamento pagamento) {
        List<Parcela> parcelas = new ArrayList<>();
        Integer qtdeParcelas = pagamento.getQuantidadeParcelas();
        BigDecimal total = pagamento.getTotal();
        BigDecimal valorParcela = total.divide(BigDecimal.valueOf(qtdeParcelas), 2, RoundingMode.HALF_UP);

        int num_parcela = 0;
        Parcela parcela;
        while (num_parcela < qtdeParcelas) {
            LocalDate dataGeracao = criarDataGeracaoParcela(num_parcela);

            parcela = new Parcela();
            parcela.setPago(false);
            parcela.setValor(valorParcela);
            parcela.setDataGeracao(dataGeracao);
            parcela.setDataVencimento(dataGeracao.plusDays(12));
            parcela.setPagamento(pagamento);

            parcelas.add(parcela);
            num_parcela++;
        }

        // total - valorParcela * qtdeParcelas
        BigDecimal sobra = total.subtract(valorParcela.multiply(BigDecimal.valueOf(qtdeParcelas)));

        // soma na última parcela a sobra por perda de precisão
        Parcela ultimaParcela = parcelas.get(parcelas.size() - 1);
        ultimaParcela.setValor(valorParcela.add(sobra));

        return parcelas;
    }


    private LocalDate criarDataGeracaoParcela (int numero_parcela) {
        LocalDate data = LocalDate.now().plusMonths(numero_parcela);
        if (data.getDayOfMonth() > 3) {
            data = data.plusMonths(1);
        }
        return data.withDayOfMonth(3);
    }
}
