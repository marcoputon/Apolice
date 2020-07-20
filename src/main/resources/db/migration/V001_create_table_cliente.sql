CREATE TABLE IF NOT EXISTS cliente (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    documento text DEFAULT NULL,
    nome varchar(100) NOT NULL,
    email varchar(100) DEFAULT NULL,
    ativo tinyint(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);