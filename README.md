# Apolice
Exercício para praticar Spring Boot.

O projeto consiste em uma API para um sistema de apólices. 
Não tenho certeza das regras e como funciona exatamente um sistema de apólices, este exercício é apenas para praticar.

#### Dependencias

Por enquanto, a API utiliza:
* *Flyway* para versionamento do banco.
* *Lombok* para getters e setters.
* *Spring Boot* (*JPA*, *Validation*, *Web*, dentre outros).

Além disso, para a aplicação funcionar, é necessário o **Java 8** e uma conexão com o banco de dados. A aplicação foi desenvolvida utilizando o **MariaDB**.

#### Funcionalidades

A API permite o cadastro de **clientes** e **apólices**.

O **endereço** de um cliente é criado consultando o serviço da [_viacep_](https://viacep.com.br/).

Ao cadastrar uma apólice, se o tipo da mesma for _VIDA_, a lista de **beneficiários** será criada com os dados do parâmetro da requisição.

Ao cadastrar uma apólice, também serão cadastrados o **pagamento** e suas **parcelas**.

Todos os dias, à meia noite, um _scheduler_ executa um método para validar os pagamentos com parcelas atrasadas, acrescentando 3.75% no valor de cada parcela vencida.

Quando uma parcela vence, o pagamento é marcado como _atrasado_. Quando todas as parcelas vencidas forem pagas, o pagamento deixa de ser atrasado.

#### Exceções
Cada requisição possui suas validações específicas, podendo lançar diferentes exceções, tratadas por um _handler_ que retornará a mensagem adequada em uma resposta HTTP de status _Bad Request_.
* **CadastroException**.
* **ClienteInativoException**.
* **ClienteInexistenteException**.
* **PagamentoException**.

#### Endpoints
|Descrição|Tipo|Url|Parâmetros|Retorno|
|---|---|---|---|---|
|Listar clientes|GET|/clientes|-|List\<Cliente\>|
|Buscar cliente por id|GET|/clientes/{Long id}|-|Cliente|
|Listar apólices|GET|/apolices|-|List\<Apolice\>|
|Buscar pagamento por id|GET|/pagamento/{Long id}|-|Pagamento|
|Listar parcelas vencidas|GET|/pagamento/{Long id}/parcelas-vencidas|-|List\<Parcela\>|
|Cadastrar cliente|POST|/clientes|Cliente cliente, String cep|Cliente|
|Cadastrar apolice|POST|/apolices|ApoliceCadastroDto apoliceDto|Apolice|
|Pagar parcela por id|POST|/pagamento/parcela/{Long id}|-|Parcela|
|Pagar todas as parcelas de um pagamento por id|POST|/pagamento/{Long id}|-|Pagamento|
