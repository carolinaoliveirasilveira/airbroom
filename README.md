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
        "name": "John Doe",
        "email": "johndoe@example.com",
        "phone": "123456789"
      }
      ```
    - **Response**:
      ```json
      {
        "id": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
        "name": "John Doe",
        "email": "johndoe@example.com",
        "phone": "123456789"
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
        "title": "Room with ocean view",
        "description": "Spacious and comfortable room.",
        "price": 150.0
      }
      ```
    - **Response**:
      ```json
      {
        "id": "a12b34c56d78e90f",
        "title": "Room with ocean view",
        "description": "Spacious and comfortable room.",
        "price": 150.0
      }
      ```

- **`GET /publicity/{id}/search`**
    - **Descrição**: Retorna os detalhes de um anúncio pelo ID.
    - **Path Parameter**:
        - `id` (Long): O identificador único do anúncio.
    - **Response**:
      ```json
      {
        "id": 1,
        "title": "Room with ocean view",
        "description": "Spacious and comfortable room.",
        "price": 150.0
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
        "clientName": "Alice Brown",
        "checkInDate": "2023-05-01",
        "checkOutDate": "2023-05-10"
      }
      ```
    - **Response**:
      ```json
      {
        "id": 1,
        "clientName": "Alice Brown",
        "checkInDate": "2023-05-01",
        "checkOutDate": "2023-05-10"
      }
      ```

- **`GET /reservations/all`**
    - **Descrição**: Retorna uma lista de todas as reservas.
    - **Response**:
      ```json
      [
        {
          "id": 1,
          "clientName": "Alice Brown",
          "checkInDate": "2023-05-01",
          "checkOutDate": "2023-05-10"
        },
        {
          "id": 2,
          "clientName": "Bob Smith",
          "checkInDate": "2023-05-15",
          "checkOutDate": "2023-05-20"
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
        "id": 1,
        "clientName": "Alice Brown",
        "checkInDate": "2023-05-01",
        "checkOutDate": "2023-05-10"
      }
      ```

- **`PUT /reservations/update/{id}`**
    - **Descrição**: Atualiza as informações de uma reserva existente.
    - **Path Parameter**:
        - `id` (Long): O identificador único da reserva.
    - **Request Body**:
      ```json
      {
        "clientName": "Alice Brown - Updated",
        "checkInDate": "2023-05-02",
        "checkOutDate": "2023-05-11"
      }
      ```
    - **Response**:
      ```json
      {
        "id": 1,
        "clientName": "Alice Brown - Updated",
        "checkInDate": "2023-05-02",
        "checkOutDate": "2023-05-11"
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
          "id": 1,
          "clientName": "Alice Brown",
          "checkInDate": "2023-05-01",
          "checkOutDate": "2023-05-10"
        }
      ]
      ```


