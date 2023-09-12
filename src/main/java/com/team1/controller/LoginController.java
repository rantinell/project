package com.team1.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
	
	@GetMapping("/login")
	public void login() {
		System.out.println("로그인 구현");
	}
}
