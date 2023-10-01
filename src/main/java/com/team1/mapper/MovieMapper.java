package com.team1.mapper;

import java.util.List;

import com.team1.dto.MovieVo;
import com.team1.dto.MovieDTO;
import com.team1.dto.Criteria;
import com.team1.dto.MemberVo;

public interface MovieMapper {
	
	public List<MovieVo> getMovieList();
	
	public List<MovieVo> getRankList();
	
	public List<MovieVo> getCommingList();

	public List<MovieVo> getRecommendList();
	public List<MovieVo> getRecommendList(int g_num);
	
	public List<MemberVo> getMemberList();
	
	public List<MovieVo> getSearchList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	public Integer insertMovieDetails(MovieVo movieVo);
	public Integer insertMovieInfo(MovieVo movieVo, String fileName);
	
	//수정씨 추가부분
	public List<MovieDTO> getMovieList_dto();
	
	public List<MovieDTO> getcategory();

	public List<MovieDTO> getMovieById();

	public List<MovieDTO> getMovieListByFilter();

	public List<MovieDTO> setNewMovie();

	public List<MovieDTO> setUpdateMovie();

	public List<MovieDTO> setDeleteMovie();
}
