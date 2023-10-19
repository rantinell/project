package com.team1.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberTests {
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwencoder;
	
	@Setter(onMethod_ = @Autowired)
	private DataSource ds;
	
	@Test
	public void testInsertMember() {
		
		String sql = "insert into test_tbl_member(m_num, m_id, m_nick, m_pw, m_name, m_mail, m_tel, g_num) values(?,?,?,?,?,?,?,?)";
		
		for(int i = 0; i < 100; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(4, pwencoder.encode("m_pw" + i));
				
				if(i < 80) {
					pstmt.setLong(1, i);
					pstmt.setString(2, "user" + i);
					pstmt.setString(5, "name" + i);
					pstmt.setString(6, "mail" + i);
					pstmt.setString(7, "tel" + i);
					pstmt.setInt(8, i%4+1);
				}else if(i < 90) {
					pstmt.setLong(1, i);
					pstmt.setString(2, "manager" + i);
					pstmt.setString(5, "운영자" + i);
					pstmt.setString(6, "mail" + i);
					pstmt.setString(7, "tel" + i);
					pstmt.setInt(8, i%4+1);
				}else {
					pstmt.setLong(1, i);
					pstmt.setString(2, "admin" + i);
					pstmt.setString(5, "관리자" + i);
					pstmt.setString(6, "mail" + i);
					pstmt.setString(7, "tel" + i);
					pstmt.setInt(8, i%4+1);
				}
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch (Exception e) {
						// TODO: handle exception
					}
				}
				if(con != null) {
					try {
						con.close();
					}catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
	}
	
	@Test
	public void testInsertAuth() {
		
		String sql = "insert into test_member_auth(m_num, auth) values(?,?)";
		
		for(int i = 0; i < 100; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
			
				if(i < 80) {
					pstmt.setLong(1, i);
					pstmt.setString(2, "ROLE_USER");
				}else if(i < 90) {
					pstmt.setLong(1, i);
					pstmt.setString(2, "ROLE_MANAGER");
				}else {
					pstmt.setLong(1, i);
					pstmt.setString(2, "ROLE_ADMIN");
				}
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch (Exception e) {
						// TODO: handle exception
					}
				}
				if(con != null) {
					try {
						con.close();
					}catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
	}
}
