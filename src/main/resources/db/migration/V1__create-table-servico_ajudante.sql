CREATE TABLE servico_ajudante (
    servico_id BIGINT,
    ajudante_id BIGINT,
    PRIMARY KEY (servico_id, ajudante_id),
    FOREIGN KEY (servico_id) REFERENCES servicos(id),
    FOREIGN KEY (ajudante_id) REFERENCES ajudantes(id) ON DELETE CASCADE
);
