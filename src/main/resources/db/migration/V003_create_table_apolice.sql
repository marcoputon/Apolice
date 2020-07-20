CREATE TABLE apolice (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    data_criacao date NOT NULL,
    id_cliente bigint(20) NOT NULL,
    tipo_apolice enum('VIDA','RESIDENCIAL','AUTOMOTIVO') NOT NULL,
    id_pagamento bigint(20) DEFAULT NULL,
    PRIMARY KEY (id),
    KEY apolice_FK (id_cliente),
    KEY apolice_FK_1 (id_pagamento),
    CONSTRAINT apolice_FK FOREIGN KEY (id_cliente) REFERENCES cliente (id),
    CONSTRAINT apolice_FK_1 FOREIGN KEY (id_pagamento) REFERENCES pagamento (id)
)