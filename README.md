# StudentAddress API

API REST desenvolvida com Spring Boot para gerenciamento de cadastro de estudantes, criada para fins acadêmicos na UNASP.

## Tecnologias

- Java 21
- Spring Boot 3.4.3
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Pré-requisitos

- Java 21 instalado
- PostgreSQL rodando localmente
- Maven instalado

## Configuração do banco de dados

Crie o banco de dados no PostgreSQL:
```sql
CREATE DATABASE studantaddress;
```

Crie a tabela de estudantes:
```sql
CREATE TABLE IF NOT EXISTS students (
    id          BIGSERIAL       PRIMARY KEY,
    nome        VARCHAR(150)    NOT NULL,
    ddd         VARCHAR(2)      NOT NULL,
    telefone    VARCHAR(9)      NOT NULL,
    email       VARCHAR(150)    NOT NULL UNIQUE,
    cep         VARCHAR(8),
    logradouro  VARCHAR(200),
    bairro      VARCHAR(100),
    cidade      VARCHAR(100),
    uf          VARCHAR(2),
    regiao      VARCHAR(50)
);
```

## Configuração da aplicação

Edite o arquivo `src/main/resources/application.properties` com suas credenciais:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/studantaddress
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## Como rodar
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/students` | Lista todos os estudantes |
| GET | `/students/{id}` | Busca estudante por ID |
| POST | `/students` | Cadastra novo estudante |
| PUT | `/students/{id}` | Atualiza estudante |
| DELETE | `/students/{id}` | Remove estudante |

## Exemplo de payload
```json
{
  "nome": "João Silva",
  "ddd": "45",
  "telefone": "991234567",
  "email": "joao.silva@email.com",
  "cep": "85825000",
  "logradouro": "Rua das Flores",
  "bairro": "Centro",
  "cidade": "Santa Tereza do Oeste",
  "uf": "PR",
  "regiao": "Sul"
}
```
