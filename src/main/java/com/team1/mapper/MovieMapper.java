package com.team1.mapper;

import java.util.List;

import com.team1.dto.MovieVo;
import com.team1.dto.MemberVo;

public interface MovieMapper {
	
	public List<MovieVo> getMovieList();
	
	public List<MovieVo> getRankList();

	public List<MovieVo> getRecommendList();
	public List<MovieVo> getRecommendList(int g_num);
	
	public List<MemberVo> getMemberList();
	

}
