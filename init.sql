CREATE TABLE IF NOT EXISTS status_reserva (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(30) UNIQUE NOT NULL
);

INSERT INTO status_reserva (nome) VALUES ('Confirmada') ON CONFLICT (nome) DO NOTHING;
INSERT INTO status_reserva (nome) VALUES ('Cancelada') ON CONFLICT (nome) DO NOTHING;
INSERT INTO status_reserva (nome) VALUES ('Pendente') ON CONFLICT (nome) DO NOTHING;
INSERT INTO status_reserva (nome) VALUES ('Finalizada') ON CONFLICT (nome) DO NOTHING;