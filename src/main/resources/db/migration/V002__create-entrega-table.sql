CREATE SEQUENCE IF NOT EXISTS y_seq;

CREATE TABLE IF NOT EXISTS entrega (
     id BIGINT DEFAULT nextval('y_seq'),
     cliente_id BIGINT NOT NULL,
     taxa DECIMAL(10,2) NOT NULL,
     status VARCHAR(20) NOT NULL,
     data_pedido DATE NOT NULL,
     data_finalizacao DATE,

     destinatario_nome VARCHAR(64) NOT NULL,
     destinatario_logradouro VARCHAR(64) NOT NULL,
     destinatario_numero VARCHAR(64) NOT NULL,
     destinatario_complemento VARCHAR(64) NOT NULL,
     destinatario_bairro VARCHAR(64) NOT NULL,

     primary key (id)
);

ALTER TABLE entrega ADD CONSTRAINT fk_entrega_cliente
    FOREIGN KEY (cliente_id) REFERENCES client (id);