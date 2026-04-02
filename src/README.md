# CRUD de Usuários (Em Memória) - Java

Aplicação simples de CRUD desenvolvida em Java com o objetivo de praticar conceitos fundamentais de backend, como arquitetura em camadas, separação de responsabilidades e validação de regras de negócio.

---

## Objetivo
A proposta é simular a estrutura de um backend real **sem uso de frameworks**, priorizando organização de código, clareza e boas práticas.

---

## Arquitetura

A aplicação segue o padrão de **arquitetura em camadas**, com responsabilidades bem definidas:

- **Controller**: recebe as requisições da interface e delega para o service
- **Service**: contém as regras de negócio e validações
- **Repository**: abstrai o acesso aos dados
- **Model**: representa a entidade de domínio (User)
- **Console (UI)**: responsável pela interação com o usuário

## Fluxo de dependência:
UI → Controller → Service → Repository


As dependências são injetadas manualmente, simulando o conceito de **Injeção de Dependência**, amplamente utilizado em frameworks como o Spring.

---

## Conceitos Aplicados

- Arquitetura em camadas (Controller, Service, Repository)
- Separação de responsabilidades (Separation of Concerns)
- Injeção de dependência (manual)
- Uso de interfaces para abstração
- Tratamento de exceções customizadas
- Validação de dados de entrada
- Persistência em memória

---

## Regras de Negócio

- O email do usuário deve ser único
- Não é possível atualizar ou deletar usuários inexistentes
- Validação de nome, email e idade
- Tratamento explícito de erros via exceções

---

## Tratamento de Exceções

O projeto utiliza exceções customizadas para tornar o fluxo mais claro e evitar retornos ambíguos:

- `EmailAlreadyExistsException`
- `UserNotFoundException`

Essa abordagem melhora a legibilidade e aproxima o código de cenários reais de backend.

---

## Limitações

- Os dados são armazenados apenas em memória (não persistem após execução)
- Não há integração com banco de dados
- Interface baseada em console
- Ainda não possui testes automatizados

---

## Próximos Passos

Evoluções planejadas para aproximar o projeto de um ambiente backend real:

- Integração com banco de dados (PostgreSQL)
- Uso de JDBC ou JPA
- Criação de API REST com Spring Boot
- Implementação de testes unitários (JUnit)
- Melhor modularização da camada de interface

---

## Como Executar

1. Clone o repositório
2. Abra em uma IDE Java (IntelliJ, Eclipse, etc.)
3. Execute a classe `Main`

**Requisitos:**
- Java 17 ou superior

---

## Observação

Este projeto tem como foco o aprendizado de fundamentos. A estrutura adotada já segue princípios utilizados em aplicações com Spring, facilitando a evolução para frameworks mais robustos.

---

**Desenvolvido por Yasmin Hirt**
