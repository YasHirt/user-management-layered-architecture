# CRUD de Usuários - Java (Evolução: Memória → JDBC)

Aplicação de CRUD desenvolvida em Java com o objetivo de praticar fundamentos de backend, evoluindo de uma versão em memória para uma versão com persistência em banco de dados utilizando JDBC.

---

## Objetivo

Simular a estrutura de um backend real sem uso de frameworks, priorizando:
- organização de código
- separação de responsabilidades
- aplicação de boas práticas

---

##  Status do Projeto

Em desenvolvimento ativo

Atualmente o projeto já possui:
- Arquitetura em camadas (Controller, Service, Repository)
- Persistência em banco de dados (MySQL) via JDBC
- Testes unitários com JUnit
- Tratamento de exceções customizadas
---

## Arquitetura

A aplicação segue o padrão de arquitetura em camadas:

- **Controller**: recebe as interações da interface
- **Service**: contém regras de negócio e validações
- **Repository**: responsável pelo acesso aos dados
- **Model**: representa a entidade de domínio (`User`)
- **Console (UI)**: interação com o usuário

## Fluxo de dependência:


UI → Controller → Service → Repository


O projeto utiliza injeção de dependência manual, simulando o comportamento de frameworks como o Spring.

---

## Persistência de Dados

O projeto evoluiu de uma versão em memória para uma integração com banco de dados utilizando JDBC.

Atualmente:
- Uso de MySQL como banco de dados
- Acesso via `DriverManager`
- Uso de `PreparedStatement`
- Implementação de repositório específico para banco (`UserRepositoryMySql`)

As credenciais são carregadas via arquivo `.env`.

---

## Testes

O projeto possui testes unitários focados na camada de negócio (Service), cobrindo:

- Validação de email único
- Usuário inexistente
- Regras de validação de entrada

Observação:
Atualmente os testes utilizam banco real, sem isolamento completo.

---

## Conceitos Aplicados

- Arquitetura em camadas
- Separation of Concerns
- Injeção de dependência manual
- Uso de interfaces
- JDBC
- PreparedStatement
- Tratamento de exceções customizadas
- Testes unitários com JUnit

---

## Regras de Negócio

- O email do usuário deve ser único
- Não é possível atualizar ou deletar usuários inexistentes
- Validação de nome, email e idade
- Tratamento explícito de erros via exceções

---

## Versões do Projeto

**v1.0.0**
- CRUD em memória
- Arquitetura em camadas
- Sem persistência
- Sem testes

**v1.0.1**
- Adição de testes unitários
- Melhorias na validação e tratamento de exceções

**v1.1.0 (atual)**
- Integração com MySQL via JDBC
- Implementação de repository com banco de dados
- Uso de PreparedStatement

---

## Limitações
- Interface baseada em console
- Sem uso de framework (Spring ainda não aplicado)
- Sem pool de conexões
  
---

## Próximos Passos

- Separar banco de teste e desenvolvimento
- Introduzir Spring Boot
- Criar API REST
- Implementar JPA/Hibernate
- Melhorar cobertura e isolamento de testes
- Implementar pool de conexões

---

## Como Executar

1. Clone o repositório
2. Configure o arquivo `.env` com:


DB_URL=jdbc:mysql://localhost:3306/seu_banco
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha


3. Execute a classe `Main`

---

## Requisitos

- Java 17+
- MySQL
- Maven

---

## Desenvolvedora

Yasmin Hirt
