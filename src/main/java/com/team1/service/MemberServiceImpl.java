package com.team1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.team1.controller.MemberController;
import com.team1.dto.MemberDAO;
import com.team1.dto.MemberVO;
import com.team1.mapper.MemberMapper;
import com.team1.security.CustomUserDetailsService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService {
	@Setter(onMethod_ = @Autowired)
	MemberDAO MemberDAO;

	@Setter(onMethod_ = @Autowired)
	MemberMapper mapper;

	@Override
	public int createMember(MemberVO memberVO) {

		log.info(memberVO.getM_id());

		log.info("create member:" + memberVO);
		boolean isMember = MemberDAO.isMember(memberVO.getM_id());
//		int isMember = mapper.isMember(memberVO.getM_id());

		if (!isMember) {
			int result = MemberDAO.insertMember(memberVO);
//			int result = mapper.insertMember(memberVO);
			if (result > 0) {
				MemberDAO.setAccount(memberVO.getM_id());
				return 1; // 생성 성공
			} else
				return -1; // 생성 실패
		} else {
			return 0; // 이미 존재하는 계정
		}
	}

	@Override
	// public MemberVO login(MemberVO memberVO) {
	public MemberVO login(String m_id) {
		// MemberVO loginedMemberVO = MemberDAO.selectMember(memberVO);
		MemberVO loginedMemberVO = mapper.selectMember(m_id);
		log.warn("service login : " + loginedMemberVO);

		System.out.println("service login : " + loginedMemberVO);

		if (loginedMemberVO != null) {
			setAccount(loginedMemberVO.getM_id());
			log.info("로그인 성공");
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
		return loginedMemberVO;
	}
	
	public void setAccount(String m_id) {
		System.out.println("setAccount");
		CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();
		customUserDetailsService.loadUserByUsername(m_id);
	}

	@Override
	public MemberVO getMemberByNum(Long m_num) {
		return MemberDAO.selectMemberByNum(m_num);
	}

	@Override
	public int setAccount(String m_id, String m_lev) {
		return MemberDAO.updateAccount(m_id, m_lev);
	}

	@Override
	public int modifyMember(MemberVO memberVO) {
		return MemberDAO.updateMember(memberVO);
	}

	@Override
	public int updateAccount(String m_id, String m_lev) {
		return MemberDAO.updateAccount(m_id, m_lev);
	}

	@Override
	public int updatePassword(String m_id, String newpw) {
		return MemberDAO.updatePassword(m_id, newpw);
	}

	// 테스트
	@Override
	public void signUp(MemberVO memberVO) {
		// TODO Auto-generated method stub
		log.info("register........................" + memberVO);
		mapper.signUp(memberVO);

	}

	@Override
	public int idChk(String m_id) {
		int result = mapper.idChk(m_id);
		return result;
	}

	@Override
	public MemberVO get(String m_id) {
		log.info("Read Member...");
		log.info("m_id: " + m_id);
		return mapper.read(m_id);
	}
}