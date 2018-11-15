package com.velasco.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.model.User;

@Controller
@RequestMapping("/users")
public class UsersController {

	@RequestMapping("/new")
	public ModelAndView create(User user) {
		ModelAndView mv = new ModelAndView("user/UserRegister");
		
		return mv;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView register(@Valid User user, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute(user);
			return create(user);
		}
		
		attributes.addFlashAttribute("message", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/user/new");
	}
	
}
