package com.team5.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.service.BoardServiceTests;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml" })
@Log4j
public class MemberTests {

	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwEncoder;

	@Setter(onMethod_ = @Autowired)
	private DataSource ds;

	@Test
	public void testInsertMember() {
		String sql = "insert into test_member(m_num, m_id, m_nick, m_pw, m_tel, m_mail, m_lev, m_name) values (test_seq_member.nextval ,?,?,?,?,?,?,?)";
		for (int i = 8; i < 50; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(3, pwEncoder.encode("pw" + i));
				if (i < 10) {
					pstmt.setString(1, "testid0" + i);
					pstmt.setString(2, "testNick0" + i);
					pstmt.setString(4, "010-1234-5678");
					pstmt.setString(5, "test@test.com");
					pstmt.setString(6, "1");
					pstmt.setString(7, "testN0" +i);
				} else if (i < 40) {
					pstmt.setString(1, "testid" + i);
					pstmt.setString(2, "testNick" + i);
					pstmt.setString(4, "010-1234-5678");
					pstmt.setString(5, "test@test.com");
					pstmt.setString(6, "2");
					pstmt.setString(7, "testN" +i);
				} else {
					pstmt.setString(1, "testid" + i);
					pstmt.setString(2, "testNick" + i);
					pstmt.setString(4, "010-1234-5678");
					pstmt.setString(5, "test@test.com");
					pstmt.setString(6, "3");
					pstmt.setString(7, "testN" +i);
				}
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (Exception e) {

					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {

					}
				}
			}
		}
	}
	
//	@Test
//	public void testInsertAuth() {
//		String sql = "insert into tbl_member_auth(userid, auth) values (?,?)";
//		for (int i = 0; i < 100; i++) {
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(sql);
//				
//				if (i < 80) {
//					pstmt.setString(1, "user" + i);
//					pstmt.setString(2, "ROLE_USER");
//				} else if (i < 90) {
//					pstmt.setString(1, "manager" + i);
//					pstmt.setString(2, "ROLE_MEMBER");
//				} else {
//					pstmt.setString(1, "admin" + i);
//					pstmt.setString(2, "ROLE_ADMIN");
//				}
//				pstmt.executeUpdate();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (Exception e) {
//
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//
//					}
//				}
//			}
//		}
//	}
}
