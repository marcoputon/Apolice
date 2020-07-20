package com.exercicio.apolice.dto;

import com.exercicio.apolice.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String unidade;
    private String ibge;
    private String gia;

    public Endereco paraEntidade() {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setCep(cep);
        novoEndereco.setCidade(localidade);
        novoEndereco.setEstado(uf);
        novoEndereco.setRua(logradouro);
        return novoEndereco;
    }
}
