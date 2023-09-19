package com.team5.service;

import java.util.List;

import com.team5.domain.BoardVO;
import com.team5.domain.Criteria;

public interface BoardService {
	public void register(BoardVO board);
	public List<BoardVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
}
