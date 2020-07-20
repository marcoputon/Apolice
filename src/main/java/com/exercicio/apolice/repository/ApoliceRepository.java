package com.exercicio.apolice.repository;

import com.exercicio.apolice.entity.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApoliceRepository extends JpaRepository<Apolice, Long> {

}