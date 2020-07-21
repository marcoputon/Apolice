package com.exercicio.apolice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

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
    private boolean atrasado;

    @Column(name = "quantidade_parcelas")
    @Min(1)
    private Integer quantidadeParcelas;

    @OneToOne(mappedBy = "pagamento")
    @JsonIgnore
    @ToString.Exclude
    private Apolice apolice;

    @OneToMany(mappedBy="pagamento")
    private List<Parcela> parcelas;

}
