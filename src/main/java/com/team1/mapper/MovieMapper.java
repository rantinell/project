package com.team1.mapper;

import java.util.List;

import com.team1.dto.MovieVo;
import com.team1.dto.Criteria;

public interface MovieMapper {
	
	public List<MovieVo> getMovieList();
	
	public List<MovieVo> getRankList();
	
	public List<MovieVo> getCommingList();

	public List<MovieVo> getRecommendList();
	public List<MovieVo> getRecommendList(int g_num);
	
	public List<MovieVo> getSearchList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	public MovieVo insertMovieDetails(MovieVo movieVo);
	public MovieVo insertMovieInfo(MovieVo movieVo, String originFileName);
	
	public MovieVo getMovieDetails(Long mi_num);
	
	//영현씨 추가부분
	public List<MovieVo> getMovieList_dto();
	
//	public List<MovieVo> getcategory();

	public List<MovieVo> getMovieById();

//	public List<MovieVo> getMovieListByFilter();

	public List<MovieVo> setNewMovie();

	public List<MovieVo> setUpdateMovie();

	public List<MovieVo> setDeleteMovie();

	
}
