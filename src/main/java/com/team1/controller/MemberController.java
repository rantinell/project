package com.team1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team1.dto.MemberVO;
import com.team1.service.MemberService;

import lombok.extern.log4j.Log4j;
//import lombok.extern.log4j.Log4j2;

@Controller
@Log4j
@RequestMapping("/movie/member")
public class MemberController {
	
	@Autowired
//	MemberService memberService;
	MemberService memberService;
	
	@GetMapping("/createMemberForm")
	public String createMemberForm() {
		log.info("create member.....");
		return "signUp";
	}
	
	@PostMapping(value = "/signUp")
	public String createMember(MemberVO memberVO) {
		log.info("signUp....");
		String nextPage = "movie/loginForm";
		
		log.info("memberData1 : " + memberVO);
		
//		int result = memberService.createMember(memberVO);
		int result = memberService.createMember(memberVO);
		
		log.info("memberData2 : " + memberVO);
		log.info("result : " + result);
		
		log.info(result);
		if(result<0) {
			nextPage = "movie/sigeUp";
		}
		
		return nextPage;
	}
	
//	@GetMapping(value = "/loginForm")
	@RequestMapping(value = "/loginForm", method = {RequestMethod.POST, RequestMethod.GET})
	public String loginForm() {
		log.info("login.....");
		return "/login";
	}
	
	@PostMapping(value = "/login")
	public String Longin(MemberVO memberVO, HttpSession session) {
		String nextPage="/movie";
		MemberVO loginedMemberVO = memberService.login(memberVO);
		
		log.info("logindata : " + loginedMemberVO);
		
		if(loginedMemberVO == null) {
			nextPage = "/loginForm";
		}else {
			session.setAttribute("loginedMemberVO", loginedMemberVO);
			session.setMaxInactiveInterval(60 * 30);
		}
		return nextPage;
	}
	
//	@GetMapping("/home")
//	public String home() {
//		log.info("movie home");
//		return "main";
//	}
	
	@PreAuthorize("isAuthenticated()")
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
	
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		log.info("logout.....");
//		String nextPage = "redirect:/";
//		
//		session.removeAttribute("loginedMemberVO");
//		
//		return nextPage;
//	}
	
	@GetMapping("/logout")
	public void logout(HttpSession session) {
		log.info("logout.....");
	}
}