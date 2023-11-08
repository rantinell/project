package com.team1.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team1.dto.MemberVO;
import com.team1.dto.MovieVo;
import com.team1.service.MemberService;

import lombok.extern.log4j.Log4j;
//import lombok.extern.log4j.Log4j2;

@Controller
@Log4j
@RequestMapping("/movie/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	// 추가
	@Autowired
	BCryptPasswordEncoder pwEncoder;

	@GetMapping("/createMemberForm")
	public String createMemberForm() {
		log.info("create member.....");
		return "signUp";
	}

	// 추가
	@RequestMapping(value = "/idChk", method = RequestMethod.POST)
	public @ResponseBody int idChk(String m_id) throws Exception {
		int result = memberService.idChk(m_id);
		return result;
	}

	@PostMapping(value = "/signUp")
	public String createMember(MemberVO memberVO) {
		log.info("signUp....");
//		String nextPage = "/movie/member/login";
//		
//		log.info("memberData1 : " + memberVO);
//		
////		int result = memberService.createMember(memberVO);
//		int result = memberService.createMember(memberVO);
//		
//		log.info("memberData2 : " + memberVO);
//		log.info("result : " + result);
//		
//		log.info(result);
//		if(result<0) {
//			nextPage = "/movie/member/sigeUp";
//		}
//		
//		return nextPage;
		log.info("post signUp....");
		int result = memberService.idChk(memberVO.getM_id());
		memberVO.setM_pw(pwEncoder.encode(memberVO.getM_pw()));
		memberService.signUp(memberVO);
		return "redirect:/movie";
	}

//	@GetMapping(value = "/loginForm")
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET })
	public String loginForm() {
		log.info("login.....");
		return "/login";
	}

//	@PostMapping(value = "/login")
//	@RequestMapping(value = "/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String Longin(@ModelAttribute("username") String m_id, @ModelAttribute("password") String m_pw,
			HttpSession session, HttpServletRequest request) {
		String nextPage = "/movie";

		System.out.println("logindata1 : " + m_id + "||" + m_pw);

		MemberVO loginedMemberVO = memberService.login(m_id);

		System.out.println("logindata2 : " + loginedMemberVO);
		log.warn("logindata : " + loginedMemberVO);

		if (loginedMemberVO == null) {
			nextPage = "/loginForm";
		} else {
			session = request.getSession();
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
	public String memberInfo(Principal principal, Model model) {
		log.info("member info.....");
		String userid = principal.getName();
		MemberVO vo = memberService.get(userid);
		model.addAttribute("member", vo);
		return "userdetails";
	}

//	userdetails 페이지에서 정보 수정 
//	@GetMapping("/modifyMemberForm")
//	public void modifyMemberForm(HttpSession session) {
//		log.info("modify member.....");
//		MemberVO loginedMemberVO = (MemberVO) session.getAttribute("loginedMemberVO");
//	}

	@PostMapping("/memberInfo")
	public String modifyMember(MemberVO memberVO, HttpSession session) {
		String nextPage = "userdetails";

		int result = memberService.modifyMember(memberVO);

		if (result > 0) {
			MemberVO loginedMemberVO = memberService.getMemberByNum(memberVO.getM_num());

			session.setAttribute("loginedMemberVO", loginedMemberVO);
			session.setMaxInactiveInterval(60 * 30);
		} else {
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
	public String logout() {
		log.info("logout.....");

		return "/logout";
	}

	@PostMapping("/logout")
	public String logoutPost(HttpSession session) {
		log.info("post logout");

		session.removeAttribute("loginedMemberVO");
		session.invalidate();

		return "redirect:/movie";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public String adminpage() {
		log.info("admin page...");
		return "/admin";
	}

}