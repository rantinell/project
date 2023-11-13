package com.team1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team1.dto.Criteria;
import com.team1.dto.ReplyVO;

public interface ReplyMapper {

	public List<ReplyVO> getMovieDetails(Long mi_num);
	
	public int insert(ReplyVO vo);

	public ReplyVO read(Long c_num);

	public int delete(Long c_num);

	public int update(ReplyVO reply);

	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("mi_num") Long mi_num);

	public int getCountByBno(Long mi_num);

	public void updateNewTotalPoint(float newTotalPoint);

	public float getTotalPoint(Long mi_num);
	
}
