package com.team1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team1.dto.MemberVO;
import com.team1.service.MemberService;

import lombok.extern.log4j.Log4j;
//import lombok.extern.log4j.Log4j2;

@Controller
@Log4j
@RequestMapping(value = "/movie/*", method = RequestMethod.GET)
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/createMemberForm")
	public String createMemberForm() {
		log.info("create member.....");
		return "signUp";
	}
	
	@PostMapping("/signUp")
//	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String createMember(@ModelAttribute MemberVO memberVO) {
		log.info("signUp");
		log.info("m_id : " + memberVO.getM_id());
		
		String nextPage = "movie/loginForm";
		
		int result = memberService.createMember(memberVO);
		log.info(result);
		if(result<0) {
			nextPage = "movie/sigeUp";
		}
		
		return nextPage;
	}
	
	@GetMapping(value = "/loginForm")
//	@RequestMapping(value = "/loginForm", method = {RequestMethod.POST, RequestMethod.GET})
	public String loginForm() {
		log.info("login.....");
		return "login";
	}
	
	@PostMapping(value = "/login")
	public String Longin(MemberVO memberVO, HttpSession session) {
		String nextPage="redirect:/movie";
		MemberVO loginedMemberVO = memberService.login(memberVO);
		
		if(loginedMemberVO == null) {
			nextPage = "login";
		}else {
			session.setAttribute("loginedMemberVO", loginedMemberVO);
			session.setMaxInactiveInterval(60 * 30);
		}
		return nextPage;
	}
	
	@GetMapping("/home")
	public String home() {
		log.info("movie home");
		return "main";
	}
	
	@GetMapping("/memberInfo")
	public String memberInfo(HttpSession session) {
		log.info("member info.....");
		MemberVO loginedMemberVO = (MemberVO) session.getAttribute("loginedMemberVO");
		return "userdetails";
	}
	
	/* userdetails 페이지에서 정보 수정
	 * @GetMapping("/modifyMemberForm") public void modifyMemberForm(HttpSession
	 * session) { log.info("modify member....."); MemberVO loginedMemberVO =
	 * (MemberVO) session.getAttribute("loginedMemberVO"); }
	 */
	
	@PostMapping("/memberInfo")
	public String modifyMember(MemberVO memberVO, HttpSession session) {
		String nextPage = "userdetails";
		
		int result = memberService.modifyMember(memberVO);
		
		if(result > 0) {
			MemberVO loginedMemberVO = memberService.getMemberByNum(memberVO.getM_num());
			
			session.setAttribute("loginedMemberVO", loginedMemberVO);
			session.setMaxInactiveInterval(60 * 30);
		}else { 
			nextPage = "movie/member/modifyFailed";
			}
			 
		return nextPage;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("logout.....");
		String nextPage = "redirect:/";
		
		session.removeAttribute("loginedMemberVO");
		
		return nextPage;
	}
	
	
	
}