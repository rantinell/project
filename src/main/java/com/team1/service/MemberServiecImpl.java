package com.team1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.dto.MemberVo;
import com.team1.dto.MovieVo;
import com.team1.mapper.MovieMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiecImpl implements MemberService {
	
	@Setter(onMethod_ = @Autowired)
	private MovieMapper movieMapper;
	
	@Setter(onMethod_ = @Autowired)
	private MovieService movieService;
	@Override
	public List<MemberVo> getMemberList() {
		
		log.info("getMemberList........." + movieMapper.getMemberList());
		
		return movieMapper.getMemberList();
	}
}
