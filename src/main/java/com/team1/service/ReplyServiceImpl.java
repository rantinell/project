package com.team1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.team1.dto.ReplyVO;
import com.team1.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@Primary
public class ReplyServiceImpl implements ReplyService {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper replyMapper;

	@Override
	public List<ReplyVO> getMovieDetails(Long mi_num) {

		log.info("getMovieDetails....");
		
		return replyMapper.getMovieDetails(mi_num);
	}

}
