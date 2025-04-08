# README.md

# Desafio técnico de integração ao HubSpot

Trata-se de um projeto Java/Springboot para integração ao fluxo de autenticação OAuth 2.0 do HubSpot para cadastro de contatos e recebimento de notificações via Webhooks.

## Features

- **Criação de URL de autorização**: Endpoint responsável por gerar e retornar a URL de autorização para iniciar o fluxo OAuth com o HubSpot.
- **Processamento do retorno OAuth**: Endpoint recebe o código de autorização fornecido pelo HubSpot e realiza a troca pelo token de acesso.
- **Criação de novo contato**: Endpoint que faz a criação de um Contato no CRM através da API. O endpoint deve respeitar as políticas de rate limit definidas pela API.
- **Webhook**: Endpoint que escuta e processa eventos do tipo "contact.creation", enviados pelo webhook do HubSpot. (Não implementado por falta de permissões na conta de desenvolvedor.)

## Requirements

- Java 17 or higher
- Maven

## Setup Instructions

1. Clonar o repositório:
   ```
   git clone <repository-url>
   ```

2. Importar projeto utilizando sua IDE de preferência.

3. Build projeto utilizando Maven:
   ```
   mvn clean install
   ```

4. Start aplicação:
   ```
   mvn spring-boot:run
   ```

5. Rodar aplicação no seu browser de preferência.
    ```
    Digitar na barra de endereço a url `http://localhost:10120/auth/generate-url`

6. Selecionar conta cadastrada no HubSpot.

7. Digitar dados do contato que deseja cadastrar e clicar em enviar.

## Explanation Development

A aplicação foi divida em camadas de Contoller, Modelos e serviços. No pacote de controller, estão as classes dos controladores de autorização, contatos e webhooks. Os controladores são basicamente responsáveis por realizar a chamada dos serviços internos de cada funcionalidade e retornar o resultado recebido.
Os serviços foram divididos em 'serviços da aplicação (ou serviços internos) e serviços de integração'. Os serviços internos são responsáveis por, se necessário, realizar build de objetos, processar e tratar respostas retornadas pelo serviço de integração, que por sua vez, tem apenas a função de realizar chamdas Rest para os serviços externos. A ideia de segregar os serviços é o isolamento de responsabilidades e proteção da aplicação externa, por exemplo, seria possivel que os DTO's de entrada da aplicação possuissem nomes de atributos diferentes dos esperados na aplicação que será consultada, podendo ser expostos num Swagger, sem expor a API final.

Apenas uma classe foi testada, apenas para demonstração da forma que eu trabalho, realizando testes unitários de todas as classes, possíveis, do projeto.

Ao pom, foram adicionadas bibliotecas como PMD, Checkstyle para garantir a qualidade do código (Porém pela falta de tempo, não estão sendo validadas).
Também foi utilizada a biblioteca do lombok para simplificar a escrita do código e utlização do SLF4L.
