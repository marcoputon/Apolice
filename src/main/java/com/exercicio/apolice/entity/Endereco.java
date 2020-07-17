package com.exercicio.apolice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String cep;

    private String rua;

    private String cidade;

    private String estado;

    @ManyToOne
    @JoinColumn(name="id_cliente", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Cliente cliente;

}
