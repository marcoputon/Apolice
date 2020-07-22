package com.exercicio.apolice.service;

import com.exercicio.apolice.dto.ApoliceCadastroDto;
import com.exercicio.apolice.entity.Apolice;
import com.exercicio.apolice.entity.Apolice.TipoApolice;
import com.exercicio.apolice.entity.Beneficiario;
import com.exercicio.apolice.entity.Cliente;
import com.exercicio.apolice.repository.ApoliceRepository;
import com.exercicio.apolice.validator.ApoliceValidator;
import com.exercicio.apolice.validator.ClienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApoliceService {
    @Autowired
    private ApoliceRepository apoliceRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BeneficiarioService beneficiarioService;

    @Autowired
    private PagamentoService pagamentoService;


    @Transactional
    public Apolice criar(ApoliceCadastroDto apoliceDto) {
        ApoliceValidator.validarCadastro(apoliceDto);

        Cliente cliente = clienteService.buscarPeloId(apoliceDto.getIdCliente());
        ClienteValidator.validarCliente(cliente);

        Apolice novaApolice = new Apolice();
        novaApolice.setCliente(cliente);
        novaApolice.setDataCriacao(LocalDate.now());
        novaApolice.setTipoApolice(apoliceDto.getTipoApolice());
        novaApolice = apoliceRepository.save(novaApolice);

        criarBeneficiarios(novaApolice, apoliceDto);

        pagamentoService.criarPagamento(novaApolice, apoliceDto);

        return novaApolice;
    }


    private void criarBeneficiarios(Apolice apoliceNova, ApoliceCadastroDto apoliceDto) {
        if (!apoliceDto.getTipoApolice().equals(TipoApolice.VIDA))
            return;

        List<Beneficiario> beneficiarios = apoliceDto.getBeneficiarios();
        apoliceNova.setBeneficiarios(new ArrayList<>());
        for (Beneficiario b : beneficiarios) {
            b.setApolice(apoliceNova);
            Beneficiario beneficiarioNovo = beneficiarioService.salvar(b);
            apoliceNova.getBeneficiarios().add(beneficiarioNovo);
        }
    }


    public List<Apolice> buscarTodos() {
        return apoliceRepository.findAll();
    }
}
