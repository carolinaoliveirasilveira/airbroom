# airbroom

O projeto **airbroom** é uma plataforma especializada no gerenciamento e aluguel de quartos, desenvolvida com foco em eficiência, escalabilidade e aderência às melhores práticas de desenvolvimento de software. Utilizando o ecossistema **Spring**, a aplicação oferece APIs RESTful robustas, facilitando a criação, gestão e consulta de anunciantes, anúncios e reservas de forma ágil e confiável.
# Visão geral

Esta aplicação back-end utiliza os frameworks [Spring Boot](https://spring.io/projects/spring-boot), [Spring Data JPA](https://spring.io/projects/spring-data-jpa) e [Spring Batch](https://spring.io/projects/spring-batch). Ela oferece APIs RESTful para gerenciamento de quartos e reservas, com persistência no banco de dados MySQL.

## Tecnologias

- **[Spring Boot](https://spring.io/projects/spring-boot):** Facilita o desenvolvimento de aplicações Java com configuração automatizada e servidor embutido.
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa):** Abstração para operações de banco de dados utilizando JPA.
- **[Spring Batch](https://spring.io/projects/spring-batch):** Framework para processamento em lote com suporte a transações e paralelismo.
- **Banco de dados:** MySQL 8.0 para persistência de dados.
- **Docker Compose:** Utilizado para configurar rapidamente ambientes de desenvolvimento.

# Setup da aplicação (local)

### Pré-requisitos

Certifique-se de que os seguintes softwares estejam instalados:

- **Java 17**: Para executar o projeto.
- **MySQL 8.0**: Banco de dados para a aplicação.
- **Maven 3.8+**: Gerenciador de dependências e build.

### Configuração do banco de dados

Crie o banco de dados no MySQL com as seguintes credenciais (conforme o `application.properties`):

```sql
CREATE DATABASE airbroom;
```

## Instalação da aplicação

1. Primeiramente, faça o clone do repositório:
   ```bash
   git clone https://github.com/carolinaoliveirasilveira/airbroom
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd airbroom
   ```
3. Compile o código e baixe as dependências do projeto:
   ```bash
   mvn clean package
   ```
4. Inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```

A aplicação estará disponível em [http://localhost:8081](http://localhost:8081).

Ao iniciar, você verá logs semelhantes a este:
```
Tomcat started on port(s): 8081 (http)
Started AppConfig in xxxx seconds (JVM running for xxxx)
```

---

# Setup da aplicação com Docker

## Pré-requisitos

Certifique-se de que as seguintes dependências estejam instaladas:

```
Java 8
Docker 17.06.0
Maven 3.3.3
```

## Preparando o ambiente

1. Suba os serviços de banco de dados utilizando o arquivo `docker-compose.yml`:
   ```bash
   docker-compose up -d
   ```

2. Construa a imagem Docker da aplicação:
   ```bash
   mvn clean package -Dmaven.test.skip=true spring-boot:build-image
   ```

3. Execute a aplicação utilizando Docker:
   ```bash
   docker run -p 8081:8081 --name airbroom-app airbroom-app
   ```

A aplicação estará disponível em [http://localhost:8081](http://localhost:8081).

# APIs Disponíveis

O projeto disponibiliza algumas APIs organizadas em três contextos principais: **Advertisers**, **Publicity**, e **Reservations**. Todas as APIs seguem o padrão REST, produzindo e consumindo dados no formato JSON.

## **Advertisers**

- **`POST /advertisers/create`**
    - **Descrição**: Cria um novo anunciante.
    - **Request Body**:
      ```json
      {
        "name": "Carolina Oliveira Silveira",
        "cpfOrCnpj": "000000000",
        "active": true,
        "contacts": [
          {
            "typeContacts": "EMAIL",
            "descriptionContacts": "carolinaos@example.com"
          }
        ],
        "addresses": [
          {
            "city": "Palhoça",
            "state": "SC",
            "country": "Brasil"
          }
        ]
      }
      ```
    - **Response**:
      ```json
      {
        "id": "766fc3a1-d083-468c-8bb3-86cece71c4d6",
        "name": "Carolina Oliveira Silveira",
        "cpfOrCnpj": "01944147012",
        "active": true,
        "contacts": [
          {
            "typeContacts": "EMAIL",
            "descriptionContacts": "carolinaos@example.com"
          }
        ],
        "addresses": [
          {
            "city": "Palhoça",
            "state": "SC",
            "country": "Brasil"
          }
        ]
      }
      ```

- **`DELETE /advertisers/{id}/delete`**
    - **Descrição**: Exclui um anunciante pelo ID.
    - **Path Parameter**:
        - `id` (UUID): O identificador único do anunciante.
    - **Response**:
        - Retorna `true` se o anunciante for excluído com sucesso.

---

## **Publicity**

- **`POST /publicity/{idAdvertiser}/create`**
    - **Descrição**: Cria um novo anúncio vinculado a um anunciante.
    - **Path Parameter**:
        - `idAdvertiser` (UUID): O ID do anunciante.
    - **Request Body**:
      ```json
      {
        "location": "Rio de Janeiro",
        "size": 45,
        "furnitureAvailable": [
          "Cama casal",
          "guarda roupa"
        ],
        "amountPeople": 4,
        "checkin": "2025-01-05",
        "checkout": "2025-01-20",
        "acceptsPets": true,
        "acceptschildren": true,
        "accessibility": "Rampa de acesso",
        "idAdvertiser": "7848838f-783d-40f9-bae7-78ebad942e2d",
        "photos": [],
        "value": 2000.0,
        "title": "Cobertura Duplex Cabo Frio - Praia das Dunas",
        "description": "Espaço inteiro: apartamento em Braga, Brasil"
      }
      ```
    - **Response**:
      ```json
      {
        "id": 15,
        "location": "Rio de Janeiro",
        "size": 45.0,
        "furnitureAvailable": [
          "Cama casal",
          "guarda roupa"
        ],
        "amountPeople": 4,
        "checkin": "2025-01-05T00:00:00.000+00:00",
        "checkout": "2025-01-20T00:00:00.000+00:00",
        "acceptsPets": true,
        "acceptschildren": true,
        "accessibility": "Rampa de acesso",
        "idAdvertiser": "7848838f-783d-40f9-bae7-78ebad942e2d",
        "photos": [],
        "value": 2000.0,
        "title": "Espaço inteiro: apartamento em Braga, Brasil",
        "description": "Cobertura Duplex Cabo Frio - Praia das Dunas"
      }
      ```

- **`GET /publicity/{id}/search`**
    - **Descrição**: Retorna os detalhes de um anúncio pelo ID.
    - **Path Parameter**:
        - `id` (Long): O identificador único do anúncio.
    - **Response**:
      ```json
      {
        "id": 13,
        "location": "Rio de Janeiro",
        "size": 45.0,
        "furnitureAvailable": [
          "Cama casal",
          "guarda roupa"
        ],
        "amountPeople": 4,
        "checkin": "2025-01-05T00:00:00.000+00:00",
        "checkout": "2025-01-20T00:00:00.000+00:00",
        "acceptsPets": true,
        "acceptschildren": true,
        "accessibility": "Rampa de acesso",
        "idAdvertiser": "7848838f-783d-40f9-bae7-78ebad942e2d",
        "photos": [],
        "value": 2000.0,
        "title": "Cobertura Duplex Cabo Frio - Praia das Dunas",
        "description": "Espaço inteiro: apartamento em Braga, Brasil",
        "reservations": []
      }
      ```

- **`DELETE /publicity/{id}/delete`**
    - **Descrição**: Exclui um anúncio pelo ID.
    - **Path Parameter**:
        - `id` (Long): O identificador único do anúncio.
    - **Response**:
        - Retorna uma mensagem de sucesso ou status 404 se o anúncio não for encontrado.

---

## **Reservations**

- **`POST /reservations/create/{publicityId}`**
    - **Descrição**: Cria uma nova reserva vinculada a um anúncio.
    - **Path Parameter**:
        - `publicityId` (Long): O ID do anúncio.
    - **Request Body**:
      ```json
      {
        "checkin": "2025-03-13",
        "checkout": "2025-03-15",
        "dailyValue": 100.00,
        "totalValue": 500.00,
        "paymentMethod": "Cartão de Crédito",
        "advancePayment": true,
        "title": "Cobertura Duplex Cabo Frio - Praia das Dunas",
        "description": "Espaço inteiro: apartamento em Braga, Brasil "
      }
      ```
    - **Response**:
      ```json
      {
        "idReservation": 0,
        "checkin": "2025-03-13T00:00:00.000+00:00",
        "checkout": "2025-03-15T00:00:00.000+00:00",
        "dailyValue": 100.00,
        "totalValue": 0,
        "paymentMethod": "",
        "advancePayment": false,
        "mensage": "As datas selecionadas já estão ocupadas."
      }
      ```

- **`GET /reservations/all`**
    - **Descrição**: Retorna uma lista de todas as reservas.
    - **Response**:
      ```json
      [
        {
          "idReservation": 20,
          "checkin": "2025-01-16T00:00:00.000+00:00",
          "checkout": "2025-01-30T00:00:00.000+00:00",
          "dailyValue": 100.00,
          "totalValue": 1400.00,
          "paymentMethod": "Cartão de Crédito",
          "advancePayment": true
        },
        {
          "idReservation": 21,
          "checkin": "2025-03-01T00:00:00.000+00:00",
          "checkout": "2025-03-05T00:00:00.000+00:00",
          "dailyValue": 100.00,
          "totalValue": 400.00,
          "paymentMethod": "Cartão de Crédito",
          "advancePayment": true
        }
      ]
      ```

- **`GET /reservations/{id}`**
    - **Descrição**: Retorna os detalhes de uma reserva pelo ID.
    - **Path Parameter**:
        - `id` (Long): O identificador único da reserva.
    - **Response**:
      ```json
      {
        "idReservation": 21,
        "checkin": "2025-03-01T00:00:00.000+00:00",
        "checkout": "2025-03-05T00:00:00.000+00:00",
        "dailyValue": 100.00,
        "totalValue": 400.00,
        "paymentMethod": "Cartão de Crédito",
        "advancePayment": true
      }
      ```

- **`PUT /reservations/update/{id}`**
    - **Descrição**: Atualiza as informações de uma reserva existente.
    - **Path Parameter**:
        - `id` (Long): O identificador único da reserva.
    - **Request Body**:
      ```json
      {
        "checkin": "2024-12-20T10:00:00",
        "checkout": "2024-12-25T10:00:00",
        "dailyValue": 150.00,
        "totalValue": 750.00,
        "paymentMethod": "Cartão de Crédito",
        "advancePayment": true,
        "publicityId": 1
      }
      ```
    - **Response**:
      ```json
      {
        "idReservation": 19,
        "checkin": "2024-12-20T10:00:00.000+00:00",
        "checkout": "2024-12-25T10:00:00.000+00:00",
        "dailyValue": 150.00,
        "totalValue": 750.00,
        "paymentMethod": "Cartão de Crédito",
        "advancePayment": true
      }
      ```

- **`DELETE /reservations/delete/{id}`**
    - **Descrição**: Exclui uma reserva pelo ID.
    - **Path Parameter**:
        - `id` (Long): O identificador único da reserva.
    - **Response**:
        - Retorna uma mensagem de sucesso.

- **`GET /reservations/publicity/{publicityId}`**
    - **Descrição**: Retorna todas as reservas associadas a um anúncio específico.
    - **Path Parameter**:
        - `publicityId` (Long): O ID do anúncio.
    - **Response**:
      ```json
      [
        {
          "reservation": {
            "idReservation": 21,
            "checkin": "2025-03-01T00:00:00.000+00:00",
            "checkout": "2025-03-05T00:00:00.000+00:00",
            "dailyValue": 100.00,
            "totalValue": 400.00,
            "paymentMethod": "Cartão de Crédito",
            "advancePayment": true
          },
          "publicity": {
            "id": "7848838f-783d-40f9-bae7-78ebad942e2d",
            "title": "Cobertura Duplex Cabo Frio - Praia das Dunas",
            "description": "Espaço inteiro: apartamento em Braga, Brasil",
            "location": "Rio de Janeiro"
          }
        }
      ]
      ```