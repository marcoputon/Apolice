package com.exercicio.apolice.controller;

import com.exercicio.apolice.dto.ApoliceCadastroDto;
import com.exercicio.apolice.entity.Apolice;
import com.exercicio.apolice.service.ApoliceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ApoliceController {
    @Autowired
    private ApoliceService apoliceService;


    @PostMapping("/apolices")
    public Apolice criar(@RequestBody ApoliceCadastroDto apoliceDto) {
        log.info("criar() - Criando apolice: {}", apoliceDto);

        Apolice apolice = apoliceService.criar(apoliceDto);

        log.info("criar() - Finalizado criação de apolice: {}", apoliceDto);
        return apolice;
    }


    @GetMapping("/apolices")
    public List<Apolice> listar () {
        return apoliceService.buscarTodos();
    }
}
