package com.exercicio.apolice.validator;

import com.exercicio.apolice.entity.Cliente;
import com.exercicio.apolice.exception.ClienteInativoException;

public class ClienteValidator {

    public static void validarCliente(Cliente cliente) {
        if (!cliente.getAtivo()) {
            throw new ClienteInativoException("Não é permitido criar apólices para clientes inativos");
        }
    }
}
