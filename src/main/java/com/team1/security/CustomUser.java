package com.team1.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.team1.dto.MemberVO;
import com.team1.dto.TestMemberVO;

import lombok.Getter;

@Getter
public class CustomUser extends User{
	
	private MemberVO member;
	private TestMemberVO testMember;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

//	public CustomUser(MemberVO vo) {
//		super(vo.getM_id(), vo.getM_pw(), vo.getAuthList());
//		this.member = vo;
//	}
	
	public CustomUser(TestMemberVO vo) {
		super(vo.getM_id(), vo.getM_pw(), vo.getAuthList().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		this.testMember = vo;
	}
}