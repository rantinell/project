package com.team5.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team5.domain.MemberVO;
import com.team5.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/")
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
	
	//아이디 중복 체크, 기존
	
	@RequestMapping(value="/idChk", method = RequestMethod.POST)
	public @ResponseBody int idChk(String m_id) throws Exception {
		int result = memberService.idChk(m_id);
		return result;	
	}
		
	//회원가입 post
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception {
		log.info("post signUp....");
		int result = memberService.idChk(vo.getM_id());
		vo.setM_pw(pwEncoder.encode(vo.getM_pw()));
		memberService.signUp(vo);
		return "/";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/userdetails")
	public void userdetails(Principal principal, Model model) {
		log.info("마이프로필 창으로 이동");
    log.info("유저아이디: " + principal.getName());
    String userid=principal.getName();
    MemberVO vo = memberService.get(userid);
    model.addAttribute("member", vo);
	}
	
	@PreAuthorize("hasAuthority('3')")
	@GetMapping("/admin")
	public void admin() {
		
	}
}
