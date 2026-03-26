# 🎬 Projeto Filmes

A aplicação consiste em um sistema de listagem, cadastro e compra de filmes fictícios, com foco em boas práticas de desenvolvimento web (REST, MVC, segurança).

---

## ✅ Funcionalidades implementadas

- Cadastro, listagem, edição, soft delete e restauração de filmes
- Listagem pública com imagem e botão de adicionar ao carrinho
- Carrinho de compras usando sessão HTTP
- Finalização de compra com limpeza da sessão
- Autenticação e autorização com Spring Security (login customizado)
- Controle de acesso por papéis: `ROLE_ADMIN` e `ROLE_USER`
- Validação de formulários com Bean Validation
- Uso de Thymeleaf com layout Bootstrap (com fragments)

---

## 🛠️ Tecnologias

- Java 21 
- Spring Boot  
- Spring Data JPA  
- Spring Web  
- Spring Security  
- Thymeleaf  
- PostgreSQL  
- Lombok  
- Bootstrap  
- Docker  


---

## 🔐 Segurança

- Senhas criptografadas com BCrypt
- Login e logout com páginas personalizadas
- Controle de acesso por papéis:
  - `ROLE_ADMIN` acessa `/admin`, `/cadastro`, `/editar`, `/deletar`, `/restaurar`
  - `ROLE_USER` acessa `/catalogo`, `/adicionarCarrinho`, `/verCarrinho`, `/finalizarCompra`
- Username do usuário logado exibido no cabeçalho

---

## 🗂️ Principais rotas

### User
- `/catalogo` — Lista filmes ativos
- `/adicionarCarrinho?id=1` — Adiciona filme ao carrinho
- `/verCarrinho` — Lista itens do carrinho
- `/finalizarCompra` — Finaliza compra e limpa sessão

## Público
- `/login`, `/logout`, `/cadastroUsuario`

### Admin
- `/admin` — Lista todos os filmes (incluindo deletados)
- `/cadastro` — Formulário de cadastro do filme
- `/editar?id=1` — Editar filme
- `/salvar` — Cadastro e edição (POST)
- `/deletar?id=1` — Soft delete
- `/restaurar?id=1` — Restaurar item deletado




