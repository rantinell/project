package com.team1.service;

import java.util.List;

import com.team1.dto.MovieVo;
import com.team1.dto.MemberVo;

public interface MovieService {
	
//	public MovieVo getMovie(Long md_num);
//	public Test getTest(Long id);
	
	public List<MovieVo> getMovieList();
	
	public List<MovieVo> getRankList();
	
	public List<MovieVo> getExpectedList();
	
	public List<MovieVo> getRecommendList();
	public List<MovieVo> getRecommendList(int g_num);

	
}
