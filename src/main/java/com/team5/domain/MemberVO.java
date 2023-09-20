package com.team5.domain;

import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	private String m_num;
	private String m_id;
	private String m_nick;
	private String m_pw;
	private String m_name;
	private String m_tel;
	private String m_mail;
	private int g_num;
	private String m_lev;
	
	private List<AuthVO> authList;
}
