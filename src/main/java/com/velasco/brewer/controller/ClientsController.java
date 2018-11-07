package com.velasco.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
}
