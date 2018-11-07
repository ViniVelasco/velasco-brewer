package com.velasco.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.model.Client;
import com.velasco.brewer.model.PeopleTyple;

@Controller
@RequestMapping("/clients")
public class ClientsController {
	
	@RequestMapping("/new")
	public ModelAndView create(Client client) {
		//model.addAttribute(new Beer()); // Make object available to dispatcher
		ModelAndView mv = new ModelAndView("client/ClientRegister");
		mv.addObject("peopleTypes", PeopleTyple.values());
		return mv;
	}
	
}
