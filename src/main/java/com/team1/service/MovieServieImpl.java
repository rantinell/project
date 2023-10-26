package com.team1.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.team1.dto.MovieVo;
import com.team1.dto.Criteria;
import com.team1.dto.MemberVO;
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
	public List<MovieVo> getRecommendList(int g_num) {
		
		log.info("getRecommendList............");
		
		return movieMapper.getRecommendList(g_num);
	}
	
	@Override
	public MemberVO getGnum(String m_id) {
		log.info("get genre......");
		return movieMapper.getGnum(m_id);
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
	public void insertMovieDetails(MovieVo movieVo) {
		
		log.info("insertMovieDetails........");
		
		movieMapper.insertMovieDetails(movieVo);
	}
	
	@Override
	public void insertMovieInfo(MovieVo movieVo) {
		
		log.info("service insertMovieInfo........");
		System.out.println("service insertMovieInfo........");
		
		String oriFileName = movieVo.getFileName().getOriginalFilename();
		movieVo.setMi_thumbnail(oriFileName);
		
		System.out.println(oriFileName);
		System.out.println(movieVo);
		movieMapper.insertMovieInfo(movieVo);
	}

	@Override
	public MovieVo getMovieDetails(Long mi_num) {

		log.info("getMovieDetails.......");
		
		return movieMapper.getMovieDetails(mi_num);
	}

	

}
