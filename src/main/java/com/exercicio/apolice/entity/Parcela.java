package com.exercicio.apolice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Data
@Entity
@Table(name = "parcela")
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_geracao")
    @JsonProperty("data_geracao")
    private LocalDate dataGeracao;

    @Column(name = "data_vencimento")
    @JsonProperty("data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "valor")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valor;

    @Column(name = "pago")
    private Boolean pago;

    @ManyToOne
    @JoinColumn(name="id_pagamento")
    @JsonIgnore
    @ToString.Exclude
    private Pagamento pagamento;
}
