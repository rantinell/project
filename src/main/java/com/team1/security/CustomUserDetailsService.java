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
import com.team1.mapper.MovieMapper;
import com.team1.dto.MemberDAO;
import com.team1.dto.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	MemberDAO mdao = new MemberDAO();
	
	@Override
	public UserDetails loadUserByUsername(String m_id) throws UsernameNotFoundException{
		
		log.warn("Load User By UserName: " + m_id);
		System.out.println("Load User By UserName: " + m_id);
		MemberVO vo = memberMapper.read(m_id);
					
		System.out.println("UserDetail : " + vo);
		log.warn("UserDetails : " + vo);
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(vo.getM_lev().equals("1")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
		}else if(vo.getM_lev().equals("2")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
		}else{
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		
		log.warn(authorities);
		vo.setAuthList(authorities);
		log.warn(vo);
		log.warn(vo.getAuthList());
		
		
		return vo == null ? null : new CustomUser(vo);
	}
}