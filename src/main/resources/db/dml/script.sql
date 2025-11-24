INSERT INTO endereco (id, logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES
(1, 'Rua das Flores', '123', NULL, 'Centro', 'São Paulo', 'SP', '01001-000'),
(2, 'Avenida Brasil', '450', 'Bloco A', 'Jardins', 'São Paulo', 'SP', '01430-000'),
(3, 'Rua dos Lírios', '87', NULL, 'Vila Nova', 'Rio de Janeiro', 'RJ', '20010-050'),
(4, 'Rua das Palmeiras', '12', 'Casa 2', 'Icaraí', 'Niterói', 'RJ', '24230-210'),
(5, 'Travessa da Paz', '55', NULL, 'Boa Vista', 'Recife', 'PE', '50050-030'),
(6, 'Rua Central', '200', NULL, 'Centro', 'Curitiba', 'PR', '80010-100'),
(7, 'Avenida Atlântica', '3500', NULL, 'Copacabana', 'Rio de Janeiro', 'RJ', '22021-001'),
(8, 'Rua Projetada', '999', 'Fundos', 'Centro', 'Belo Horizonte', 'MG', '30110-022'),
(9, 'Rua Almeida', '14', NULL, 'Vila Nova', 'Florianópolis', 'SC', '88010-100'),
(10, 'Avenida Paulista', '1000', NULL, 'Bela Vista', 'São Paulo', 'SP', '01310-100');

INSERT INTO usuario (id, cpf, nome, senha, email, data_cadastro) VALUES
(1, '11111111111', 'João Silva', '123456', 'joao@example.com', '2025-01-01'),
(2, '22222222222', 'Maria Souza', '123456', 'maria@example.com', '2025-01-02'),
(3, '33333333333', 'Carlos Lima', '123456', 'carlos@example.com', '2025-01-03'),
(4, '44444444444', 'Ana Paula', '123456', 'ana@example.com', '2025-01-04'),
(5, '55555555555', 'Bruno Rocha', '123456', 'bruno@example.com', '2025-01-05'),
(6, '66666666666', 'Patrícia Gomes', '123456', 'patricia@example.com', '2025-01-06'),
(7, '77777777777', 'Ricardo Alves', '123456', 'ricardo@example.com', '2025-01-07'),
(8, '88888888888', 'Fernanda Dias', '123456', 'fernanda@example.com', '2025-01-08'),
(9, '99999999999', 'Lucas Martins', '123456', 'lucas@example.com', '2025-01-09'),
(10, '10101010101', 'Juliana Castro', '123456', 'juliana@example.com', '2025-01-10');

INSERT INTO paciente (id, cpf, nome, nome_social, sexo, nome_mae, nome_pai, telefone, email, rg, cns, data_nascimento, endereco_id) VALUES
(1, '11111111111', 'Pedro Alves', NULL, 'M', 'Maria Alves', 'José Alves', '11999990001', 'pedro@example.com', '1234567', '12345678901', '1990-05-10', 1),
(2, '22222222222', 'Mariana Silva', NULL, 'F', 'Joana Silva', 'Carlos Silva', '11999990002', 'mariana@example.com', '2345678', '12345678902', '1988-02-20', 2),
(3, '33333333333', 'Rafael Costa', NULL, 'M', 'Ana Costa', 'Paulo Costa', '11999990003', 'rafael@example.com', '3456789', '12345678903', '1985-03-15', 3),
(4, '44444444444', 'Camila Rocha', NULL, 'F', 'Patrícia Rocha', 'Bruno Rocha', '11999990004', 'camila@example.com', '4567890', '12345678904', '1992-10-05', 4),
(5, '55555555555', 'Thiago Moreira', NULL, 'M', 'Sandra Moreira', 'Carlos Moreira', '11999990005', 'thiago@example.com', '5678901', '12345678905', '1993-06-25', 5),
(6, '66666666666', 'Beatriz Lima', NULL, 'F', 'Adriana Lima', 'Marcos Lima', '11999990006', 'beatriz@example.com', '6789012', '12345678906', '1991-07-30', 6),
(7, '77777777777', 'Gustavo Mendes', NULL, 'M', 'Lucia Mendes', 'Fernando Mendes', '11999990007', 'gustavo@example.com', '7890123', '12345678907', '1994-01-12', 7),
(8, '88888888888', 'Larissa Sampaio', NULL, 'F', 'Helena Sampaio', 'Rafael Sampaio', '11999990008', 'larissa@example.com', '8901234', '12345678908', '1995-04-18', 8),
(9, '99999999999', 'Eduardo Teixeira', NULL, 'M', 'Marta Teixeira', 'Roberto Teixeira', '11999990009', 'eduardo@example.com', '9012345', '12345678909', '1996-09-22', 9),
(10, '10101010101', 'Aline Fernandes', NULL, 'F', 'Vera Fernandes', 'João Fernandes', '11999990010', 'aline@example.com', '0123456', '12345678910', '1997-11-05', 10);

INSERT INTO unidade_saude (id, cnpj, nome, razao_social, sigla, cnes, endereco_id) VALUES
(1, '11.111.111/0001-11', 'UBS Central', 'Unidade Básica de Saúde Central', 'UBSC', '123001', 1),
(2, '22.222.222/0001-22', 'UPA Norte', 'Unidade de Pronto Atendimento Norte', 'UPAN', '123002', 2),
(3, '33.333.333/0001-33', 'Hospital Municipal', 'Hospital Municipal São Lucas', 'HMSL', '123003', 3),
(4, '44.444.444/0001-44', 'UBS Sul', 'Unidade Básica de Saúde Sul', 'UBSS', '123004', 4),
(5, '55.555.555/0001-55', 'UPA Leste', 'Unidade de Pronto Atendimento Leste', 'UPAL', '123005', 5),
(6, '66.666.666/0001-66', 'Clínica Popular', 'Clínica Popular Saúde Já', 'CPSJ', '123006', 6),
(7, '77.777.777/0001-77', 'Hospital Oeste', 'Hospital Regional Oeste', 'HRO', '123007', 7),
(8, '88.888.888/0001-88', 'UBS Norte', 'Unidade Básica de Saúde Norte', 'UBSN', '123008', 8),
(9, '99.999.999/0001-99', 'UPA Sul', 'Unidade de Pronto Atendimento Sul', 'UPAS', '123009', 9),
(10, '10.101.010/0001-10', 'Hospital Central', 'Hospital Central Municipal', 'HCM', '123010', 10);

INSERT INTO sala (id, nome, capacidade, unidade_saude_id) VALUES
(1, 'Sala 01', 5, 1),
(2, 'Sala 02', 3, 1),
(3, 'Sala 03', 4, 2),
(4, 'Sala 04', 6, 3),
(5, 'Sala 05', 2, 3),
(6, 'Sala 06', 5, 4),
(7, 'Sala 07', 4, 5),
(8, 'Sala 08', 3, 6),
(9, 'Sala 09', 2, 7),
(10, 'Sala 10', 8, 10);

INSERT INTO status_reserva (id, nome) VALUES
(1, 'PENDENTE'),
(2, 'CONFIRMADA'),
(3, 'CANCELADA'),
(4, 'FINALIZADA');

INSERT INTO reserva (
    id,
    data_hora_inicio,
    data_hora_termino,
    sala_id,
    usuario_id,
    status_id,
    usuario_cancelamento_id,
    motivo_cancelamento,
    data_cancelamento
) VALUES
(1, '2025-01-01 09:00', '2025-01-01 10:00', 1, 1, 1, NULL, NULL, NULL),
(2, '2025-01-02 10:00', '2025-01-02 11:00', 2, 2, 2, NULL, NULL, NULL),
(3, '2025-01-03 14:00', '2025-01-03 15:00', 3, 3, 2, NULL, NULL, NULL),
(4, '2025-01-04 08:00', '2025-01-04 09:00', 4, 4, 3, 4, 'Paciente faltou', '2025-01-04 07:50'),
(5, '2025-01-05 16:00', '2025-01-05 17:00', 5, 5, 1, NULL, NULL, NULL),
(6, '2025-01-06 11:00', '2025-01-06 12:00', 6, 6, 1, NULL, NULL, NULL),
(7, '2025-01-07 13:00', '2025-01-07 14:00', 7, 7, 2, NULL, NULL, NULL),
(8, '2025-01-08 15:00', '2025-01-08 16:00', 8, 8, 4, NULL, NULL, NULL),
(9, '2025-01-09 09:30', '2025-01-09 10:30', 9, 9, 3, 9, 'Cancelado por conflito de agenda', '2025-01-09 09:00'),
(10, '2025-01-10 17:00', '2025-01-10 18:00', 10, 10, 2, NULL, NULL, NULL);


SELECT setval('endereco_id_seq', (SELECT MAX(id) FROM endereco));

SELECT setval('paciente_id_seq', (SELECT MAX(id) FROM paciente));

SELECT setval('usuario_id_seq', (SELECT MAX(id) FROM usuario));

SELECT setval('unidade_saude_id_seq', (SELECT MAX(id) FROM unidade_saude));

SELECT setval('sala_id_seq', (SELECT MAX(id) FROM sala));

SELECT setval('reserva_id_seq', (SELECT MAX(id) FROM reserva));
