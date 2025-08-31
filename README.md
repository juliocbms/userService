# Microsserviço de Gestão de Usuários

Este microserviço é a **fonte de verdade** para todas as funcionalidades de usuário na arquitetura. Ele é responsável pelo ciclo de vida completo do usuário: cadastro, login, autenticação e gerenciamento de permissões. Sua principal função é validar as credenciais e emitir tokens JWT que serão usados por outros serviços para garantir a segurança das requisições.

### Tecnologias Utilizadas

-   **Linguagem:** Java 21
-   **Framework:** Spring Boot 3
-   **Segurança:** Spring Security e JWT (JSON Web Tokens)
-   **Persistência:** Spring Data JPA
-   **Banco de Dados:** PostgreSQL (via Docker)
-   **Ferramenta de Build:** Maven

### Funcionalidades (Endpoints)

| Método | Endpoint | Descrição | Restrição de Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/users/login` | Autentica um usuário com e-mail e senha, retornando um token JWT. | **Público** |
| `POST` | `/users` | Cadastra um novo usuário no banco de dados. | **Público** |
| `GET` | `/users` | Retorna uma lista com todos os usuários. | Somente ADMIN |
| `GET` | `/users/{id}` | Busca um usuário específico por ID. | Somente ADMIN |
| `PUT` | `/users/{id}` | Atualiza os dados de um usuário existente. | Somente ADMIN |
| `DELETE`| `/users/{id}` | Deleta um usuário do sistema. | Somente ADMIN |

### Detalhes da Implementação

#### Segurança e Autenticação
A segurança é gerenciada pelo **Spring Security**, que atua como um filtro na cadeia de requisições. O microserviço utiliza:
-   **JWT:** Após um login bem-sucedido, um token JWT é gerado e retornado ao cliente.
-   **Filtro de Segurança:** Um filtro personalizado (`SecurityFilter`) é executado antes das requisições para endpoints protegidos, validando o token JWT para garantir que a requisição é válida e autorizada.
-   **Controle de Acesso:** Anotações como `@PreAuthorize("hasRole('ADMIN')")` garantem que as rotas sensíveis só possam ser acessadas por usuários com as permissões corretas.

#### Persistência de Dados
O banco de dados é uma instância do **PostgreSQL** rodando em um container **Docker**. Isso garante um ambiente de desenvolvimento isolado, consistente e de fácil configuração.

### Como Rodar o Projeto

#### Requisitos do Sistema
-   Java 21 
-   Maven 3
-   Docker e Docker Compose

#### 1. Clonar o Repositório
```bash
git clone [https://github.com/juliocbms/userService.git](https://github.com/juliocbms/userService.git)
cd userService
