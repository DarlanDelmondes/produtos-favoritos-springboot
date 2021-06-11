
# Lista de Produtos SpringFavs

Olá, seja bem vindo(a).
Esta aplicação está escrita em Java usando o framework Spring Boot e integração com o MySQL (Hibernate).
Trata-se de uma aplicação destinada a cadastrar uma coleção de produtos favoritos de um determinado cliente.

# Instalação


> **Note:** Certifique-se de que possua o Maven e o Docker instalado em sua máquina. 
>  
> O Maven será usado para a instalação das dependências e o docker para subir um banco de dados MySQL em seu ambiente de instalação, caso já possua uma instância MySQL previamente instalada e queira utilizar certifique-se que haja uma credencial com usuário root e senha root. 

Baixe a imagem do MySQL em sua máquina:

    docker run --name labs-desafio -p 3306:3306 -p 33060:33060  -e MYSQL_ROOT_HOST='%' -e MYSQL_ROOT_PASSWORD='root' -d mysql/mysql-server:latest;

Acesse o MySQL atraves do comando:

    docker exec -it labs-desafio mysql -uroot -proot

Crie uma nova database chamada labs:

    create database labs;
    commit;

## Subindo a aplicação
Faça checkout do repositório em seu ambiente.
Acesse a raiz da pasta do projeto e execute a aplicação com o comando a seguir:

    mvn spring-boot:run -Drun.arguments="--spring.profiles.active=mysql"

### Autentique-se:
Para consumir qualquer recurso da API é necessária uma autenticação prévia via Basic Auth usando o método GET:

 - Usuario: Luiza 
 - Senha: Labs123

# Como funciona

## Cadastrando um Cliente
Listando os clientes cadastrados:

    GET http://<endpoint_da_aplicacao>/api/clientes

Adicionando um cliente:

    POST http://<endpoint_da_aplicacao>/api/clientes
    {"endereco": "nome@conta.com", "nome": "Usuario"}

Consultando um cliente:

    GET http://<endpoint_da_aplicacao>/api/clientes/5

Excluindo um cliente:

    DELETE http://<endpoint_da_aplicacao>/api/clientes/5

Atualizando um cliente:

    PUT http://<endpoint_da_aplicacao>/api/clientes
    {"endereco": "nome@conta.com", "nome": "Usuario"}

## Cadastrando um Produto

Listando os produtos disponíveis: 

Adicionando um produto na prateleira:

    POST http://<endpoint_da_aplicacao>/api/itens
    {
      "id" : 1,
      "title" : "G6 Moto",
      "price" : 1300.0,
      "image" : "https://brmotorolanew.vteximg.com.br/arquivos/ids/156175-700-700/Moto-G9_ELECTRIC-BLUE_PDP-HERO-foto-1.png?v=637455482514630000",
      "brand" : "Motorola",
      "reviewScore" : 1.0
      }

Consultado um produto na prateleira:

    GET http://<endpoint_da_aplicacao>/api/itens/5

Excluindo um produto da prateleira:

    DELETE http://<endpoint_da_aplicacao>/api/itens/5

Atualizando um produto na prateleira:

    PUT http://<endpoint_da_aplicacao>/api/itens
    {
      "id" : 1,
      "title" : "G6 Moto",
      "price" : 1300.0,
      "image" : "https://brmotorolanew.vteximg.com.br/arquivos/ids/156175-700-700/Moto-G9_ELECTRIC-BLUE_PDP-HERO-foto-1.png?v=637455482514630000",
      "brand" : "Motorola",
      "reviewScore" : 1.0
      }

## Adicionando uma lista de  favoritos para um Cliente

Exemplo:

     http://<endpoint_da_aplicacao>/rest/favorito/novo/1

Onde:

     http://<endpoint_da_aplicacao>/rest/favorito/novo/{clienteId}

## Adicionando o Produto na lista de favoritos de um Cliente

> **Note:** Não é permitido adicionar o mesmo produto duas vezes na lista de favoritos:

    http://<endpoint_da_aplicacao>/rest/favorito/novo/2/1/1,2,3

Onde:

    http://<endpoint_da_aplicacao>/rest/pedido/novo/{clienteId}/{favoritoId}/{listaDeItens}

## Listando os produtos da lista de favoritos de um determinado cliente

Consulta as listas do cliente:

    http://localhost:8080/api/clientes/{clienteId}/pedidos

Consulta os produtos da lista:

    http://localhost:8080/api/favoritoes/{favoritoId}/itens


