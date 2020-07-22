package com.exercicio.apolice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@Entity
@Table(name = "beneficiario")
public class Beneficiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documento;

    @NotBlank
    private String nome;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name="id_apolice")
    @JsonIgnore
    @ToString.Exclude
    private Apolice apolice;
}
