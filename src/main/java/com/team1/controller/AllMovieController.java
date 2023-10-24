package com.team1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team1.dto.MovieVo;
import com.team1.dto.PageDTO;
import com.team1.dto.Criteria;
import com.team1.dto.MemberVO;
import com.team1.service.MovieService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/movie/*")
public class AllMovieController {

	@Autowired
	private MovieService movieService;

	// 랭크 페이지
	@GetMapping("/rank")
	public String rankPage(Model model) {
		System.out.println("[AllMovieController] RackPage()");

		List<MovieVo> list = movieService.getRankList();

		model.addAttribute("rankingmovie", list);

//		 나중에 주석처리 
		for (MovieVo movieVo : list) {
			log.info(movieVo);
		}

//		String nextPage = "movie/test";
		String nextPage = "board/ranking";
		return nextPage;
	}

	// 상영예정 페이지
	@GetMapping("/comming")
	public String commingPage(Model model) {
		System.out.println("[AllMovieController] commingPage()");

		List<MovieVo> list = movieService.getCommingList();

		model.addAttribute("comming", list);

		// 나중에 주석처리
		for (MovieVo movieVo : list) {
			log.info(movieVo);
		}

//		String nextPage = "movie/test";
		String nextPage = "board/commingsoon";

		return nextPage;
	}

	// 추천영화 페이지
	@GetMapping("/recommend")
	public String recommendPage(@ModelAttribute("member") MemberVO member, Model model) {
		System.out.println(member);
		System.out.println("[AllMovieController] RecommendPage()");

//		List<MovieVo> list = movieService.getRecommendList(2);
		log.info("선호장르 번호 : " + member.getG_num());
		List<MovieVo> list = movieService.getRecommendList(member.getG_num());

		model.addAttribute("recommend", list);

		// 나중에 주석처리
		for (MovieVo movieVo : list) {
			log.info(movieVo);
		}

//		String nextPage = "movie/test";
		String nextPage = "board/recommend";

		return nextPage;
	}

	//검색 결과 페이지
	@GetMapping("/search")
	public String movieSearchList(Model model, @ModelAttribute("cri") Criteria cri) throws Exception {

		log.info("[AllMoviecontroller] movieSearchList()");

		model.addAttribute("search", movieService.getSearchList(cri));

		List<MovieVo> list = movieService.getSearchList(cri);
		for (MovieVo movieVo : list) {
			log.info(movieVo);
		}

		int total = movieService.getTotal(cri);
		log.info("total : " + total);

//		PageDTO pageMaker = new PageDTO(cri, 5);
		PageDTO pageMaker = new PageDTO(cri, total);

		model.addAttribute("pageMaker", pageMaker);
		log.info("amount : " + pageMaker.getCri().getAmount());
		log.info("pageNum : " + pageMaker.getCri().getPageNum());
		log.info("keyword : " + pageMaker.getCri().getKeyword());
		log.info("type : " + pageMaker.getCri().getType());
		log.info("listLink : " + pageMaker.getCri().getListLink());

		String nextPage = "board/search";

		return nextPage;
	}

	// 현제 상영 목록 페이지
	@GetMapping("/now")
	public String nowPage(Model model) {
		System.out.println("[AllMovieController] nowPage()");

		List<MovieVo> list = movieService.getMovieList();

		model.addAttribute("now", list);

//		나중에 주석처리 
		for (MovieVo movieVo : list) {
			log.info(movieVo);
		}

//		String nextPage = "movie/test";
		String nextPage = "board/nowplaying";
		return nextPage;
	}

}
