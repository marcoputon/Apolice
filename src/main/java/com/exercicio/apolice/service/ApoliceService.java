package com.exercicio.apolice.service;

import com.exercicio.apolice.dto.ApoliceCadastroDto;
import com.exercicio.apolice.entity.*;
import com.exercicio.apolice.entity.Apolice.TipoApolice;
import com.exercicio.apolice.exception.ClienteInativoException;
import com.exercicio.apolice.exception.ClienteInexistenteException;
import com.exercicio.apolice.repository.ApoliceRepository;
import com.exercicio.apolice.validator.ApoliceValidator;
import com.exercicio.apolice.validator.ClienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ApoliceService {
    @Autowired
    private ApoliceRepository apoliceRepository;

    @Autowired
    private ClienteService cadastroDeCliente;

    @Autowired
    private BeneficiarioService beneficiarioService;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private ParcelaService parcelaService;

    @Autowired
    private DateService dateService;


    @Transactional
    public Apolice criar(ApoliceCadastroDto apoliceDto) {
        ApoliceValidator.validarCadastro(apoliceDto);

        Optional<Cliente> cliente = cadastroDeCliente.buscarPeloId(apoliceDto.getIdCliente());

        // Tive que deixar essa validação aqui, pois não pode usar Optional
        // em parâmetros de métodos.
        if (!cliente.isPresent()) {
            throw new ClienteInexistenteException("Cliente não encontrado");
        }
        ClienteValidator.validarCliente(cliente.get());

        // Criar Apolice
        Apolice novaApolice = new Apolice();
        novaApolice.setCliente(cliente.get());
        novaApolice.setDataCriacao(dateService.dataAtual());
        novaApolice.setTipoApolice(apoliceDto.getTipoApolice());
        novaApolice = apoliceRepository.save(novaApolice);

        // Criar Beneficiários
        if (apoliceDto.getTipoApolice().equals(TipoApolice.VIDA)) {
            salvarBeneficiarios(novaApolice, apoliceDto.getBeneficiarios());
        }

        // Criar Pagamento
        salvarPagamento(novaApolice, apoliceDto);

        return novaApolice;
    }


    private void salvarPagamento(Apolice novaApolice, ApoliceCadastroDto apoliceDto) {
        Pagamento novoPagamento = new Pagamento();
        novoPagamento.setAtrasado(false);
        novoPagamento.setTotal(apoliceDto.getValor());
        novoPagamento.setQuantidadeParcelas(apoliceDto.getNumParcelas());
        novoPagamento.setApolice(novaApolice);
        novoPagamento = pagamentoService.salvar(novoPagamento);

        novoPagamento.setParcelas(new ArrayList<>());
        List<Parcela> parcelas = criarParcelas(novoPagamento);
        for (Parcela p : parcelas) {
            novoPagamento.getParcelas().add(p);
            parcelaService.salvar(p);
        }

        novaApolice.setPagamento(novoPagamento);
    }

    private List<Parcela> criarParcelas(Pagamento pagamento) {
        List<Parcela> parcelas = new ArrayList<>();
        Integer qtdeParcelas = pagamento.getQuantidadeParcelas();
        BigDecimal total = pagamento.getTotal();
        BigDecimal valorParcela = total.divide(new BigDecimal(qtdeParcelas), 2, RoundingMode.HALF_UP);

        int num_parcela = 0;
        Parcela parcela;
        while (num_parcela < qtdeParcelas) {
            Date dataGeracao = criarDataGeracaoParcela(num_parcela);

            parcela = new Parcela();
            parcela.setPago(false);
            parcela.setValor(valorParcela);
            parcela.setDataGeracao(dataGeracao);
            parcela.setDataVencimento(dateService.somarDias(dataGeracao, 12));
            parcela.setPagamento(pagamento);

            parcelas.add(parcela);
            num_parcela++;
        }

        // total - valorParcela * qtdeParcelas
        BigDecimal sobra = total.subtract(valorParcela.multiply(new BigDecimal(qtdeParcelas)));

        // soma na última parcela a sobra por perda de precisão
        Parcela ultimaParcela = parcelas.get(parcelas.size() - 1);
        ultimaParcela.setValor(valorParcela.add(sobra));

        return parcelas;
    }

    private Date criarDataGeracaoParcela (int numero_parcela) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, numero_parcela);
        return dateService.proximoDia(cal.getTime(),3);
    }


    private void salvarBeneficiarios(Apolice apoliceNova, List<Beneficiario> beneficiarios) {
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


    public Optional<Apolice> buscarPeloId(Long id) {
        return apoliceRepository.findById(id);
    }
}
