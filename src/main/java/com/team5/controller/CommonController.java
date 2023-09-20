package com.team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team5.domain.MemberVO;
import com.team5.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	@Autowired
	MemberService memberService;

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
		log.info("signUp....");
	}
	
	@RequestMapping("/signUp")
	public String postSignUp(MemberVO memberVO) throws Exception {
		log.info("post signUp...");
		int result = memberService.idChk(memberVO);
		try {
			if(result == 1) { // id 중복체크
				return "/signUp";
			} else if(result == 0) {
				String passBefore = memberVO.getM_id();
				String pwd = pwEncoder.encode(passBefore);
				memberVO.setM_pw(pwd);
				
				memberService.signUp(memberVO);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return "redirect:/";
	}
	
	@GetMapping("/userdetails")
	public void userdetails() {
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void admin() {
		
	}
}
