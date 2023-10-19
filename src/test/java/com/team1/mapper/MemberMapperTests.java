package com.team1.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team1.dto.MemberDAO;
import com.team1.dto.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {

	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	@Test
	public void testlogin() {
		String m_id="admin";
		MemberVO vo = memberMapper.read(m_id);
		
		log.info(vo);
	}
}
