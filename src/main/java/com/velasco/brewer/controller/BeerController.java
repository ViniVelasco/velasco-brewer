package com.velasco.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.model.Beer;
import com.velasco.brewer.repository.Beers;

@Controller
public class BeerController {

	@Autowired
	private Beers beers;
	
	@RequestMapping("/beer/new")
	public String create(Beer beer) {
		return "beer/BeerRegister";
	}
	
	@RequestMapping(value = "/beer/new", method = RequestMethod.POST)
	public String register(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(beer);
			return create(beer);
		}
		
		attributes.addFlashAttribute("message", "Cerveja salva com sucesso");
		return "redirect:/beer/new";
	}

}
