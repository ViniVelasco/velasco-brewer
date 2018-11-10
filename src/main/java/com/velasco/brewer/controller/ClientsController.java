package com.velasco.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.model.Client;
import com.velasco.brewer.model.PeopleTyple;
import com.velasco.brewer.repository.States;

@Controller
@RequestMapping("/clients")
public class ClientsController {
	
	@Autowired
	private States states;

	
	@RequestMapping("/new")
	public ModelAndView create(Client client) {
		//model.addAttribute(new Beer()); // Make object available to dispatcher
		ModelAndView mv = new ModelAndView("client/ClientRegister");
		
		mv.addObject("peopleTypes", PeopleTyple.values());
		mv.addObject("states", states.findAll());
		
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView save(@Valid Client client, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return create(client);
		}
		
		
		attributes.addFlashAttribute("message", "Cliente salvo com sucesso");
		return new ModelAndView("redirect:/clients/new");
	}
	
}
