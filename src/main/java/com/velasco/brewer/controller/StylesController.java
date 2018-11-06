package com.velasco.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.brewer.controller.page.PageWrapper;
import com.velasco.brewer.exception.NameStyleAlreadyRegisteredException;
import com.velasco.brewer.model.Style;
import com.velasco.brewer.repository.Styles;
import com.velasco.brewer.repository.filter.StyleFilter;
import com.velasco.brewer.service.StyleRegisterService;


@Controller
@RequestMapping("/styles")
public class StylesController {

	@Autowired
	private StyleRegisterService styleRegisterService;
	
	@Autowired
	private Styles styles;
	
	@RequestMapping("/new")
	public ModelAndView create(Style style) {
		//model.addAttribute(new Beer()); // Make object available to dispatcher
		return new ModelAndView("style/StyleRegister");
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView register(@Valid Style style, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return create(style);
		}
		
		try {
			styleRegisterService.save(style);
		} catch (NameStyleAlreadyRegisteredException e) {
			result.rejectValue("name", e.getMessage(), e.getMessage());
			return create(style);
		}
		
		attributes.addFlashAttribute("message", "Estilo salvo com sucesso");
		return new ModelAndView("redirect:/styles/new");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Style style, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
		}
		
		style = styleRegisterService.save(style);
		return ResponseEntity.ok(style);
	}
	
	@GetMapping
	public ModelAndView search(StyleFilter styleFilter, BindingResult result,
			@PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("style/StyleSearch");
		
		PageWrapper<Style> pageWrapper = new PageWrapper<>(styles.filter(styleFilter, pageable)
				, httpServletRequest);
		mv.addObject("page", pageWrapper);
		return mv;
	}
}
