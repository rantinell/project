package com.team1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team1.dto.MovieVo;
import com.team1.dto.ReplyVO;
import com.team1.service.MovieService;
import com.team1.service.ReplyService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/movie/reply/")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private MovieService movieService;
	
	//코멘트 가져오기
	@GetMapping(value = "/{mi_num}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String movieDetails(@PathVariable("mi_num") Long mi_num, Model model, MovieVo movieVo) {
		System.out.println("[ReplyController] movieDetails()");
		String nextPage = "board/moviedetails";
		
		List<ReplyVO> commentList = replyService.getMovieDetails(mi_num);
		MovieVo movie = movieService.getMovieDetails(mi_num);
		
		log.info(movieVo.getMi_num().getClass());
		log.info(movieVo.getMi_num());
		log.info(mi_num.getClass());
		log.info(mi_num);
		model.addAttribute("comment", commentList);
		model.addAttribute("movie", movie);
		
		//나중에 주석처리 
		for (ReplyVO replyVO : commentList) {
			log.info(replyVO);
		}
		log.info(movie);
		
		return nextPage;
	}

}
