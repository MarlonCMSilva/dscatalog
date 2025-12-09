# DS Catalog: API RESTful com Spring Boot 3 e Estratégia de Testes Robusta

## 🚀 Visão Geral do Projeto

O **DS Catalog** é uma aplicação *backend* desenvolvida em Java com o framework Spring Boot, que simula um sistema de catálogo de produtos e categorias. O projeto foi concebido com uma arquitetura RESTful e segue o padrão de desenvolvimento em camadas (Controller, Service, Repository), com um foco especial na **qualidade de código e na cobertura de testes**.

Este repositório serve como um portfólio para demonstrar proficiência em:
1.  Desenvolvimento de APIs RESTful com Spring Boot 3.
2.  Implementação de uma estratégia de testes abrangente, cobrindo testes de unidade e de integração em todas as camadas críticas da aplicação.
3.  Configuração de ambientes de desenvolvimento e teste com bancos de dados distintos (PostgreSQL e H2).

## 🛡️ Estratégia de Testes: Qualidade e Confiança

A qualidade do software é garantida por uma estratégia de testes em múltiplas camadas, que assegura o comportamento correto de cada componente, desde a lógica de negócio até a comunicação com o banco de dados e a exposição dos endpoints REST.

| Tipo de Teste | Camada Testada | Ferramentas | Objetivo Principal |
| :--- | :--- | :--- | :--- |
| **Testes de Unidade (Service)** | Lógica de Negócio (Service) | JUnit 5, Mockito | Verificar o comportamento isolado das regras de negócio, simulando (mocking) as dependências (Repositories). |
| **Testes de Integração (Service - IT)** | Service + Repository + DB (H2) | `@SpringBootTest`, `@Transactional` | Garantir que a lógica de negócio interage corretamente com a camada de persistência e o banco de dados (em memória). |
| **Testes de Unidade (Resource - WebMvcTest)** | Controller (Resource) | `@WebMvcTest`, Mockito, MockMvc | Testar o mapeamento de requisições HTTP, a serialização/desserialização de JSON e o tratamento de erros, isolando a camada de Serviço. |
| **Testes de Integração (Resource - IT)** | Aplicação Completa (End-to-End) | `@SpringBootTest`, MockMvc | Simular requisições HTTP reais, testando o fluxo completo da aplicação, incluindo a persistência no banco de dados (H2). |

### Detalhes da Implementação de Testes

*   **Isolamento e Mocking:** Nos testes de unidade (`ProductServiceTests.java` e `ProductResourcesTests.java`), o **Mockito** é utilizado para simular o comportamento das dependências, garantindo que o componente em teste seja avaliado de forma isolada.
*   **Testes de Exceção:** Foram implementados testes para garantir o tratamento adequado de exceções, como `ResourcesNotFoundException` (HTTP 404) e `DatabaseException` (em casos de violação de integridade referencial).
*   **Massa de Dados:** O arquivo `import.sql` é utilizado para popular o banco de dados H2 em memória com dados iniciais, garantindo um ambiente de teste consistente e replicável.

## 💻 Tecnologias Utilizadas

O projeto é construído com tecnologias modernas e amplamente utilizadas no mercado:

*   **Linguagem:** Java 21
*   **Framework:** Spring Boot 3.3.5
*   **Persistência:** Spring Data JPA
*   **Banco de Dados:** PostgreSQL (Produção) e H2 (Testes)
*   **Build Tool:** Maven
*   **Testes:** JUnit 5, Mockito, Spring Boot Test, MockMvc
*   **Padrão de Projeto:** RESTful API

## ⚙️ Funcionalidades da API REST

A API expõe endpoints para a gestão de produtos e categorias:

| Recurso | Método HTTP | Endpoint | Descrição |
| :--- | :--- | :--- | :--- |
| Produtos | `GET` | `/products` | Retorna uma lista paginada de produtos. |
| Produtos | `GET` | `/products/{id}` | Retorna um produto específico por ID. |
| Produtos | `POST` | `/products` | Cria um novo produto. |
| Produtos | `PUT` | `/products/{id}` | Atualiza um produto existente. |
| Produtos | `DELETE` | `/products/{id}` | Exclui um produto por ID. |
| Categorias | `GET` | `/categories` | Retorna uma lista paginada de categorias. |
| Categorias | `GET` | `/categories/{id}` | Retorna uma categoria específica por ID. |

## 🛠️ Configuração e Execução do Projeto

Para executar o projeto localmente, siga os passos abaixo:

### Pré-requisitos

*   Java Development Kit (JDK) 21 ou superior.
*   Maven.
*   Um servidor de banco de dados PostgreSQL.

### 1. Configuração do Banco de Dados

O projeto utiliza o PostgreSQL. Você deve criar um banco de dados e configurar as credenciais de acesso no arquivo `application.properties`.

1.  Crie um banco de dados chamado `dscatalog`.
2.  Edite o arquivo `dscatalog/backend/src/main/resources/application.properties` para incluir as configurações do seu banco de dados.

**Exemplo de `application.properties` (para PostgreSQL):**

```properties
spring.application.name=dscatalog
spring.profiles.active=dev # Altere para 'dev' para usar o PostgreSQL
spring.jpa.open-in-view=false

# Configurações do PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/dscatalog
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driverClassName=org.postgresql.Driver

# Configurações JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 2. Execução

1.  Navegue até o diretório raiz do backend:
    ```bash
    cd dscatalog/backend
    ```
2.  Compile e execute a aplicação usando o Maven:
    ```bash
    mvn spring-boot:run
    ```

A aplicação estará acessível em `http://localhost:8080`.

### 3. Execução dos Testes

Para rodar todos os testes (unidade e integração), utilize o comando Maven:

```bash
cd dscatalog/backend
mvn test
```

Os testes de integração utilizarão o perfil `test` e o banco de dados H2 em memória, garantindo que o banco de dados de desenvolvimento/produção não seja afetado.

---

*Este README foi gerado por Manus AI com base na análise do código-fonte do projeto.*
