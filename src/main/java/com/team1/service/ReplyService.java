package com.team1.service;

import java.util.List;

import com.team1.dto.MovieVo;
import com.team1.dto.ReplyVO;

public interface ReplyService {

	List<ReplyVO> getMovieDetails(Long mi_num);

}
