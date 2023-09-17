package com.team1.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@RequestMapping("/board/*")
@Log4j
@Controller
public class MovieController {
	@GetMapping("/board/ranking")
	public void ranking() {
		
	}
}
