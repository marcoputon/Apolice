package com.exercicio.apolice.scheduler;

import com.exercicio.apolice.entity.Parcela;
import com.exercicio.apolice.service.DateService;
import com.exercicio.apolice.service.ParcelaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class AtrasoPagamentoScheduler {

    @Autowired
    private ParcelaService parcelaService;

    @Autowired
    DateService dateService;


    // Chama o método a meia noite
    @Scheduled(cron = "0 12 18 * * *")
    public void validarAtrasoPagamento() {
        log.info("validarAtrasoPagamento(): Validando pagamentos atrasados.");

        List<Parcela> parcelasVencidas = parcelaService.buscarParcelasVencidas();
        for (Parcela p : parcelasVencidas) {
            log.info("Parcela vencida: {}", p.getId());
            p.setValor(p.getValor().multiply( BigDecimal.valueOf(1.0375)));
            parcelaService.salvar(p);
        }

        log.info("validarAtrasoPagamento(): Validação concluída.");
    }
}
