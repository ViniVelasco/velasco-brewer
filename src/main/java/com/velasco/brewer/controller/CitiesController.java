package com.velasco.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.model.City;

@Controller
public class CitiesController {
	@RequestMapping("/city/new")
	public String create(City city) {
		//model.addAttribute(new Beer()); // Make object available to dispatcher
		return "city/CityRegister";
	}
	
	@RequestMapping(value = "/city/new", method = RequestMethod.POST)
	public String register(@Valid City city, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(city);
			return create(city);
		}
		
		attributes.addFlashAttribute("message", "Cidade salva com sucesso");
		return "redirect:/city/new";
	}
}
