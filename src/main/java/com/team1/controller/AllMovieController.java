package com.team1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team1.dto.MovieVo;
import com.team1.dto.MemberVo;
import com.team1.service.MemberService;
import com.team1.service.MovieService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/movie/*")
public class AllMovieController {

	@Autowired
	private MovieService service;

	@Autowired
	private MemberService memberService;

	// 랭크 페이지
	@GetMapping("/rank")
	public String rankPage(Model model) {
		System.out.println("[AllMovieController] RackPage()");
		List<MovieVo> list = service.getRankList();

		model.addAttribute("rank", list);

		// 나중에 주석처리
		for (MovieVo movieVo : list) {
			log.info(movieVo);
		}

		String nextPage = "movie/test";
		return nextPage;
	}

	// 상영예정 페이지
	@GetMapping("/expected")
	public String expectedPage(Model model) {
		System.out.println("[AllMovieController] ExpectedPage()");

		List<MovieVo> list = service.getMovieList();

		model.addAttribute("expected", list);

		// 나중에 주석처리
		for (MovieVo movieVo : list) {
			log.info(movieVo);
		}

		String nextPage = "movie/test";

		return nextPage;
	}

	// 추천영화 페이지
	@GetMapping("/recommend")
	public String recommendPage(MemberVo member, Model model) {
		System.out.println("[AllMovieController] RecommendPage()");

//		List<MovieVo> list = service.getRecommendList(2);
		List<MovieVo> list = service.getRecommendList(member.getG_num());

		model.addAttribute("recommend", list);

		// 나중에 주석처리
		for (MovieVo movieVo : list) {
			log.info(movieVo);
		}

		String nextPage = "movie/test";

		return nextPage;
	}

}
