Este projeto foi desenvolvido como parte da avaliação da disciplina de Programação Web na UFRN - Escola Agrícola de Jundiaí. A aplicação consiste em um sistema de listagem, cadastro e compra de filmes fictícios, utilizando a stack Java + Spring Boot, com foco em boas práticas de desenvolvimento web (REST, MVC, segurança, deploy).

Funcionalidades implementadas:

✅ Cadastro, listagem, edição, soft-delete e restauração de filmes

✅ Listagem pública de filmes com visualização de imagem e botão de adicionar ao carrinho

✅ Carrinho de compras usando sessão HTTP

✅ Finalização de compra com limpeza da sessão

✅ Autenticação e autorização com Spring Security (login customizado)

✅ Controle de acesso por papéis: ROLE_ADMIN e ROLE_USER

✅ Validação de formulários com Bean Validation

✅ Uso de Thymeleaf com layout baseado em template Bootstrap (com fragments)

✅ Deploy completo no Render.com com Docker e banco PostgreSQL na nuvem

🛠️ Tecnologias

- Java 17

- Spring Boot

- Spring Data JPA

- Spring Web

- Spring Security

- Thymeleaf

- PostgreSQL

- Lombok

- Bootstrap (via template HTML)

- Render.com (deploy)

- Docker

🗂️ Estrutura das rotas principais

Público
/index — Lista todos os filmes ativos (não deletados)

/adicionarCarrinho?id=1 — Adiciona filme ao carrinho

/verCarrinho — Exibe itens no carrinho

/finalizarCompra — Finaliza e limpa o carrinho

/login, /logout — Login/logout com páginas personalizadas

Admin
/admin — Lista todos os filmes, incluindo deletados

/cadastro — Formulário de cadastro

/editar?id=1 — Edição de filme

/salvar — POST de cadastro/edição

/deletar?id=1 — Soft delete

/restaurar?id=1 — Restauração de item deletado

/cadusuario — Cadastro de usuário com opção de admin

📸 Imagens dos filmes

As imagens estão pré-carregadas na pasta /static/images. No cadastro ou edição, uma imagem é atribuída aleatoriamente ao item.

🔐 Segurança

Senhas criptografadas com BCrypt

Acesso controlado por tipo de usuário:

ROLE_ADMIN: pode gerenciar filmes e usuários

ROLE_USER: pode comprar filmes

Exibição do nome do usuário logado no cabeçalho

Logout com botão no layout

🐳 Deploy com Docker + Render

Aplicação foi empacotada com Docker

PostgreSQL configurado na nuvem (Render)

GitHub configurado com deploy automático no push

👤 Autoria

Desenvolvido por: Jeane Mirele e Vitor Paulino
