package com.velasco.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BrewerController {
	
	@RequestMapping("/beer/new")
	public String create() {
		return "beer/BeerRegister";
	}

}
