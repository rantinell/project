package com.team1.mapper;

import java.util.List;

import com.team1.dto.ReplyVO;

public interface ReplyMapper {

	public List<ReplyVO> getMovieDetails(Long mi_num);
	
}
