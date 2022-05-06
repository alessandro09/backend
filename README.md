# Introdução

Este projeto  visa responder o teste de habilidades repassado pela TexoIt voltado para o desenvolvedor fullstack.
Foi desenvolvido utilizando spring boot, spring data, spring web, liquibase e h2. O principal objetivo é importar um arquivo csv para uma base de dados h2 e criar uma api para retornar os produtores cinematograficos com dois premios mais proximos / dois premios mais distantes respectivamente.

A cobertura de testes, conforme solicitado foi realizado com teste de integração apenas através do endpoint

# Passos para execução

A maineira mais fácil de executar é abrindo o terminal e executando o comando a baixo (Dependencias serão obtidas automaticamente):

`./gradlew bootRun`

Outra opção é importar o projeto para IDE de sua preferencia e executar `br.com.texo.testback.backend.BackendApplication`

# Endpoints disponíveis e informações para acesso

### Acesso ao console do h2
**host:** http://localhost:8080/h2<br>
**jdbc/url:** jdbc:h2:./data/appdb-new<br>
**usuário:** admin<br>
**senha:** admin<br>

** **Para evitar problemas, optei por criar um novo banco. Este pode ser excluído que será recriado toda a vez que a aplicação for reiniciada caso ainda não exista **

### Acesso ao endpoint da atividade
http://localhost:8080/producer/extreme-producer-awards