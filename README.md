# ms-email

`ms-email` é um microserviço responsável por receber mensagens de e-mail de uma fila RabbitMQ e enviá-las usando SMTP e MailSender.
![image](https://github.com/luanreis164/ms-email/assets/76753597/b1d134a8-0b54-490f-8dac-414d9927e229)

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring AMQP (RabbitMQ)
- Spring Mail
- Maven
- Swagger


## Pré-requisitos

- Java 17
- Conta no CloudAMQP (ou outro serviço de fila RabbitMQ)
- Servidor SMTP configurado

## Configuração

1. Clone este repositório:
  - https://github.com/luanreis164/ms-email.git

2. Configure as credenciais da fila RabbitMQ no arquivo `application.properties`:
 - `spring.rabbitmq.addresses` = seu endereço completo gerado.

3. Configure as propriedades do servidor SMTP no mesmo arquivo `application.properties`:
  - `spring.mail.username=smtp_usuario`
  - `spring.mail.password=smtp_senha`

4. Execute a aplicação.

5. Acesse a documentação da API Swagger:
   Após iniciar a aplicação, você pode acessar a documentação da API Swagger em:
   http://localhost:8082/swagger-ui/index.html
## Fluxo de Trabalho

1. Um produtor (micro-serviço ms-user) envia uma mensagem de e-mail para a fila RabbitMQ quando um usuario for criado.
2. O consumer do `ms-email` consome a mensagem e a envia utilizando o servidor SMTP configurado.

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues para reportar bugs, sugerir novas funcionalidades ou enviar pull requests.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
