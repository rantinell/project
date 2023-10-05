package com.team1.repositroy;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.github.mustachejava.Code;
import com.team1.dto.MovieVo;



@Repository
public interface MovieRepository  {
	
	List<MovieVo> getAllMovieList();	

    boolean existsByRating(Code rating);

    void deleteByMovieId(Long movieId);
    
    //랭크 리스트도 필요한 rk 
    List<MovieVo> getRankList();  

    List<MovieVo> getMovieListByCategory(String category);    
    List<MovieVo> getMovieListByFilter(Map<String, List<String>> filter);
    MovieVo getBookById(String movie);
    void setUpdateMovie(MovieVo movie);
    void setDeleteMovie(String movieID); 

}