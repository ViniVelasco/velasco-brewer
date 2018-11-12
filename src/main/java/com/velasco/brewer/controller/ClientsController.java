package com.velasco.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.controller.page.PageWrapper;
import com.velasco.brewer.model.Client;
import com.velasco.brewer.model.PeopleTyple;
import com.velasco.brewer.repository.Clients;
import com.velasco.brewer.repository.States;
import com.velasco.brewer.repository.filter.ClientFilter;
import com.velasco.brewer.service.ClientRegisterService;
import com.velasco.brewer.service.exception.CpfCnpjAlreadyRegisteredException;

@Controller
@RequestMapping("/clients")
public class ClientsController {
	
	@Autowired
	private States states;
	
	@Autowired
	private ClientRegisterService clientRegisterService;
	
	@Autowired
	private Clients clients;

	
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
		try {
			clientRegisterService.save(client);
		} catch(CpfCnpjAlreadyRegisteredException e) {
			result.rejectValue("cpfCnpj", e.getMessage(), e.getMessage());
			
			return create(client);
		}
		attributes.addFlashAttribute("message", "Cliente salvo com sucesso");
		return new ModelAndView("redirect:/clients/new");
	}
	
	@GetMapping
	public ModelAndView search(ClientFilter clientFilter, BindingResult result, 
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("client/ClientSearch");
		
		PageWrapper<Client> pageWrapper = new PageWrapper<>(clients.filter(clientFilter, pageable)
				, httpServletRequest);
		mv.addObject("page", pageWrapper);
		return mv;
	}
	
}
