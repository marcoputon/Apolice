CREATE TABLE parcela (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    data_geracao date NOT NULL,
    data_vencimento date NOT NULL,
    valor decimal(10,0) NOT NULL,
    pago bigint(20) unsigned NOT NULL DEFAULT 0,
    id_pagamento bigint(20) NOT NULL,
    PRIMARY KEY (id),
    KEY parcela_FK (id_pagamento),
    CONSTRAINT parcela_FK FOREIGN KEY (id_pagamento) REFERENCES pagamento (id)
)