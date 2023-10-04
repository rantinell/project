package com.team1.security;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.team1.mapper.MemberMapper;
import com.team1.dto.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		log.warn("Load User By UserName: "+username);
		MemberVO vo = memberMapper.read(username);

		List<GrantedAuthority> authorities = new ArrayList<>();
		if(vo.getM_lev()=="1") {
			authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
		}else if(vo.getM_lev()=="2") {
			authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
		}else if(vo.getM_lev()=="3") {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		vo.setAuthList(authorities);

		return vo == null ? null : new CustomUser(vo);
	}
}