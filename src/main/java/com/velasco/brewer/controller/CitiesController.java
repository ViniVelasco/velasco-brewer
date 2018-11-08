package com.velasco.brewer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.model.City;
import com.velasco.brewer.repository.Cities;

@Controller
@RequestMapping("cities")
public class CitiesController {
	
	@Autowired
	private Cities cities;
	
	@RequestMapping("/new")
	public String create(City city) {
		//model.addAttribute(new Beer()); // Make object available to dispatcher
		return "city/CityRegister";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String register(@Valid City city, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(city);
			return create(city);
		}
		
		attributes.addFlashAttribute("message", "Cidade salva com sucesso");
		return "redirect:/city/new";
	}
	
	// /brewer/cities?state=2
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<City> searchByStateId(@RequestParam(name = "state", defaultValue = "-1") Long id) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return cities.findByStateId(id);
	}
}
