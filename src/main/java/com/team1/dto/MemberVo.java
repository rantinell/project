package com.team1.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberVo {
	
	//회원 테스트
	private Long m_num;
	private String m_id;
	private String m_nick;
	private String m_pw;
	private String m_name;
	private String m_tel;
	private String m_mail;
	private int g_num;
	private String m_lev;
}
