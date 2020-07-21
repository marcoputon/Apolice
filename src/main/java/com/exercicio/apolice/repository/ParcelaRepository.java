package com.exercicio.apolice.repository;

import com.exercicio.apolice.entity.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
    @Query(value="select * from parcela where not pago and data_vencimento < CURRENT_DATE", nativeQuery=true)
    Collection<Parcela> encontrarParcelasVencidas();
}
