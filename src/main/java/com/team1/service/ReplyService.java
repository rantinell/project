package com.team1.service;

import java.util.List;

import com.team1.dto.Criteria;
import com.team1.dto.MovieVo;
import com.team1.dto.ReplyPageDTO;
import com.team1.dto.ReplyVO;

public interface ReplyService {

	List<ReplyVO> getMovieDetails(Long mi_num);
	
	public int register(ReplyVO vo);

	public ReplyVO get(Long c_num);

	public int modify(ReplyVO vo);

	public int remove(Long c_num);

	public List<ReplyVO> getList(Criteria cri, Long mi_num);
	
	public ReplyPageDTO getListPage(Criteria cri, Long mi_num);

	public int getCountCpoint(ReplyVO vo);

	public void updateNewTotalPoint(float newTotalPoint);
}
