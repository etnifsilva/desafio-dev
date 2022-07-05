# Desafio programação - Upload de arquivos de transação

Este projeto tem como objetivo fazer o upload de arquivos de transação de lojas para uma base de dados.

Para a execução do projeto basta utilizar o docker-compose em sua raiz, através do comando. Necessário ter o docker instalado:

docker-compose up --build

Com este comando será feito o build das imagens do docker e a subida dos serviços necessário para sua execução.

São 3 serviços:

1. Base de dados (Postgresql)
2. Backend
3. Frontend

após a inicialização do projeto para acessar a página de upload de arquivos basta acessar o link [localhost:8000/app](http://localhost:8000/app)