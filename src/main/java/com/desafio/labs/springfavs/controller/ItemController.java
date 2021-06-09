
package com.desafio.labs.springfavs.controller;

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

import com.desafio.labs.springfavs.domain.Products;
import com.desafio.labs.springfavs.repository.ProductRepository;

@Controller
@RequestMapping("/products")
public class ItemController {

	private final ProductRepository itemRepository;
	private final String ITEM_URI = "products/";

	public ItemController(ProductRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@GetMapping("/")
	public ModelAndView list() {
		Iterable<Products> itens = this.itemRepository.findAll();
		return new ModelAndView(ITEM_URI + "list","itens",itens);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Products item) {
		return new ModelAndView(ITEM_URI + "view","item",item);
	}

	@GetMapping("/novo")
	public String createForm(@ModelAttribute Products item) {
		return ITEM_URI + "form";
	}

	@PostMapping(params = "form")
	public ModelAndView create(@Valid Products item,BindingResult result,RedirectAttributes redirect) {
		if (result.hasErrors()) { return new ModelAndView(ITEM_URI + "form","formErrors",result.getAllErrors()); }
		item = this.itemRepository.save(item);
		redirect.addFlashAttribute("globalMessage","Item gravado com sucesso");
		return new ModelAndView("redirect:/" + ITEM_URI + "{item.id}","item.id",item.getId());
	}

	@GetMapping(value = "remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id,RedirectAttributes redirect) {
		this.itemRepository.deleteById(id);
		Iterable<Products> itens = this.itemRepository.findAll();
		
		ModelAndView mv = new ModelAndView(ITEM_URI + "list","itens",itens);
		mv.addObject("globalMessage","Item removido com sucesso");
	
		return mv;
	}

	@GetMapping(value = "alterar/{id}")
	public ModelAndView alterarForm(@PathVariable("id") Products item) {
		return new ModelAndView(ITEM_URI + "form","item",item);
	}

}
