# Fórum

## Resumo:
API REST que representa um Fórum, e que possui as seguintes funcionalidades:
- Login com autenticação via Token JWT.
- Listar todos os tópicos.
- Listar um tópico com informações mais detalhadas por ID.
- Cadastrar e alterar um tópico.
- Excluir um tópico se tiver perfil como Moderador.

## Tecnologias Utilizadas:
- Java
- Spring Boot, Data, Security, Actuator
- Token JWT
- Beans Validation
- Banco H2
- Maven
- Swagger
- Junit/Mockito

## Mais informações:
- Documentação Swagger detalhada da API pode ser acessada através da requisição "/swagger-ui/"
- Para se logar, basta fazer um POST para "/auth" passando login e password
- Na pasta src/main/resources há um arquivo data.sql que contém os inserts de alguns registros que populam o BD.
- Na pasta spring-boot-admin contém o aplicação client para verificar informações de desempenho da API "Fórum" liberadas pelo Spring Boot Actuator. Para visualizar essas informações, basta rodar as duas aplicações juntas, e depois acessar pelo navegador o endereço localhost:8081 para acessar o Spring Boot Admin.

- Usuários para testes:

login: aluno@email.com
password: 123456

login: moderador@email.com
password: 123456 (PERFIL MODERADOR)
