package com.team5.member;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class MemberVO {
	private Long m_num;
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_tell;
	private String m_mail;
	private String m_lev;
	private String m_nick;
	private String genre;
	
	private List<GrantedAuthority> authList;
}
