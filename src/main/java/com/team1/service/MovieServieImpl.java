package com.team1.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.team1.dto.MovieVo;
import com.team1.dto.Criteria;
import com.team1.mapper.MovieMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@Primary
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
	public List<MovieVo> getCommingList(){
		
		log.info("getExpectedList........");
		
		return movieMapper.getCommingList();
		
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
	
	@Override
	public int getTotal(Criteria cri) {
		
		log.info("get total count");

		return movieMapper.getTotal(cri);
	}

	@Override
	public List<MovieVo> getSearchList(Criteria cri) {
		
		log.info("get List with criteria : " + cri);
		
		return movieMapper.getSearchList(cri);
	}

	@Override
	public Integer insertMovieDetails(MovieVo movieVo) {
		
		log.info("insertMovieDetails........");
		
		return movieMapper.insertMovieDetails(movieVo);
	}
	
	@Override
	public Integer insertMovieInfo(MovieVo movieVo) {
		
		log.info("insertMovieInfo........");
		String fileName = movieVo.getMi_thumbnail().getOriginalFilename();
		
		
		return movieMapper.insertMovieInfo(movieVo, fileName);
	}
	

}
