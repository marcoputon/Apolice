package com.exercicio.apolice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Data
@Entity
@Table(name = "parcela")
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_criacao")
    @JsonProperty("data_criacao")
    private Date dataCriacao;

    @Column(name = "data_vencimento")
    @JsonProperty("data_vencimento")
    private Date dataVencimento;

    @Column(name = "valor")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valor;

    @Column(name = "pago")
    @NotBlank
    private boolean pago;

    @ManyToOne
    @JoinColumn(name="id_pagamento", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Pagamento pagamento;

}
