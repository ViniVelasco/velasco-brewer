package com.velasco.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.model.Client;

@Controller
public class ClientsController {
	
	@RequestMapping("/clients/new")
	public String create(Client client) {
		//model.addAttribute(new Beer()); // Make object available to dispatcher
		return "client/ClientRegister";
	}
	
	@RequestMapping(value = "/client/new", method = RequestMethod.POST)
	public String register(@Valid Client client, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(client);
			return create(client);
		}
		
		attributes.addFlashAttribute("message", "Cliente salvo com sucesso");
		return "redirect:/client/new";
	}
	
}
