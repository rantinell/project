package com.team5.service;

import java.util.List;

import com.team5.domain.Criteria;
import com.team5.domain.ReplyPageDTO;
import com.team5.domain.ReplyVO;

public interface ReplyService {
	
	public int register(ReplyVO vo);
	
	public ReplyVO get(Long c_num);
	
	public int remove(Long c_num);
	
	public int modify(ReplyVO vo);
	
	public List<ReplyVO> getList(Criteria cri, Long mi_nulm);
	
	public ReplyPageDTO getListPage(Criteria cri, Long mi_num);
}
