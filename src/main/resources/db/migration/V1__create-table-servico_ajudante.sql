CREATE TABLE servico_ajudante (
    id BIGINT auto_increment,
    servico_id BIGINT not null,
    ajudante_id BIGINT not null,
    pago BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id),
    FOREIGN KEY (servico_id) REFERENCES servicos(id),
    FOREIGN KEY (ajudante_id) REFERENCES ajudantes(id) ON DELETE CASCADE
);
