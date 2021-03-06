 package com.velasco.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.controller.page.PageWrapper;
import com.velasco.brewer.model.Beer;
import com.velasco.brewer.model.Flavor;
import com.velasco.brewer.model.Origin;
import com.velasco.brewer.repository.Beers;
import com.velasco.brewer.repository.Styles;
import com.velasco.brewer.repository.filter.BeerFilter;
import com.velasco.brewer.service.BeerRegisterService;

@Controller
@RequestMapping("/beers")
public class BeersController {
	
	@Autowired
	private Styles styles;
	
	@Autowired
	private BeerRegisterService beerRegisterService;
	
	@Autowired
	private Beers beers;
	
	@RequestMapping("/new")
	public ModelAndView create(Beer beer) {
		ModelAndView mv = new ModelAndView("beer/BeerRegister");
		mv.addObject("flavors", Flavor.values());
		mv.addObject("styles", styles.findAll());
		mv.addObject("origins", Origin.values());
		
		return mv;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView register(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(beer);
			return create(beer);
		}

		beerRegisterService.save(beer);
		attributes.addFlashAttribute("message", "Cerveja salva com sucesso");
		return new ModelAndView("redirect:/beers/new");
	}
	
	@GetMapping
	public ModelAndView search(BeerFilter beerFilter, BindingResult result,
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("beer/BeerSearch");
		mv.addObject("flavors", Flavor.values());
		mv.addObject("styles", styles.findAll());
		mv.addObject("origins", Origin.values());
		
		PageWrapper<Beer> pageWrapper = new PageWrapper<>(beers.filter(beerFilter, pageable)
				, httpServletRequest);
		mv.addObject("page", pageWrapper);
		return mv;
	}

}
