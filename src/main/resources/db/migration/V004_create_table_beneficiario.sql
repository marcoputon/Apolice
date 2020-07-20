CREATE TABLE beneficiario (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    documento text DEFAULT NULL,
    nome varchar(100) NOT NULL,
    valor bigint(20) NOT NULL,
    id_apolice bigint(20) NOT NULL,
    PRIMARY KEY (id),
    KEY beneficiario_FK (id_apolice),
    CONSTRAINT beneficiario_FK FOREIGN KEY (id_apolice) REFERENCES apolice (id)
)