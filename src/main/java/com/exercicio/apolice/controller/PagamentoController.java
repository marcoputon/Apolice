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

@Slf4j
@RestController
public class PagamentoController {
    @Autowired
    PagamentoService pagamentoService;


    @PostMapping("/pagamento/parcela/{idParcela}")
    public ResponseEntity<Parcela> pagarParcela (@PathVariable final Long idParcela) {
        log.info("pagarParcela() - Pagando parcela: {}", idParcela);

        Parcela parcela = pagamentoService.pagarParcela(idParcela);

        log.info("pagarParcela() - Finalizado pagamento de parcela: {}", idParcela);
        return ResponseEntity.ok(parcela);
    }


    @PostMapping("/pagamento/{idPagamento}")
    public ResponseEntity<Pagamento> pagar (@PathVariable final Long idPagamento) {
        log.info("pagar() - Pagando pagamento: {}", idPagamento);

        Pagamento pagamento = pagamentoService.pagar(idPagamento);

        log.info("pagar() - Finalizado pagamento: {}", idPagamento);
        return ResponseEntity.ok(pagamento);
    }


    @GetMapping("/pagamento/{idPagamento}")
    public ResponseEntity<Pagamento> buscarPagamento (@PathVariable final Long idPagamento) {
        log.info("buscarPagamento() - Consulta de pagamento: {}", idPagamento);

        Pagamento pagamento = pagamentoService.buscarPagamento(idPagamento);

        log.info("buscarPagamento() - Finalizado consulta de pagamento: {}", idPagamento);
        return ResponseEntity.ok(pagamento);
    }


    @GetMapping("/pagamento/{idPagamento}/parcelas-vencidas")
    public ResponseEntity<List<Parcela>> buscarParcelasVencidas (@PathVariable Long idPagamento) {
        log.info("buscarParcelasVencidas() - Consulta de parcelas vencidas: {}", idPagamento);

        List<Parcela> parcelas = pagamentoService.parcelasVencidas(idPagamento);

        log.info("buscarParcelasVencidas() - Finalizado consulta de parcelas vencidas: {}", idPagamento);
        return ResponseEntity.ok(parcelas);
    }
}
