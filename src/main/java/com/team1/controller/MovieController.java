package com.team1.controller;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/*")
public class MovieController {
	@GetMapping("/main")
	public void mainpage() {
		log.info("main....");
	}
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access denied : " + auth);
		model.addAttribute("msg", "Access Denied");
	}	
	
	@GetMapping("/login")
	public void loginInput(String error, String logout, Model model) {
		log.info("error: " + error);
		log.info("logout: " + logout);
		
		if(error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "Logout!!");
		}
	}
	
	@GetMapping("/logout")
	public void logoutGet() {
		log.info("logout");
	}
	
	@PostMapping("/logout")
	public void logoutPost() {
		log.info("post logout");
	}
	
	@GetMapping("/signUp")
	public void signUp() {
		
	}
	
	
	@GetMapping("/board/ranking")
	public void ranking() {
		
	}
}
