package com.exercicio.apolice.repository;

import com.exercicio.apolice.entity.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

}
