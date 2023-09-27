package com.team5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.domain.MemberVO;
import com.team5.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {
	@Setter(onMethod_ = @Autowired(required = false))
	private MemberMapper memberMapper;

	@Override
	public void signUp(MemberVO memberVO) {
		// TODO Auto-generated method stub
		log.info("register........................" + memberVO);
		memberMapper.signUp(memberVO);
		
	}
	
	@Override
	public int idChk(String m_id) {
		int result = memberMapper.idChk(m_id);
		return result;
	}
	
	@Override
	public MemberVO get(String m_id) {
		log.info("Read Member...");
		log.info("m_id: " + m_id);
		return memberMapper.read(m_id);
	}

}
