package com.velasco.brewer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.controller.page.PageWrapper;
import com.velasco.brewer.model.City;
import com.velasco.brewer.repository.Cities;
import com.velasco.brewer.repository.States;
import com.velasco.brewer.repository.filter.CityFilter;
import com.velasco.brewer.service.CitiesRegisterService;
import com.velasco.brewer.service.exception.NameCityAlreadyRegisteredException;

@Controller
@RequestMapping("cities")
public class CitiesController {
	
	@Autowired
	private Cities cities;
	
	@Autowired
	private CitiesRegisterService citiesService;

	@Autowired
	private States states;
	
	@RequestMapping("/new")
	public ModelAndView create(City city) {
		ModelAndView mv = new ModelAndView("city/CityRegister");
		
		mv.addObject("states", states.findAll());
		return mv;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView register(@Valid City city, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(city);
			return create(city);
		}
		try {
			citiesService.save(city);
		} catch (NameCityAlreadyRegisteredException e) {
			result.rejectValue("name", e.getMessage(), e.getMessage());
			return create(city);
		}
		
		attributes.addFlashAttribute("message", "Cidade salva com sucesso");
		return new ModelAndView("redirect:/cities/new");
	}
	
	@GetMapping
	public ModelAndView search(CityFilter cityFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("city/CitySearch");
		mv.addObject("states", states.findAll());
		
		PageWrapper<City> pageWrapper = new PageWrapper<>(cities.filter(cityFilter, pageable)
				, httpServletRequest);
		mv.addObject("page", pageWrapper);
		return mv;
	}
	
	// /brewer/cities?state=2
	@Cacheable("cities")
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
