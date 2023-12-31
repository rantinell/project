package com.team1.mapper;

import java.util.List;

import com.team1.dto.MovieVo;
import com.team1.dto.Criteria;
import com.team1.dto.MemberVO;

public interface MovieMapper {
	
	public List<MovieVo> getMovieList();
	
	public List<MovieVo> getRankList();
	
	public List<MovieVo> getCommingList();

//	public List<MovieVo> getRecommendList();
	public List<MovieVo> getRecommendList(int g_num);
	
	public List<MovieVo> getSearchList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	public void insertMovieDetails(MovieVo movieVo);
	public void insertMovieInfo(MovieVo movieVo);
	
	public MovieVo getMovieDetails(Long mi_num);

	public MemberVO getGnum(String m_id);
	
}
