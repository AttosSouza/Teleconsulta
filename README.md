# TELECONSULTA 

## Gerenciamento de Salas de Espera para Teleconsulta

---

## 📋 Roteiro de Criação do Projeto de Teleconsulta

## 1. Arquitetura e Configuração Inicial

* [x] **1. Criação da arquitetura do projeto usando Maven**
* [x] **2. Configuração do WAR para WEB**
* [x] **3. Configuração do JSF**
* [x] **4. Inclusão do PrimeFaces**
* [x] **5. Configuração do Hibernate e conexão com o banco de dados**
* [x] **6. Levantamento das tabelas da aplicação**

---

## 2. Mapeamento das Entidades

* [ ] **2.1. Mapeamento das entidades**

---

## 3. Telas do Usuário

* [ ] **3.1. Tela de login do usuário**
* [ ] **3.2. Tela de cadastro do usuário**
* [ ] **3.3. Tela de pesquisa do usuário**

---

## 4. Telas do Paciente

* [ ] **4.1. Criação da tela do paciente**
* [ ] **4.2. Tela de cadastro do paciente**
* [ ] **4.3. Tela de pesquisa do paciente**

---

## 5. Telas da Unidade

* [ ] **5.1. Criação da tela da unidade**
* [ ] **5.2. Tela de cadastro da unidade**
* [ ] **5.3. Tela de pesquisa da unidade**

---

## 6. Telas da Sala

* [ ] **6.1. Criação da tela da sala**
* [ ] **6.2. Tela de cadastro da sala**
* [ ] **6.3. Tela de pesquisa da sala**

---

## 7. Telas da Reserva

* [ ] **7.1. Criação da tela da reserva**
* [ ] **7.2. Tela de cadastro da reserva**
* [ ] **7.3. Tela de pesquisa da reserva**

---

## 8. Camada de Persistência

* [ ] **8.1. Mapeamento dos Repositories**

---

## 9. Serviços e Regras de Negócio

### 9.1. Regras Explícitas

* [ ] **9.1.1. Criar reserva:** Deve possibilitar reservar uma sala, validando conflitos de horário
* [ ] **9.1.2. Consultar disponibilidade:** Listar salas disponíveis filtrando por unidade e período
* [ ] **9.1.3. Cancelar reserva:** Permitir o cancelamento de uma reserva existente

### 9.2. Regras Implícitas (Validações)

* [ ] **9.2.1. Validar campo *nome* do usuário (não pode ser nulo)**
* [ ] **9.2.2. Validar campo *email* do usuário (formato válido)**
* [ ] **9.2.3. Validar campo *email* do usuário (não pode ser nulo)**
* [ ] **9.2.4. Validar campo *CPF* do usuário (CPF válido)**
* [ ] **9.2.5. Validar campo *CPF* do usuário (não pode ser nulo)**

#  Como Rodar com Docker

O projeto já está totalmente configurado para rodar via Docker.

### 1. **Gerar a imagem + build do projeto**
Sempre que você alterar arquivos que influenciam o ambiente Docker (como Dockerfile, pom.xml, dependências), execute:


docker compose build

Ou simplesmente:

docker compose up --build

Após o build inicial, você pode subir apenas com:

docker compose up

###  Acessar a aplicação

O projeto roda na porta:

**http://localhost:8080/teleconsulta**

Credenciais estão definidas no arquivo `docker-compose.yml`.
