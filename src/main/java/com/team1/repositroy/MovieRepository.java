package com.team1.repositroy;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.github.mustachejava.Code;
import com.team1.dto.MovieDTO;


@Repository
public interface MovieRepository  {
	
	List<MovieDTO> getAllMovieList();	

    boolean existsByRating(Code rating);

    void deleteByMovieId(Long movieId);
    
    //랭크 리스트도 필요한 rk 
    List<MovieDTO> getRankList();  


}