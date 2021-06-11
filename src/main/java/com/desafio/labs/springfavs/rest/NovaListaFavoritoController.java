package com.desafio.labs.springfavs.rest;

import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.labs.springfavs.domain.Cliente;
import com.desafio.labs.springfavs.domain.Favorito;
import com.desafio.labs.springfavs.domain.Product;
import com.desafio.labs.springfavs.dto.RespostaDTO;
import com.desafio.labs.springfavs.repository.ClienteRepository;

@RestController
public class NovaListaFavoritoController {

	@Autowired
	public NovaListaFavoritoController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	private final ClienteRepository clienteRepository;

	@GetMapping("/rest/favorito/novo/{clienteId}")
	public RespostaDTO novo(@PathVariable("clienteId") Long clienteId) {

		RespostaDTO dto = new RespostaDTO();

		System.out.println(clienteId);
		
		try {
			Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
			Cliente c = clienteOpt.get();

			
			System.out.println("passou por aqui");
			
			if (c.getFavoritos().size() == 0) {
			
				System.out.println("passou por aqui");
				
 				System.out.println(c.getEmail());
				
				List<Product> produtos = new ArrayList<Product>();

				Favorito favorito = new Favorito();
				
				favorito.setItens(produtos);
				favorito.setCliente(c);
				
				c.novoFavorito(favorito);
				this.clienteRepository.saveAndFlush(c);
				
				List<Long> favoritosID = new ArrayList<Long>();
				
				for (Favorito p : c.getFavoritos()) {
					favoritosID.add(p.getId());
				}
				Long ultimoPedido = Collections.max(favoritosID);
			
				dto = new RespostaDTO(ultimoPedido, "lista de favoritos criada com sucesso");
	
			} else {
				dto = new RespostaDTO(null, "este cliente ja possui uma lista cadastrada");
			}
			
		} catch (Exception e) {
			dto.setMensagem("Erro: " + e.getMessage());
			System.out.println(e.getMessage());
		}
		return dto;

	}

}
