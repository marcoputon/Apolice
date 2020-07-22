package com.exercicio.apolice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documento;

    @NotBlank
    private String nome;

    private String email;

    private Boolean ativo;

    @OneToMany(mappedBy="cliente")
    private List<Endereco> enderecos;

    @OneToMany(mappedBy="cliente")
    private List<Apolice> apolices;
}
