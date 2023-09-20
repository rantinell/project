package com.team1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.dto.MovieVo;
import com.team1.dto.MemberVo;
import com.team1.mapper.MovieMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class MovieServieImpl implements MovieService {
	
	@Setter(onMethod_ = @Autowired)
	private MovieMapper movieMapper;

	@Override
	public List<MovieVo> getMovieList() {
		
		log.info("getMovieList.....");
		
		return movieMapper.getMovieList();
	}

	@Override
	public List<MovieVo> getRankList() {
		
		log.info("getRankList........");
		
		return movieMapper.getRankList();
	}
	
	@Override
	public List<MovieVo> getExpectedList(){
		
		log.info("getExpectedList........");
		
		return movieMapper.getExpectedList();
		
	}

	@Override
	public List<MovieVo> getRecommendList() {
		
		log.info("getRecommendList............");
		
		return movieMapper.getRecommendList();
	}
	
	@Override
	public List<MovieVo> getRecommendList(int g_num) {
		
		log.info("getRecommendList............");
		
		return movieMapper.getRecommendList(g_num);
	}
	
//	@Override
//	public List<MovieVo> getRecommendList() {
//		
//		log.info("getRecommendList........" + movieMapper.getRecommendList());
//		
//		return movieMapper.getRecommendList();
//	}
	
	
	
}
