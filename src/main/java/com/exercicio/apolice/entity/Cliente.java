package com.exercicio.apolice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

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

    private boolean ativo;

    @OneToMany(mappedBy="cliente")
    private List<Endereco> enderecos;

}