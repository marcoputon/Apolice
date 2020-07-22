package com.exercicio.apolice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "apolice")
public class Apolice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_criacao")
    @JsonProperty("data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "tipo_apolice")
    @JsonProperty("tipo_apolice")
    @Enumerated(EnumType.STRING)
    private TipoApolice tipoApolice;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    @JsonIgnore
    @ToString.Exclude
    private Cliente cliente;

    @OneToMany(mappedBy="apolice")
    private List<Beneficiario> beneficiarios;

    @OneToOne()
    @JoinColumn(name = "id_pagamento")
    private Pagamento pagamento;


    public enum TipoApolice {
        VIDA,
        RESIDENCIAL,
        AUTOMOTIVO
    }
}