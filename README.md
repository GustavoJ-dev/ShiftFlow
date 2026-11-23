# ShiftFlow

**API REST para Gestão de Escalas e Cronogramas Corporativos**

[![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-6DB33F?logo=springboot)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-Multi--Module-C71A36?logo=apachemaven)](https://maven.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.4-4479A1?logo=mysql)](https://www.mysql.com/)
[![RabbitMQ](https://img.shields.io/badge/RabbitMQ-4-FF6600?logo=rabbitmq)](https://www.rabbitmq.com/)

O **ShiftFlow** é uma API backend desenvolvida para o gerenciamento de escalas e turnos corporativos, utilizando uma arquitetura de microsserviços.

O projeto tem como objetivo organizar a distribuição de horários entre colaboradores, evitar conflitos de agendamento e estruturar solicitações de troca de turnos, proporcionando maior controle, rastreabilidade e comunicação entre os serviços.

A solução está sendo desenvolvida com Java e Spring Boot, priorizando a separação de responsabilidades, comunicação entre microsserviços, segurança, testes automatizados e práticas de integração e entrega contínuas.

> **Status:** Em desenvolvimento.

---

## Objetivos do projeto

- Centralizar o gerenciamento de escalas e turnos.
- Impedir sobreposições de horários entre colaboradores.
- Controlar solicitações e aprovações de troca de turnos.
- Implementar autenticação e autorização baseada em perfis.
- Utilizar comunicação assíncrona para processamento de eventos.
- Garantir isolamento de dados entre os microsserviços.
- Estruturar uma aplicação preparada para execução em containers e Kubernetes.

---

## Arquitetura

O ShiftFlow utiliza uma arquitetura de microsserviços organizada em três serviços independentes, cada um responsável por uma parte específica do domínio.

### Auth Service

Responsável pela autenticação e gerenciamento dos colaboradores.

**Principais responsabilidades:**
- Cadastro de colaboradores.
- Autenticação utilizando JWT.
- Gerenciamento de permissões e perfis.
- Controle de usuários ativos.

**Porta:** `8081`  
**Banco de dados:** `auth_db`

### Schedule Service

Responsável pelo gerenciamento das escalas e pelas principais regras de negócio.

**Principais responsabilidades:**
- Criação, edição e cancelamento de turnos.
- Consulta de escalas.
- Validação de conflitos de horários.
- Solicitações de troca de turnos.
- Aprovação de trocas por administradores.
- Publicação de eventos no RabbitMQ.

**Porta:** `8082`  
**Banco de dados:** `schedule_db`

### Notification Service

Responsável pelo processamento assíncrono dos eventos publicados pelos demais serviços.

**Principais responsabilidades:**
- Consumo de mensagens do RabbitMQ.
- Processamento de eventos relacionados às escalas.
- Registro estruturado de notificações e auditoria.

**Porta:** `8083`  
**Fila:** `shift.events`

---

## Tecnologias utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java 25 | Linguagem principal |
| Spring Boot 4.1.2 | Desenvolvimento dos microsserviços |
| Spring Web MVC | Construção das APIs REST |
| Spring Security | Segurança e autorização |
| Spring Data JPA | Persistência de dados |
| Spring Validation | Validação de dados |
| Spring AMQP | Comunicação com RabbitMQ |
| MySQL 8.4 | Bancos de dados |
| Flyway | Versionamento do banco |
| RabbitMQ 4 | Mensageria assíncrona |
| Maven | Gerenciamento e construção do projeto |
| JUnit 5 e Mockito | Testes automatizados |
| Docker e Docker Compose | Containerização e ambiente local |
| GitHub Actions e Jenkins | Integração e entrega contínuas |
| Kubernetes | Orquestração de containers |

---

## Estrutura do projeto

```text
ShiftFlow/
│
├── auth-service/
│   ├── src/
│   └── pom.xml
│
├── schedule-service/
│   ├── src/
│   └── pom.xml
│
├── notification-service/
│   ├── src/
│   └── pom.xml
│
├── k8s/
│
├── .github/
│   └── workflows/
│
├── compose.yaml
├── .env.example
├── .gitignore
├── Jenkinsfile
├── pom.xml
└── README.md
```

---

## Configuração e execução local

### Pré-requisitos

Antes de executar o projeto, tenha instalado:

- JDK 25 ou superior.
- Apache Maven.
- Docker Desktop.
- Docker Compose.

### 1. Clone o repositório

```bash
git clone https://github.com/GustavoJ-dev/ShiftFlow.git

cd ShiftFlow
```

### 2. Configure as variáveis de ambiente

Crie seu arquivo `.env` com base no exemplo disponibilizado:

```bash
cp .env.example .env
```

Configure as credenciais necessárias para os bancos MySQL e RabbitMQ.

**Importante:** o arquivo `.env` contém informações locais e não deve ser enviado ao repositório.

### 3. Inicialize a infraestrutura

```bash
docker compose up -d
```

O ambiente Docker será responsável pelos serviços de infraestrutura definidos no `compose.yaml`.

### 4. Compile o projeto

Na raiz do repositório, execute:

```bash
mvn clean verify
```

Esse comando realiza a limpeza, compilação e execução dos testes dos módulos Maven.

> **Nota:** a integração completa dos três microsserviços ao Docker Compose está em desenvolvimento.

---

## Testes

O projeto utiliza JUnit 5 e Mockito para validar as regras de negócio e os comportamentos dos microsserviços.

A estratégia de testes contempla:

- Regras de sobreposição de horários.
- Fluxo de solicitação e aprovação de trocas.
- Controle de permissões.
- Validação de dados.
- Comportamento dos componentes de cada serviço.

Execução dos testes:

```bash
mvn test
```

---

## Roadmap

- [x] Estrutura inicial do monorepo Maven.
- [x] Criação dos módulos de microsserviços.
- [x] Configuração inicial das dependências.
- [x] Configuração dos perfis de ambiente.
- [x] Estrutura inicial do Docker Compose.
- [ ] Implementação da autenticação e geração de JWT.
- [ ] Implementação do gerenciamento de colaboradores.
- [ ] Implementação do gerenciamento de escalas.
- [ ] Implementação das regras de conflitos de horários.
- [ ] Implementação do fluxo de troca de turnos.
- [ ] Integração com RabbitMQ.
- [ ] Implementação do processamento de notificações.
- [ ] Testes automatizados das regras de negócio.
- [ ] Documentação das APIs com Swagger.
- [ ] Pipeline CI/CD com GitHub Actions e Jenkins.
- [ ] Construção e publicação das imagens Docker.
- [ ] Manifestos Kubernetes e validação local.

---

**Desenvolvido por Gustavo de Jesus Silva**

[GitHub](https://github.com/GustavoJ-dev) · [Portfólio](https://gustavoj.com.br)