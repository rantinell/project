package com.team1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team1.dto.MovieVo;
import com.team1.service.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value = {"", "/"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String index(Model model) {
		
		System.out.println("[MovieController] main()");
		
		String nextPage = "main";
		
		List<MovieVo> rankList = movieService.getRankList();
		List<MovieVo> commingList = movieService.getCommingList();
		List<MovieVo> nowList = movieService.getMovieList();
		
		model.addAttribute("rankingmovie", rankList);
		model.addAttribute("comming", commingList);
		model.addAttribute("now", nowList);
		
		return nextPage;
	}
}
