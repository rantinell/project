package com.team1.service;

import java.util.List;

import com.team1.domain.BoardVO;
import com.team1.domain.Criteria;

public interface BoardService {
	public void register(BoardVO board);
	public List<BoardVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
}
