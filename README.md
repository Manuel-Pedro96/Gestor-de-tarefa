# API de Gerenciamento de Tarefas

Uma API RESTful simples para gerenciar tarefas, construída com Spring Boot, Spring Data JPA e MariaDB.

## Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias:

* **Java:** JDK 21
* **Spring Boot:** v3.x.x
* **Spring Data JPA:** Para abstração e simplificação do acesso a dados.
* **Hibernate:** Implementação de JPA.
* **MariaDB (ou MySQL):** Sistema de gerenciamento de banco de dados relacional.
* **Apache Maven:** Ferramenta para automação de build e gerenciamento de dependências.
* **Lombok:** Biblioteca para reduzir código boilerplate em classes Java.

## Funcionalidades (CRUD)

A API oferece as seguintes operações básicas de gerenciamento de tarefas:

* **Criar novas tarefas:** `POST /api/tasks`
* **Listar todas as tarefas:** `GET /api/tasks`
* **Obter uma tarefa por ID:** `GET /api/tasks/{id}`
* **Atualizar tarefas existentes:** `PUT /api/tasks/{id}`
* **Excluir tarefas:** `DELETE /api/tasks/{id}`

## Como Rodar o Projeto

Para que esta API funcione corretamente em sua máquina local, siga os passos abaixo:

### 1. Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em seu ambiente:

* **Java Development Kit (JDK) 21:**
    * Você pode verificar sua versão do Java executando `java --version` no terminal.
* **Apache Maven 3.x:**
    * Utilizado para gerenciar as dependências do projeto e o processo de build. Você pode verificar sua versão com `mvn --version`.
* **Servidor MariaDB:**
    * A API utiliza MariaDB como banco de dados. Você pode instalar o MariaDB Server (ou MySQL, pois são compatíveis para este projeto). Certifique-se de que ele esteja rodando na porta padrão (3306).

### 2. Clonar o Repositório

Abra seu terminal ou prompt de comando e execute o seguinte comando para clonar o repositório para sua máquina local:

```bash
git clone https://github.com/Manuel-Pedro96/Gestor-de-tarefa.git
cd Gestor-de-tarefa

3. Configuração do Banco de Dados MariaDB

Esta API se conecta a um banco de dados MariaDB chamado gestor_de_tarefa usando um usuário específico. Siga as instruções para configurar o seu banco de dados:

Acessar o Cliente MariaDB/MySQL:

Abra um terminal e acesse o console do MariaDB/MySQL como um usuário com privilégios administrativos (ex: root):
Bash

mysql -u root -p
# Digite a senha do usuário root quando solicitado.

Criar o Banco de Dados:

Dentro do console do MariaDB, crie o banco de dados gestor_de_tarefa se ele ainda não existir:
SQL

CREATE DATABASE IF NOT EXISTS gestor_de_tarefa;

Criar Usuário e Conceder Permissões:

Crie o usuário e conceda a ele todas as permissões no banco de dados gestor_de_tarefa. É crucial que estas credenciais correspondam às configuradas no arquivo application.properties da aplicação.
SQL

CREATE USER 'seu_usuario_db'@'localhost' IDENTIFIED BY 'sua_senha_db';
GRANT ALL PRIVILEGES ON gestor_de_tarefa.* TO 'seu_usuario_db'@'localhost';
FLUSH PRIVILEGES; -- Recarrega os privilégios para que as alterações tenham efeito imediato

    Nota: Substitua 'seu_usuario_db' e 'sua_senha_db' pelas credenciais que você deseja usar. Para este projeto de portfólio, ALL PRIVILEGES é adequado para desenvolvimento. Para ambientes de produção, seria recomendado usar permissões mais restritas.

Sair do Console do MariaDB:

SQL

EXIT;

4. Configurar application.properties

Navegue até o diretório src/main/resources do seu projeto e abra o arquivo application.properties. Verifique e ajuste as configurações de conexão com o banco de dados para corresponderem às credenciais que você configurou no passo anterior:
Properties

spring.application.name=gerenciamento_de_tarefa

spring.datasource.url=jdbc:mariadb://localhost:3306/gestor_de_tarefa?createDatabaseIfNotExist=true
spring.datasource.username=seu_usuario_db
spring.datasource.password=sua_senha_db
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
# spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect # Pode ser removido, pois Hibernate 6 detecta automaticamente

    Lembre-se de usar as mesmas credenciais definidas durante a criação do usuário no MariaDB.

5. Iniciar a Aplicação Spring Boot

Com o banco de dados configurado, você pode iniciar a aplicação Spring Boot. Navegue até o diretório raiz do projeto no terminal (cd gerenciamento_de_tarefa) e execute:
Bash

./mvnw spring-boot:run

A aplicação será iniciada e o servidor Tomcat embutido estará disponível na porta 8080. Você verá logs indicando que a aplicação está "Started" e que o Hibernate criou a tabela tasks no banco de dados.

Exemplos de Endpoints

Para testar a API, você pode usar ferramentas como curl, Postman ou Insomnia. Abaixo estão alguns exemplos de requisições:

Criar uma nova tarefa (POST)

Bash

curl -X POST \
  http://localhost:8080/api/tasks \
  -H 'Content-Type: application/json' \
  -d '{
    "title": "Aprender Spring Boot e JPA",
    "description": "Estudar a documentação e praticar codificação.",
    "completed": false,
    "dueDate": "2025-07-25T23:59:59"
  }'

Listar todas as tarefas (GET)

Bash

curl -X GET http://localhost:8080/api/tasks

Obter uma tarefa por ID (GET por ID)

Assumindo que você criou uma tarefa com id=1.
Bash

curl -X GET http://localhost:8080/api/tasks/1

Atualizar tarefa específica (PUT)

Bash

curl -X PUT \
  http://localhost:8080/api/tasks/1 \
  -H 'Content-Type: application/json' \
  -d '{
    "id": 1,
    "title": "Aprender Spring Boot e JPA",
    "description": "Estudar a documentação e praticar codificação - ATUALIZADO.",
    "completed": true,
    "dueDate": "2025-07-25T23:59:59"
  }'

Deletar tarefa (DELETE)

Bash

curl -X DELETE http://localhost:8080/api/tasks/1

Desafios e Soluções

Durante o desenvolvimento e configuração desta API, enfrentei alguns desafios comuns em projetos Spring Boot com banco de dados, que me permitiram aprofundar meus conhecimentos em depuração e configuração:

    Problemas de Dependência (cannot find symbol): Inicialmente, encontrei erros de compilação como cannot find symbol e package ... does not exist. A solução envolveu a correta inclusão da dependência spring-boot-starter-data-jpa no pom.xml, que trouxe as dependências necessárias para JPA e Hibernate.

    Erro de Carregamento de Driver JDBC (Failed to load driver class): Houve um problema com o carregamento do driver JDBC para MariaDB, manifestado por Failed to load driver class com.mysql.cj.jdbc.Driver. Este problema foi resolvido garantindo a compatibilidade da versão do Java (<java.version> no pom.xml ajustado para 21) com o driver MariaDB e o Spring Boot 3.5.3, e confirmando que o driver estava corretamente no classpath.

    Erro de Permissão de Banco de Dados (Access denied): Após resolver os problemas de driver, a aplicação não conseguia acessar o banco de dados devido a um erro de Access denied for user 'minimercado_userMP'@'localhost' to database 'gestor_de_tarefa'. Este problema foi solucionado concedendo as permissões ALL PRIVILEGES ao usuário minimercado_userMP no banco de dados gestor_de_tarefa diretamente no console do MariaDB.

Esses desafios, embora comuns, foram valiosas oportunidades de aprendizado e reforçaram a importância da configuração detalhada e da depuração em projetos Java.
