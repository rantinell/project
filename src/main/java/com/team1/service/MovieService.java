package com.team1.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.team1.dto.MovieVo;
import com.team1.dto.Criteria;

public interface MovieService {
	
//	public MovieVo getMovie(Long md_num);
//	public Test getTest(Long id);
	
	public List<MovieVo> getMovieList();
	
	public List<MovieVo> getRankList();
	
	public List<MovieVo> getCommingList();
	
	public List<MovieVo> getRecommendList();
	public List<MovieVo> getRecommendList(int g_num);

	public int getTotal(Criteria cri);
	public List<MovieVo> getSearchList(Criteria cri);
	
	//insert
	public Integer insertMovieDetails(MovieVo movieVo);
	public Integer insertMovieInfo(MovieVo movieVo);
	
}
