package com.exercicio.apolice.controller;

import com.exercicio.apolice.entity.Pagamento;
import com.exercicio.apolice.entity.Parcela;
import com.exercicio.apolice.service.PagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;


    @PostMapping("/pagamento/parcela/{id}")
    public ResponseEntity<Parcela> pagarParcela (@PathVariable(value="id") final Long idParcela) {
        log.info("pagarParcela() - Pagando parcela: {}", idParcela);

        Parcela parcela = pagamentoService.pagarParcela(idParcela);

        log.info("pagarParcela() - Finalizado pagamento de parcela: {}", idParcela);
        return ResponseEntity.ok(parcela);
    }


    @PostMapping("/pagamento/{id}")
    public ResponseEntity<Pagamento> pagar (@PathVariable(value="id") final Long idPagamento) {
        log.info("pagar() - Pagando pagamento: {}", idPagamento);

        Pagamento pagamento = pagamentoService.pagar(idPagamento);

        log.info("pagar() - Finalizado pagamento: {}", idPagamento);
        return ResponseEntity.ok(pagamento);
    }


    @GetMapping("/pagamento/{idPagamento}")
    public ResponseEntity<Pagamento> buscarPagamento (@PathVariable final Long idPagamento) {
        Optional<Pagamento> pagamento = pagamentoService.buscarPagamento(idPagamento);
        return ResponseEntity.of(pagamento);
    }


    @GetMapping("/pagamento/{idPagamento}/parcelas-vencidas")
    public List<Parcela> parcelasVencidas (@PathVariable Long idPagamento) {
        return pagamentoService.parcelasVencidas(idPagamento);
    }
}
