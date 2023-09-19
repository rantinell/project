package com.team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team5.domain.Criteria;
import com.team5.domain.PageDTO;
import com.team5.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/board/*")
@Log4j
@Controller
public class MovieController {
	
	private BoardService service;
	
	@GetMapping("/ranking")
	public void ranking() {
		log.info("ranking page...");
	}
	
	@GetMapping("/nowplaying")
	public void nowplaying() {
		log.info("nowplaying page...");
	}
	
	@GetMapping("/commingsoon")
	public void comming() {
		log.info("commingsoon page...");
	}
	
	@GetMapping("/recommend")
	public void recommend() {
		log.info("recommend page...");
	}
	
	@GetMapping("/search")
	public void list(Criteria cri, Model model) {
		/*
		 * log.info("list: " + cri); model.addAttribute("list", service.getList(cri));
		 * //model.addAttribute("pageMaker", new PageDTO(cri, 123));
		 * 
		 * int total = service.getTotal(cri); log.info("total: " + total);
		 * model.addAttribute("pageMaker", new PageDTO(cri, total));
		 */
	}
}
