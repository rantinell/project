package com.team5.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;


@Getter
public class CustomUser extends User {
	
	//private static final long serialVaersionUID = 1L;	
	private MemberVO member;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(MemberVO vo) {
		super(vo.getM_id(), vo.getM_pw(), vo.getAuthList().stream().map(auth -> new SimpleGrantedAuthority(auth.getM_lev())).collect(Collectors.toList()));
		this.member = vo;
	}
}
