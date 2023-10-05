package com.team1.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team1.dto.MovieVo;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MovieMapperTest {
	
	@Setter(onMethod_ = @Autowired)
	private MovieMapper movieMapper;
	
//	@Test
//	public void testGetList() {
//		log.info(movieMapper.getClass().getName());
//		movieMapper.getMovieList().forEach(movie -> log.info(movie));
//	}
	
//	@Test
//	public void testMemberList() {
//		
//		log.info(movieMapper.getClass().getName());
//		movieMapper.getMemberTest().forEach(member -> log.info(member));
//		
//	}
	
//	@Test
//	public void testRecommendList() {
//		log.info(movieMapper.getClass().getName());
//		movieMapper.getRecommendList().forEach(test -> log.info(test));
//	}
	
}
