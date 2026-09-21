# MedSchedule

API REST para gerenciamento de uma clínica médica: cadastro de pacientes, médicos (usuários), especialidades e agendamento de consultas.

Projeto desenvolvido na **Avaliação Parcial 1 — Estruturação Inicial da API e Persistência**: configuração do projeto, conexão com o PostgreSQL, mapeamento objeto-relacional (ORM) e CRUD completo via HTTP.

## Tecnologias

- Java 17
- Spring Boot 4.1.0 (Spring Web, Spring Data JPA, Spring Security)
- Hibernate (ORM)
- PostgreSQL
- Swagger UI (springdoc-openapi) para documentar e testar a API
- Maven

## Pré-requisitos

- JDK 17 ou superior
- PostgreSQL instalado e em execução (porta padrão `5432`)
- Maven (ou o `mvnw` que já acompanha o projeto)

## Configuração do banco de dados

1. Crie o banco no PostgreSQL:

   ```sql
   CREATE DATABASE medschedule;
   ```

2. A conexão está configurada em `src/main/resources/application.properties` (host `localhost`, porta `5432`, banco `medschedule`, usuário `postgres`).

3. A senha do banco **não fica no repositório**. Crie o arquivo `src/main/resources/application-local.properties` (ele é ignorado pelo Git) com o conteúdo:

   ```properties
   spring.datasource.password=SUA_SENHA_DO_POSTGRES
   ```

4. Não é preciso criar as tabelas: o Hibernate as cria automaticamente ao iniciar a aplicação (`spring.jpa.hibernate.ddl-auto=update`).

## Como executar

Pela IDE (IntelliJ): abra a classe `MedScheduleApplication` e execute o método `main`.

Ou pelo terminal, na pasta do projeto:

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.

## Documentação e testes (Swagger)

Com a aplicação em execução, acesse:

**http://localhost:8080/swagger-ui.html**

Nesta fase a API não exige autenticação, então é possível testar todas as rotas direto pelo Swagger ou pelo Postman.

## Endpoints

| Verbo | Rota | Descrição | Retorno |
|---|---|---|---|
| POST | `/pacientes` | Cadastra um novo paciente | 201 Created |
| GET | `/pacientes` | Lista todos os pacientes | 200 OK |
| GET | `/pacientes/{id}` | Busca um paciente pelo id | 200 OK / 404 |
| PUT | `/pacientes/{id}` | Atualiza os dados do paciente | 200 OK / 404 |
| PATCH | `/pacientes/{id}/status` | Atualiza somente o status | 200 OK / 404 |
| DELETE | `/pacientes/{id}` | Remove o paciente do banco | 200 OK / 404 |

As mesmas rotas (POST, GET, GET por id, PUT, PATCH `/status` e DELETE) existem para:

- `/usuarios` — médicos e usuários do sistema
- `/especialidades` — especialidades médicas
- `/consultas` — agendamentos (também há `GET /consultas/medico/{medicoId}`)

Há ainda `POST /login`, que valida CPF e senha de um usuário cadastrado.

### Exemplos de JSON

Cadastrar paciente (`POST /pacientes`):

```json
{
  "nome": "Maria Silva",
  "cpf": "12345678900",
  "telefone": "11999999999",
  "dataNascimento": "2000-01-01"
}
```

Atualizar status (`PATCH /pacientes/1/status`):

```json
{
  "status": "I"
}
```

Agendar consulta (`POST /consultas`):

```json
{
  "pacienteId": 1,
  "medicoId": 1,
  "especialidadeId": 1,
  "dataHora": "2026-10-01T10:00"
}
```

## Estrutura do projeto

```
src/main/java/org/example/medschedule
├── MedScheduleApplication.java   # ponto de entrada da aplicação
├── config/                       # configuração de segurança
├── controllers/                  # rotas HTTP (camada web)
├── dto/                          # objetos de entrada e saída da API (Request/Response)
├── entities/                     # classes mapeadas para tabelas (ORM)
└── repositories/                 # acesso ao banco (Spring Data JPA)
```

## Observações

- As entidades (`Paciente`, `Usuario`, `Especialidade` e `Consulta`) são isoladas: não há relacionamentos entre tabelas nesta fase. A consulta guarda apenas os ids de paciente, médico e especialidade.
- O `DELETE` remove o registro definitivamente do banco de dados.
- A segurança (autenticação e autorização) será implementada em uma fase futura.
