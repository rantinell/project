package com.team5.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team5.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private MemberService service;
	
//	@Setter(onMethod_ = @Autowired)
//	private PasswordEncoder pwEncoder;
	
	@Test
	public void testRegister() {
		MemberVO memberVO = new MemberVO();
		memberVO.setM_id("testid51");
		// memberVO.setM_pw(pwEncoder.encode("pw51"));
		memberVO.setM_pw("pw51");
		memberVO.setM_name("이순신");
		memberVO.setM_tel("010-1234-5678");
		memberVO.setM_mail("test51@aaa.com");
		
		service.signUp(memberVO);
		log.info("생성된 계정의 회원번호: " + memberVO.getM_num());
	}
}
