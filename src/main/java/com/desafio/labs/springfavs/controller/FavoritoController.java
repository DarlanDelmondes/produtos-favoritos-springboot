
package com.desafio.labs.springfavs.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafio.labs.springfavs.domain.Cliente;
import com.desafio.labs.springfavs.domain.Favorito;
import com.desafio.labs.springfavs.domain.Product;
import com.desafio.labs.springfavs.repository.ClienteRepository;
import com.desafio.labs.springfavs.repository.FavoritoRepository;
import com.desafio.labs.springfavs.repository.ProductRepository;

@Controller
@RequestMapping("/favoritos")
public class FavoritoController {

	private final FavoritoRepository favoritoRepository;
	private final ProductRepository itemRepository;
	private final ClienteRepository clienteRepository;
	private final String ITEM_URI = "favoritos/";

	public FavoritoController(FavoritoRepository pedidoRepository,ProductRepository itemRepository,ClienteRepository clienteRepository) {
		this.favoritoRepository = pedidoRepository;
		this.itemRepository = itemRepository;
		this.clienteRepository = clienteRepository;
	}

	@GetMapping("/")
	public ModelAndView list() {
		Iterable<Favorito> pedidos = this.favoritoRepository.findAll();
		return new ModelAndView(ITEM_URI + "list","pedidos",pedidos);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Favorito pedido) {
		return new ModelAndView(ITEM_URI + "view","pedido",pedido);
	}

	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Favorito pedido) {

		Map<String,Object> model = new HashMap<String,Object>();
		model.put("todosItens",itemRepository.findAll());
		model.put("todosClientes",clienteRepository.findAll());
		return new ModelAndView(ITEM_URI + "form",model);
		 
	}

	@PostMapping(params = "form")
	public ModelAndView create(@Valid Favorito pedido,BindingResult result,RedirectAttributes redirect) {
		if (result.hasErrors()) { return new ModelAndView(ITEM_URI + "form","formErrors",result.getAllErrors()); }

		if (pedido.getId() != null) {
			
			Optional<Favorito> pedidoParaAlterarOpt = favoritoRepository.findById(pedido.getId());
			Favorito pedidoParaAlterar = pedidoParaAlterarOpt.get();
						
			Optional<Cliente> clienteOpt = clienteRepository.findById(pedidoParaAlterar.getCliente().getId());
			Cliente c = clienteOpt.get();
			
			pedidoParaAlterar.setItens(pedido.getProducts());
					
			c.getFavoritos().remove(pedidoParaAlterar);
			c.getFavoritos().add(pedidoParaAlterar);
			this.clienteRepository.save(c);
		} else {
			
			Optional<Cliente> clienteOpt = clienteRepository.findById(pedido.getCliente().getId());
			Cliente c = clienteOpt.get();

			pedido = this.favoritoRepository.save(pedido);
			c.getFavoritos().add(pedido);
			this.clienteRepository.save(c);
		}
		redirect.addFlashAttribute("globalMessage","Pedido gravado com sucesso");
		return new ModelAndView("redirect:/" + ITEM_URI + "{pedido.id}","pedido.id",pedido.getId());
	}

	@GetMapping(value = "remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id,RedirectAttributes redirect) {

		Optional<Favorito> pedidoParaRemoverOpt = favoritoRepository.findById(id);
		Favorito pedidoParaRemover = pedidoParaRemoverOpt.get();

		Optional<Cliente> clienteOpt = clienteRepository.findById(pedidoParaRemover.getCliente().getId());
		Cliente c = clienteOpt.get();
 
		this.clienteRepository.save(c);
		this.favoritoRepository.deleteById(id);

		Iterable<Favorito> pedidos = this.favoritoRepository.findAll();

		ModelAndView mv = new ModelAndView(ITEM_URI + "list","pedidos",pedidos);
		mv.addObject("globalMessage","Pedido removido com sucesso");

		return mv;
	}

	@GetMapping(value = "alterar/{id}")
	public ModelAndView alterarForm(@PathVariable("id") Favorito pedido) {

		Map<String,Object> model = new HashMap<String,Object>();
		model.put("todosItens",itemRepository.findAll());
		model.put("todosClientes",clienteRepository.findAll());
		model.put("pedido",pedido);

		return new ModelAndView(ITEM_URI + "form",model);
	}

}
