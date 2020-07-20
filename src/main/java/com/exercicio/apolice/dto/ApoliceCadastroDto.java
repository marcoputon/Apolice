package com.exercicio.apolice.dto;

import com.exercicio.apolice.entity.Apolice.TipoApolice;
import com.exercicio.apolice.entity.Beneficiario;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ApoliceCadastroDto {
    private Long idCliente;
    private TipoApolice tipoApolice;
    private BigDecimal valor;
    private Integer numParcelas;
    private List<Beneficiario> beneficiarios;
}
