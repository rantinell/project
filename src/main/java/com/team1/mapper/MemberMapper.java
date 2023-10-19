package com.team1.mapper;

import com.team1.dto.MemberVO;
import com.team1.dto.TestMemberVO;

public interface MemberMapper {
	
	public MemberVO read(String m_id);
	
	public TestMemberVO testRead(String m_id);

}