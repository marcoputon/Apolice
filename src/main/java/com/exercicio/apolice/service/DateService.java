package com.exercicio.apolice.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateService {
    private final SimpleDateFormat dateFormatterPTBR = new SimpleDateFormat("dd/MM/yyyy");

    public String formatarData(Date data) {
        return dateFormatterPTBR.format(data);
    }

    public Date dataAtual() {
        return new Date();
    }
}
