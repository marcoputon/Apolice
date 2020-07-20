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

@Getter
@Setter
@Data
@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal total;

    @Column(name = "atrasado")
    @NotBlank
    private boolean atrasado;

    @OneToOne(mappedBy = "pagamento")
    @JsonIgnore
    @ToString.Exclude
    private Apolice apolice;

}
