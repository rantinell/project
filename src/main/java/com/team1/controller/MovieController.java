package com.team1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String index() {
		
		System.out.println("[MovieController] main()");
		
		String nextPage = "main";
		
		return nextPage;
	}
}
