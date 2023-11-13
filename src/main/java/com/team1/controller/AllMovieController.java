package com.team1.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team1.dto.MovieVo;
import com.team1.dto.PageDTO;
import com.team1.dto.ReplyVO;
import com.team1.dto.Criteria;
import com.team1.dto.MemberVO;
import com.team1.service.MovieService;
import com.team1.service.ReplyService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/movie/*")
public class AllMovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ReplyService service;

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
	public String recommendPage(Model model, Principal principal) {
		System.out.println("[AllMovieController] RecommendPage()");

		if (principal != null) {
			String id = principal.getName();
			System.out.println("recommendPage id : " + id);

			MemberVO member = movieService.getGnum(id);
			System.out.println(member);

			log.info("선호장르 번호 : " + member.getG_num());
			List<MovieVo> list = movieService.getRecommendList(member.getG_num());

			model.addAttribute("recommend", list);

			// 나중에 주석처리
			for (MovieVo movieVo : list) {
				log.info(movieVo);
			}
		}else {
			List<MovieVo> list = movieService.getRankList();
			model.addAttribute("recommend", list);
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
	
	@GetMapping(value = "/{mi_num}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String movieDetails(@PathVariable("mi_num") Long mi_num, Model model, MovieVo movieVo, Principal principal) {
		System.out.println("[AllmovieController] movieDetails()");
		String nextPage = "board/moviedetails";

		if (principal != null) {
			String username = principal.getName();
			log.info("user id : " + username);
		}

		MovieVo movie = movieService.getMovieDetails(mi_num);
		List<ReplyVO> commentList = service.getMovieDetails(mi_num);

		log.info(movieVo.getMi_num());
		log.info(mi_num);

		model.addAttribute("movie", movie);
		model.addAttribute("comment", commentList);

		// 나중에 주석처리
		for (ReplyVO replyVO : commentList) {
			log.info(replyVO);
		}
		log.info(movie);

		return nextPage;
	}

}
