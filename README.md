# Welcome to StackEdit!

Olá, seja bem vindo(a).
Esta aplicação está escrita em Java usando o framework Spring Boot e integração com o MySQL (Hibernate).
Trata-se de uma aplicação destinada a cadastrar uma coleção de produtos favoritos de um determinado cliente.

# Instalação

StackEdit stores your files in your browser, which means all your files are automatically saved locally and are accessible **offline!**

## Docker (aplicação)

The file explorer is accessible using the button in left corner of the navigation bar. You can create a new file by clicking the **New file** button in the file explorer. You can also create folders by clicking the **New folder** button.

## Docker (MySQL)

All your files and folders are presented as a tree in the file explorer. You can switch from one to another by clicking a file in the tree.

# Como funciona

## Cadastrando um Cliente
Listando os clientes cadastrados:

    GET http://<endpoint_da_aplicacao>/api/clientes

Adicionando um cliente:

    POST http://<endpoint_da_aplicacao>/api/clientes
    {"email": "a@a.com", "nome": "aaa3"}

Consultando um cliente:

    GET http://<endpoint_da_aplicacao>/api/clientes/5

Excluindo um cliente:

    DELETE http://<endpoint_da_aplicacao>/api/clientes/5

Atualizando um cliente:

    PUT http://<endpoint_da_aplicacao>/api/clientes
    {"email": "aaa3", "nome": "aaa3"}

## Cadastrando um Produto

Listando os produtos disponíveis: 

Adicionando um produto na prateleira:

    POST http://<endpoint_da_aplicacao>/api/itens
    {"nome": "Mortandela", "preco": 112.12}

Consultado um produto na prateleira:

    GET http://<endpoint_da_aplicacao>/api/itens/5

Excluindo um produto da prateleira:

    DELETE http://<endpoint_da_aplicacao>/api/itens/5

Atualizando um produto na prateleira:

    PUT http://<endpoint_da_aplicacao>/api/itens
    {"nome": "Mortandela", "preco": 112.13}

## Adicionando uma lista de  favoritos para um Cliente

Exemplo:

     http://<endpoint_da_aplicacao>/rest/pedido/novo/1

Onde:

     http://<endpoint_da_aplicacao>/rest/pedido/novo/{clienteId}

## Adicionando o Produto na lista de favoritos de um Cliente

> **Note:** Não é permitido adicionar o mesmo produto duas vezes na lista de favoritos:

    http://<endpoint_da_aplicacao>/rest/pedido/novo/2/1/1,2,3

Onde:

    http://<endpoint_da_aplicacao>/rest/pedido/novo/{clienteId}/{pedidoId}/{listaDeItens}

## Listando os produtos da lista de favoritos de um determinado cliente

Consulta as listas do cliente:

    http://localhost:8080/api/clientes/{clienteId}/pedidos

Consulta os produtos da lista:

    http://localhost:8080/api/pedidoes/{pedidoId}/itens
