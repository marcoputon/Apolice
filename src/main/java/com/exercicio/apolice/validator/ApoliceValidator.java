package com.exercicio.apolice.validator;

import com.exercicio.apolice.dto.ApoliceCadastroDto;
import com.exercicio.apolice.entity.Apolice;
import com.exercicio.apolice.entity.Beneficiario;
import com.exercicio.apolice.exception.CadastroException;

import java.math.BigDecimal;

public class ApoliceValidator {

    public static void validarCadastro(ApoliceCadastroDto apoliceDto) throws CadastroException {
        validarBeneficiarios(apoliceDto);
    }


    public static void validarBeneficiarios(ApoliceCadastroDto apoliceDto) throws CadastroException {
        BigDecimal soma = new BigDecimal(0);
        for (Beneficiario b: apoliceDto.getBeneficiarios()) {
            soma = soma.add(b.getValor());
        }

        if (soma.compareTo(apoliceDto.getValor()) != 0) {
            throw new CadastroException("A soma de valores dos beneficiários é diferente do total");
        }
    }
}