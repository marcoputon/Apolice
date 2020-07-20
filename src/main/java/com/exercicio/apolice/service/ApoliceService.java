package com.exercicio.apolice.service;

import com.exercicio.apolice.dto.ApoliceCadastroDto;
import com.exercicio.apolice.entity.Apolice;
import com.exercicio.apolice.entity.Apolice.TipoApolice;
import com.exercicio.apolice.entity.Beneficiario;
import com.exercicio.apolice.entity.Cliente;
import com.exercicio.apolice.repository.ApoliceRepository;
import com.exercicio.apolice.validator.ApoliceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApoliceService {
    @Autowired
    private ApoliceRepository apoliceRepository;

    @Autowired
    private ClienteService cadastroDeCliente;

    @Autowired
    private BeneficiarioService beneficiarioService;

    @Autowired
    private DateService dateService;

    @Transactional
    public Apolice criarESalvar(ApoliceCadastroDto apoliceDto) {
        ApoliceValidator.validarCadastro(apoliceDto);

        Apolice apoliceNova = new Apolice();
        Optional<Cliente> cliente = cadastroDeCliente.buscarPeloId(apoliceDto.getIdCliente());

        if (!cliente.isPresent()) {
            throw new RuntimeException();
        }

        apoliceNova.setCliente(cliente.get());
        apoliceNova.setDataCriacao(dateService.dataAtual());
        apoliceNova.setTipoApolice(apoliceDto.getTipoApolice());
        apoliceNova = apoliceRepository.save(apoliceNova);

        // Cadastro de apolice recebe os beneficiários, a lista começa sempre vazia.
        apoliceNova.setBeneficiarios(new ArrayList<>());
        if (apoliceDto.getTipoApolice().equals(TipoApolice.VIDA)) {

            for (Beneficiario b : apoliceDto.getBeneficiarios()) {
                b.setApolice(apoliceNova);
                Beneficiario beneficiarioNovo = beneficiarioService.salvar(b);
                apoliceNova.getBeneficiarios().add(beneficiarioNovo);
            }
        }
        return apoliceNova;
    }

    public List<Apolice> buscarTodos() {
        return apoliceRepository.findAll();
    }

    public Optional<Apolice> buscarPeloId(Long id) {
        return apoliceRepository.findById(id);
    }
}
