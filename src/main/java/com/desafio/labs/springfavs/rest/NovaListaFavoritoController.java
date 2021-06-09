package com.desafio.labs.springfavs.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.labs.springfavs.domain.Cliente;
import com.desafio.labs.springfavs.domain.Favorito;
import com.desafio.labs.springfavs.domain.Products;
import com.desafio.labs.springfavs.dto.RespostaDTO;
import com.desafio.labs.springfavs.repository.ClienteRepository;
import com.desafio.labs.springfavs.repository.FavoritoRepository;
import com.desafio.labs.springfavs.repository.ProductRepository;

@RestController
public class NovaListaFavoritoController {

	@Autowired
	public NovaListaFavoritoController(ClienteRepository clienteRepository, FavoritoRepository pedidoRepository,
			ProductRepository itemRepository) {
		this.clienteRepository = clienteRepository;
		this.pedidoRepository = pedidoRepository;
		this.itemRepository = itemRepository;
	}

	private final ClienteRepository clienteRepository;
	private final FavoritoRepository pedidoRepository;
	private final ProductRepository itemRepository;

	@GetMapping("/rest/favorito/novo/{clienteId}")
	public RespostaDTO novo(@PathVariable("clienteId") Long clienteId) {

		RespostaDTO dto = new RespostaDTO();

		try {
			Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
			Cliente c = clienteOpt.get();
			
			Favorito pedido = new Favorito();

			List<Products> itensPedidos = new ArrayList<Products>();

			pedido.setItens(itensPedidos);
			pedido.setData(new Date());
			pedido.setCliente(c);
			c.getFavoritos().add(pedido);

			this.clienteRepository.saveAndFlush(c);

			List<Long> pedidosID = new ArrayList<Long>();
			for (Favorito p : c.getFavoritos()) {
				pedidosID.add(p.getId());
			}

			Long ultimoPedido = Collections.max(pedidosID);

			dto = new RespostaDTO(ultimoPedido, " lista de favoritos criada com sucesso");

		} catch (Exception e) {
			dto.setMensagem("Erro: " + e.getMessage());
		}
		return dto;

	}

}
