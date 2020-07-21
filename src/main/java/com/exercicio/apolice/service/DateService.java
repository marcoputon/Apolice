package com.exercicio.apolice.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public Date proximoDia(Date data, int dia) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        if (cal.get(Calendar.DAY_OF_MONTH) > dia) {
            cal.add(Calendar.MONTH, 1);
        }

        cal.set(Calendar.DAY_OF_MONTH, dia);

        return cal.getTime();
    }

    public Date somarDias(Date data, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        cal.add(Calendar.DAY_OF_MONTH, dias);

        return cal.getTime();
    }
}
