package com.exercicio.apolice.service;

import com.exercicio.apolice.entity.Beneficiario;
import com.exercicio.apolice.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BeneficiarioService {
    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    
    @Transactional
    public Beneficiario salvar(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }
}
