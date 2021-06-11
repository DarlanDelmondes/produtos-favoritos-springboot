package com.desafio.labs.springfavs.carga;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.desafio.labs.springfavs.domain.Cliente;
import com.desafio.labs.springfavs.domain.Favorito;
import com.desafio.labs.springfavs.domain.Product;
import com.desafio.labs.springfavs.repository.ClienteRepository;

@Component
public class RepositoryTest 
implements ApplicationRunner 
{

	private static final long ID_CLIENTE = 11l;
	
	private static final long ID_ITEM1 = 100l;
	private static final long ID_ITEM2 = 101l;
	private static final long ID_ITEM3 = 102l;
	
	private static final long ID_FAV1 = 101;
	
	@Autowired
    private ClienteRepository clienteRepository;
	
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    	System.out.println(">>> Iniciando carga de dados...");
    	Cliente cliente = new Cliente(ID_CLIENTE,"Darlan","darlan-teste@live.com");
    	
    	//Long id,String title,Double price, String brand, String image, String reviewScore
    	Product prod1 = new Product(ID_ITEM1,"G6 Moto",1200.00,"Motorola",
    					"https://brmotorolanew.vteximg.com.br/arquivos/ids/156175-700-700/Moto-G9_ELECTRIC-BLUE_PDP-HERO-foto-1.png?v=637455482514630000",
    					1.0);
    	Product prod2 = new Product(ID_ITEM2,"QLED 8K",7000.00,"Samsung",
						"https://brmotorolanew.vteximg.com.br/arquivos/ids/156175-700-700/Moto-G9_ELECTRIC-BLUE_PDP-HERO-foto-1.png?v=637455482514630000",
						1.0);
    	Product prod3 = new Product(ID_ITEM3,"iPhone 11",5000.00,"Apple",
						"https://brmotorolanew.vteximg.com.br/arquivos/ids/156175-700-700/Moto-G9_ELECTRIC-BLUE_PDP-HERO-foto-1.png?v=637455482514630000",
						1.0);
		    	
    	List<Product> listaProdutosFernando1 = new ArrayList<Product>();
    	listaProdutosFernando1.add(prod1);
    	listaProdutosFernando1.add(prod2);
    	listaProdutosFernando1.add(prod3);
    	
    	Favorito favoritosDoFernando = new Favorito(ID_FAV1,cliente,listaProdutosFernando1);
    	cliente.novoFavorito(favoritosDoFernando);
    	
    	System.out.println(">>> Pedido 1 - Fernando : "+ favoritosDoFernando);
    	
		clienteRepository.saveAndFlush(cliente);
    	System.out.println(">>> Gravado cliente 1: "+cliente);
		
    }
 
}