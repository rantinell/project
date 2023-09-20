package com.team5.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.team5.domain.CustomUser;
import com.team5.domain.MemberVO;
import com.team5.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String m_id) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.warn("Load User By UserName: " + m_id);
		
		MemberVO vo = memberMapper.read(m_id);
		
		log.warn("queried by member mapper: " + vo);
		
		return vo == null ? null : new CustomUser(vo);
	}
	
}
