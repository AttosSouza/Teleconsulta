CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    logradouro VARCHAR(150) NOT NULL,
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(100),
    cidade VARCHAR(100) NOT NULL,
    estado CHAR(2) NOT NULL,
    cep VARCHAR(10)
);

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    data_cadastro DATE NOT NULL
);

CREATE TABLE paciente (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    nome VARCHAR(150) NOT NULL,
    nome_social VARCHAR(100),
    sexo CHAR(1) NOT NULL,
    nome_mae VARCHAR(100),
    nome_pai VARCHAR(100),
    telefone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    rg VARCHAR(20),
    cns VARCHAR(15),
    data_nascimento DATE,
    endereco_id INT NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE unidade_saude (
    id SERIAL PRIMARY KEY,
    cnpj VARCHAR(18) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    razao_social VARCHAR(150),
    sigla VARCHAR(10),
    cnes VARCHAR(15),
    endereco_id INT NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE sala (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    capacidade INT NOT NULL,
    unidade_saude_id INT NOT NULL,
    FOREIGN KEY (unidade_saude_id) REFERENCES unidade_saude(id)
);

CREATE TABLE status_reserva (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE reserva (
    id SERIAL PRIMARY KEY,
    data_hora_inicio TIMESTAMP NOT NULL,
    data_hora_termino TIMESTAMP NOT NULL,
    sala_id INT NOT NULL,
    usuario_id INT NOT NULL,
    status_id INT NOT NULL,
    usuario_cancelamento_id INT,
    motivo_cancelamento VARCHAR(255),
    data_cancelamento TIMESTAMP,

    FOREIGN KEY (sala_id) REFERENCES sala(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (usuario_cancelamento_id) REFERENCES usuario(id),
    FOREIGN KEY (status_id) REFERENCES status_reserva(id),

    CHECK (data_hora_inicio < data_hora_termino)
);
