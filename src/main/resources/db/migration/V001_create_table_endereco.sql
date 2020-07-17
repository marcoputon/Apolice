CREATE TABLE IF NOT EXISTS endereco (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    cep varchar(10) DEFAULT NULL,
    rua varchar(50) DEFAULT NULL,
    cidade varchar(30) DEFAULT NULL,
    estado varchar(2) DEFAULT NULL,
    id_cliente bigint(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT endereco_FK FOREIGN KEY (id_cliente) REFERENCES cliente (id)
);