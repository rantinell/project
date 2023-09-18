package com.team5.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/movie/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/createMemberForm")
	public void createMemberForm() {
		log.info("create member.....");
	}
	
	@PostMapping("/createMember")
	public String createMember(MemberVO memberVO) {
		String nextPage = "movie/member/loginForm";
		
		int result = memberService.createMember(memberVO);
		if(result<0)
			nextPage = "create_member_failed";
		
		return nextPage;
	}
	
	@GetMapping("/loginForm")
	public void loginForm() {
		log.info("login.....");
	}
	
	@PostMapping("/login")
	public String Longin(MemberVO memberVO, HttpSession session) {
		String nextPage="movie/member/home";
		MemberVO loginedMemberVO = memberService.login(memberVO);
		
		if(loginedMemberVO == null) {
			nextPage = "login_failed";
		}else {
			session.setAttribute("loginedMemberVO", loginedMemberVO);
			session.setMaxInactiveInterval(60 * 30);
		}
		return nextPage;
	}
	
	@GetMapping("/memberInfo")
	public void memberInfo(HttpSession session) {
		log.info("member info.....");
		MemberVO loginedMemberVO = (MemberVO) session.getAttribute("loginedMemberVO");
	}
	
	@GetMapping("/modifyMemberForm")
	public void modifyMemberForm(HttpSession session) {		
		log.info("modify member.....");
		MemberVO loginedMemberVO = (MemberVO) session.getAttribute("loginedMemberVO");
	}
	
	@PostMapping("/modifyMember")
	public String modifyMember(MemberVO memberVO, HttpSession session) {
		String nextPage = "movie/member/memberInfo";
		
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
