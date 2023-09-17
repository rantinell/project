package com.team1.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
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
	
	@PostMapping("/login")
	public void postLogin() {
		
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
	
	@GetMapping("/userdetails")
	public void userdetails() {
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void admin() {
		
	}
}
