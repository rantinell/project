package com.team1.mapper;

import com.team1.dto.MemberVO;

public interface MemberMapper {
	public MemberVO read(String m_id);

	public boolean isMember(String m_id);

	public int insertMember(MemberVO memberVO);
}