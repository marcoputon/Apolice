CREATE TABLE pagamento (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    total decimal(10,0) NOT NULL,
    atrasado tinyint(1) NOT NULL,
    PRIMARY KEY (id)
)