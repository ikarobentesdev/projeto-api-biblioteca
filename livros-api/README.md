# 📚 API de Gerenciamento de Livros

API REST em Spring Boot para gerenciamento de livros, demonstrando arquitetura em camadas.

---

## 🏗️ Estrutura do Projeto

```
src/main/java/com/biblioteca/
├── Application.java          ← Ponto de entrada (@SpringBootApplication)
├── controller/
│   └── LivroController.java  ← Endpoints REST (@RestController)
├── service/
│   └── LivroService.java     ← Regras de negócio (@Service)
├── repository/
│   └── LivroRepository.java  ← Persistência em memória (@Repository)
└── model/
    └── Livro.java            ← Entidade principal (POJO)
```

---

## ▶️ Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.8+

### Comandos
```bash
# Compilar e iniciar
mvn spring-boot:run

# Ou compilar primeiro e depois rodar
mvn clean package
java -jar target/livros-api-1.0.0.jar
```

A API estará disponível em: `http://localhost:8080`

---

## 🔗 Endpoints REST

| Método | URL                  | Descrição             | Retorno       |
|--------|----------------------|-----------------------|---------------|
| POST   | `/api/livros`        | Cadastrar livro       | 201 Created   |
| GET    | `/api/livros`        | Listar todos os livros| 200 OK        |
| GET    | `/api/livros/{id}`   | Buscar livro por ID   | 200 OK / 404  |
| DELETE | `/api/livros/{id}`   | Remover livro         | 204 / 404     |

---

## 📋 Exemplos de Requisições

### 1. Cadastrar um livro (POST)
```bash
curl -X POST http://localhost:8080/api/livros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "O Senhor dos Anéis",
    "autor": "J.R.R. Tolkien",
    "isbn": "978-8595084759",
    "anoPub": 1954
  }'
```

**Resposta (201 Created):**
```json
{
  "id": 4,
  "titulo": "O Senhor dos Anéis",
  "autor": "J.R.R. Tolkien",
  "isbn": "978-8595084759",
  "anoPub": 1954
}
```

---

### 2. Listar todos os livros (GET)
```bash
curl http://localhost:8080/api/livros
```

**Resposta (200 OK):**
```json
[
  { "id": 1, "titulo": "Clean Code", "autor": "Robert C. Martin", ... },
  { "id": 2, "titulo": "O Programador Pragmático", "autor": "David Thomas", ... },
  { "id": 3, "titulo": "Design Patterns", "autor": "Gang of Four", ... }
]
```

---

### 3. Buscar livro por ID (GET)
```bash
curl http://localhost:8080/api/livros/1
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "isbn": "978-0132350884",
  "anoPub": 2008
}
```

**Resposta quando não encontrado (404 Not Found)**

---

### 4. Remover livro (DELETE)
```bash
curl -X DELETE http://localhost:8080/api/livros/1
```

**Resposta (204 No Content)** — sucesso sem corpo  
**Resposta (404 Not Found)** — ID não existe

---

## 💉 Injeção de Dependências

O projeto demonstra injeção via **construtor** (prática recomendada):

```java
// LivroController recebe o LivroService
@RestController
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService) { // ← Injeção via construtor
        this.livroService = livroService;
    }
}

// LivroService recebe o LivroRepository
@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) { // ← Injeção via construtor
        this.livroRepository = livroRepository;
    }
}
```

## 🔄 Fluxo de Comunicação

```
Cliente HTTP
     │
     ▼
LivroController  (@RestController)  ← recebe/retorna JSON
     │
     ▼
LivroService     (@Service)         ← aplica regras de negócio
     │
     ▼
LivroRepository  (@Repository)      ← acessa dados em memória
     │
     ▼
Livro            (Model/POJO)       ← entidade do domínio
```

---

## 🗂️ Dados Pré-carregados

O repositório inicializa com 3 livros de exemplo para facilitar os testes:

| ID | Título                     | Autor              | Ano  |
|----|----------------------------|--------------------|------|
| 1  | Clean Code                 | Robert C. Martin   | 2008 |
| 2  | O Programador Pragmático   | David Thomas       | 1999 |
| 3  | Design Patterns            | Gang of Four       | 1994 |
