# Foodlivery API

API REST desenvolvida em Spring Boot para gerenciamento de usuários de um sistema de food delivery.
O projeto implementa operações CRUD, autenticação de usuário e atualização de senha, seguindo boas práticas de arquitetura (DTO, Service Layer, tratamento global de exceções) e containerização com Docker.

- Construído para fins meramente acadêmicos

## 🚀 Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker / Docker Compose
- Maven
- Bean Validation

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas inspirada em DDD:

- **Controller** → Camada de entrada (API REST)
- **Service** → Regras de negócio
- **Repository** → Acesso a dados
- **DTOs** → Transferência de dados
- **Mapper** → Conversão entre DTO e entidade
- **Exceptions** → Tratamento global de erros
- **Security** → Autenticação e criptografia de senha

## 📁 Estrutura

src/main/java/br/com/fiap/foodlivery

- controllers/
- services/
- repositories/
- dtos/
- entities/
- mappers/
- security/
- exceptions/
- config/

## 📡 Endpoints

### 🔹 Usuários

- `GET /usuarios` → listar usuários
- `GET /usuarios/{id}` → buscar por ID
- `POST /usuarios` → criar usuário
- `PUT /usuarios/{id}` → atualizar usuário
- `DELETE /usuarios/{id}` → deletar usuário

### 🔹 Segurança

- `POST /auth/login` → autenticação
- `PUT /usuarios/{id}/password` → atualizar senha

## 🔐 Segurança

- Senhas criptografadas com BCrypt
- Validação de credenciais no login
- Atualização de senha com verificação de senha atual

## ⚠️ Tratamento de Erros

O projeto utiliza um handler global para tratar:

- Recurso não encontrado (404)
- Erros de validação (400)
- Erros de negócio
- Erros de integridade de banco

## 🗃️ Banco de Dados

- PostgreSQL
- Uso de volumes Docker para persistência

### ⚠️ Inicialização de dados

O projeto utiliza um script `data.sql` para popular o banco **apenas em ambiente de desenvolvimento/teste**.

Em produção:

- A execução automática de scripts está desabilitada

Configuração:

- `application-dev.properties` → `spring.sql.init.mode=always`
- `application-prod.properties` → `spring.sql.init.mode=never`

## 🧠 Melhorias futuras

- Implementação de JWT
- Refatoração para DDD mais puro
- Testes automatizados

## 🐳 Rodando com Docker

### 1. Clonar o repositório

```bash
git clone https://github.com/conradrenno/foodlivery
cd foodlivery
```
### 2. Criar arquivo .env
```
POSTGRES_DB=usuarios_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
```
### 3. Subir a aplicação
```
docker-compose up --build
```
### 4. Parar a aplicação
```
docker-compose down
```
## 🧪 Rodando localmente (sem Docker)

### 1. Pré-requisitos

- Java 21 instalado
- Maven instalado
- PostgreSQL rodando

### 2. Configurar banco de dados

Crie um banco PostgreSQL:

```sql
CREATE DATABASE usuarios_db;
```

### 3. Configurar as variáveis de ambiente (.env)

```
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/usuarios_db
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=postgres
```

### 3. Rodar a aplicação
```
mvn clean install
mvn spring-boot:run
```
### OU executar o .jar:
```
java -jar target/foodlivery-0.0.1-SNAPSHOT.jar
```
