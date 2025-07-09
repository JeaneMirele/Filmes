# 🎬 Projeto Filmes

Este projeto foi desenvolvido com Java + Spring Boot. A aplicação consiste em um sistema de listagem, cadastro e compra de filmes fictícios, com foco em boas práticas de desenvolvimento web (REST, MVC, segurança e deploy).

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
- Deploy completo no Render.com com Docker e PostgreSQL

---

## 🛠️ Tecnologias

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Spring Web  
- Spring Security  
- Thymeleaf  
- PostgreSQL  
- Lombok  
- Bootstrap  
- Docker  
- Render.com

---

## 🔐 Segurança

- Senhas criptografadas com BCrypt
- Login e logout com páginas personalizadas
- Controle de acesso por papéis:
  - `ROLE_ADMIN` acessa `/admin`, `/cadastro`, `/editar`, `/deletar`, `/restaurar`
  - `ROLE_USER` acessa `/index`, `/adicionarCarrinho`, `/verCarrinho`, `/finalizarCompra`
- Username do usuário logado exibido no cabeçalho

---

## 🗂️ Principais rotas

### Público
- `/index` — Lista filmes ativos
- `/adicionarCarrinho?id=1` — Adiciona filme ao carrinho
- `/verCarrinho` — Lista itens do carrinho
- `/finalizarCompra` — Finaliza compra e limpa sessão
- `/login`, `/logout` — Login/logout

### Admin
- `/admin` — Lista todos os filmes (incluindo deletados)
- `/cadastro` — Formulário de cadastro
- `/editar?id=1` — Editar filme
- `/salvar` — Cadastro e edição (POST)
- `/deletar?id=1` — Soft delete
- `/restaurar?id=1` — Restaurar item deletado
- `/cadusuario` — Cadastro de usuário com papel

---

## 📸 Imagens dos filmes

As imagens estão na pasta `/static/images`. Uma imagem é atribuída aleatoriamente ao cadastrar um filme.

---
👤 Autoria
Desenvolvido por: Jeane Mirele e Vitor Paulino

