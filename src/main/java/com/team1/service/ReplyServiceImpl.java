package com.team1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team1.dto.Criteria;
import com.team1.dto.ReplyPageDTO;
import com.team1.dto.ReplyVO;
import com.team1.mapper.MovieMapper;
import com.team1.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private MovieMapper moiveMapper;

	@Override
	public List<ReplyVO> getMovieDetails(Long mi_num) {

		log.info("getMovieDetails....");
		
		return mapper.getMovieDetails(mi_num);
	}
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {

		log.info("register......" + vo);

		return mapper.insert(vo);

	}

	@Override
	public ReplyVO get(Long c_num) {

		log.info("get......" + c_num);

		return mapper.read(c_num);

	}

	@Override
	public int modify(ReplyVO vo) {

		log.info("modify......" + vo);

		return mapper.update(vo);

	}

	@Transactional
	@Override
	public int remove(Long c_num) {

		log.info("remove...." + c_num);

		ReplyVO vo = mapper.read(c_num);

		return mapper.delete(c_num);

	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long mi_num) {

		log.info("get Reply List of a Board " + mi_num);

		return mapper.getListWithPaging(cri, mi_num);

	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long mi_num) {

		return new ReplyPageDTO(mapper.getCountByBno(mi_num), mapper.getListWithPaging(cri, mi_num));
	}

	@Override
	public int getCountCpoint(ReplyVO vo) {
		log.info("get : " + vo);
		Long mi_num = vo.getMi_num();
		return mapper.getCountByBno(mi_num);
	}
	
	@Override
	public void updateNewTotalPoint(float newTotalPoint, Long mi_num) {
		log.info("newTotalPoint : " + newTotalPoint);
		mapper.updateNewTotalPoint(newTotalPoint, mi_num);
	}

	@Override
	public float getTotalPoint(Long mi_num) {
		log.info("mi_num : " + mi_num);
		
		return mapper.getTotalPoint(mi_num);
	}

}
