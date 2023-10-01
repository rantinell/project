package com.team1.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.team1.dto.MovieVo;
import com.team1.dto.MovieDTO;
import com.team1.dto.Criteria;
import com.team1.dto.MemberVo;

public interface MovieService {
	
//	public MovieVo getMovie(Long md_num);
//	public Test getTest(Long id);
	
//	public List<MovieVo> getMovieList();
	
	public List<MovieVo> getRankList();
	
	public List<MovieVo> getCommingList();
	
	public List<MovieVo> getRecommendList();
	public List<MovieVo> getRecommendList(int g_num);

	public int getTotal(Criteria cri);
	public List<MovieVo> getSearchList(Criteria cri);
	
	//insert
	public Integer insertMovieDetails(MovieVo movieVo);
	public Integer insertMovieInfo(MovieVo movieVo);
	
	//수정씨 추가부분
	public List<MovieDTO> getMovieList();
	
//	List<MovieDTO> getRankList();
	
	List<MovieDTO> getAllMovieList();
	
	List<MovieDTO> getMovieListByCategory(String category);	
	
	List<MovieDTO> getMovieListByFilter(Map<String, List<String>> filter);		
	 
	List<MovieDTO> getMovieById(String MovieId);
	
	List<MovieDTO> setNewMovie(MovieDTO movieDTO);
	
	List<MovieDTO> setUpdateMovie(MovieDTO movieDTO);
	
	List<MovieDTO> setDeleteMovie(String movieID);

	List<MovieDTO> getScreeningList();
}
