package com.velasco.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.model.Style;


@Controller
public class StyleController {

	@RequestMapping("/style/new")
	public String create(Style style) {
		//model.addAttribute(new Beer()); // Make object available to dispatcher
		return "style/StyleRegister";
	}
	
	@RequestMapping(value = "/style/new", method = RequestMethod.POST)
	public String register(@Valid Style style, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(style);
			return create(style);
		}
		
		attributes.addFlashAttribute("message", "Estilo salvo com sucesso");
		return "redirect:/style/new";
	}
}
