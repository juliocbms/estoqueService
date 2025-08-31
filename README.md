# Microsserviço de Gestão de Estoque

Este microserviço é responsável por gerenciar o catálogo de produtos, controlar o estoque e fornecer informações de disponibilidade para outros serviços. Seu design é focado em alta resiliência, utilizando um padrão de comunicação assíncrona para não depender da disponibilidade imediata de outros serviços.

### Tecnologias Utilizadas

-   **Linguagem:** Java 21
-   **Framework:** Spring Boot 3
-   **Segurança:** Spring Security e JWT (JSON Web Tokens)
-   **Persistência:** Spring Data JPA
-   **Banco de Dados:** PostgreSQL (via Docker)
-   **Mensageria:** RabbitMQ (para comunicação assíncrona)
-   **Ferramenta de Build:** Maven

## Funcionalidades (Endpoints)

| Método | Endpoint | Descrição | Restrição de Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/products` | Cadastra um novo produto. | Somente ADMIN |
| `GET` | `/products` | Lista todos os produtos disponíveis. | Público |
| `GET` | `/products/{id}` | Busca um produto específico por ID. | Público |
| `PUT` | `/products/{id}` | Atualiza um produto existente. | Somente ADMIN |
| `DELETE`| `/products/{id}` | Deleta um produto. | Somente ADMIN |
| `GET` | `/products/{id}/price` | Retorna o preço de um produto. | Somente Serviço |
| `GET` | `/products/{id}/available?quantidade={val}`| Verifica a disponibilidade de um produto no estoque. | Somente Serviço |


### Detalhes da Implementação

#### Comunicação Assíncrona com RabbitMQ
A comunicação para a **atualização do estoque** é totalmente assíncrona. Em vez de receber chamadas síncronas de outros serviços, este microserviço atua como um **consumidor de eventos**. Quando uma venda é concluída em outro serviço, um evento é publicado no RabbitMQ. O serviço de Estoque consome este evento para processar a redução da quantidade de produtos. Essa abordagem evita gargalos e falhas em cascata.

#### Persistência de Dados
O banco de dados, uma instância do **PostgreSQL**, é executado em um container **Docker**. Isso garante o **isolamento de dados**, com o serviço de Estoque sendo o único a ter acesso e controle sobre suas próprias tabelas.

### Como Rodar o Projeto

#### Requisitos do Sistema
-   Java 21
-   Maven 3.
-   Docker e Docker Compose
-   RabbitMQ (Cloud AMQP)

#### 1. Clonar o Repositório
```bash
git clone [https://github.com/juliocbms/estoqueService.git](https://github.com/juliocbms/estoqueService.git)
cd estoqueService
