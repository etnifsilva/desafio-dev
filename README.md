# Desafio de programação - Upload de arquivos de transação

Este projeto tem como objetivo fazer o upload de arquivos de transação de lojas para uma base de dados.

Para a execução do projeto basta utilizar o docker-compose localizado em sua raiz através do comando abaixo. Necessário ter o docker instalado:

docker-compose up --build

Com este comando será feito o build das imagens do docker e do projeto, além de subir os serviços necessário para sua execução.

São 3 serviços inicializados:

1. Base de dados (Postgresql)
2. Backend (Java / Spring Boot)
3. Frontend (Typescript / Lit)

Após a inicialização do projeto para acessar a página de upload de arquivos basta acessar este link: [http://localhost:8000/app](http://localhost:8000/app)

Para mais informações sobre como utilizar a API de inserção e listagem de transações por loja, utilize a documentação de utilização da API (OpenAPI) através do link [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)