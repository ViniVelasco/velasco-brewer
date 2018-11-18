package com.velasco.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.model.User;
import com.velasco.brewer.repository.Grupos;
import com.velasco.brewer.service.UserRegisterService;
import com.velasco.brewer.service.exception.EmailUserAlreadyRegisteredException;
import com.velasco.brewer.service.exception.PasswordRequiredUserException;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Grupos groups;
	
	@Autowired
	private UserRegisterService userRegisterService;

	@RequestMapping("/new")
	public ModelAndView create(User user) {
		ModelAndView mv = new ModelAndView("user/UserRegister");
		mv.addObject("groups", groups.findAll());
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView register(@Valid User user, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return create(user);
		}
		
		try {
			userRegisterService.save(user);
		} catch(EmailUserAlreadyRegisteredException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return create(user);
		} catch(PasswordRequiredUserException e) {
			result.rejectValue("password", e.getMessage(), e.getMessage());
			return create(user);
		}
		
		attributes.addFlashAttribute("message", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/users/new");
	}
	
}
