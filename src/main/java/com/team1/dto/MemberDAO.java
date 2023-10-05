package com.team1.dto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.team1.security.CustomUserDetailsService;

@Component
public class MemberDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public boolean isMember(String m_id) {
		System.out.println("isMember");
		String sql = "select count(*) from member where m_id = ?";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class, m_id);
		System.out.println(result);
		return result > 0 ? true : false;
	}
	
	public int insertMember(MemberVO MemberVO) {
		System.out.println("insertMember");
		String sql = "insert into member(m_num, m_id, m_pw, m_name, m_tell"
				+ "m_mail, m_lev) values (seq_mem, ?, ?, ?, ?, ?, ?)";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, MemberVO.getM_id(), MemberVO.getM_pw(), MemberVO.getM_name(), MemberVO.getM_tell(), MemberVO.getM_mail());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public MemberVO selectMember(MemberVO memberVO) {
		System.out.println("selectMember");
		String sql = "select * from member where m_id = ?";
		
		List<MemberVO> MemberVOs = new ArrayList<MemberVO>();
		
		try {
			MemberVOs = jdbcTemplate.query(sql,  new RowMapper<MemberVO>() {
				
				@Override
				public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					MemberVO memberVO = new MemberVO();
					
					memberVO.setM_id(rs.getString("m_id"));
					memberVO.setM_pw(rs.getString("m_pw"));
					memberVO.setM_name(rs.getString("m_name"));
					memberVO.setM_tell(rs.getString("m_tell"));
					memberVO.setM_mail(rs.getString("m_mail"));
					memberVO.setM_lev(rs.getString("m_lev"));
					
					return memberVO;
				}
			}, memberVO.getM_id());
			
			if(!passwordEncoder.matches(memberVO.getM_pw(), MemberVOs.get(0).getM_pw()))
				MemberVOs.clear();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return MemberVOs.size() > 0 ? MemberVOs.get(0) : null;
	}
	
	public MemberVO selectMemberByNum(Long m_num) {
		System.out.println("selectMember_num");
		String sql = "select * from member where m_num = ?";
		
		List<MemberVO> MemberVOs = new ArrayList<MemberVO>();
		
		try {
			MemberVOs = jdbcTemplate.query(sql,  new RowMapper<MemberVO>() {
				@Override
				public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					MemberVO memberVO = new MemberVO();
					
					memberVO.setM_id(rs.getString("m_id"));
					memberVO.setM_pw(rs.getString("m_pw"));
					memberVO.setM_name(rs.getString("m_name"));
					memberVO.setM_tell(rs.getString("m_tell"));
					memberVO.setM_mail(rs.getString("m_mail"));
					memberVO.setM_lev(rs.getString("m_lev"));
					
					return memberVO;
				}
				
			}, m_num);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return MemberVOs.size() > 0 ? MemberVOs.get(0) : null;
	}

	public int updateMember(MemberVO memberVO) {
		System.out.println("updateMember");
		String sql = "update member set m_name = ?, m_nick = ?, m_tell = ?, m_mail = ? where m_num = ?";
		int result = -1;

		try {
			result = jdbcTemplate.update(sql, memberVO.getM_name(), memberVO.getM_nick(), memberVO.getM_tell(), memberVO.getM_mail(), memberVO.getM_num());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updatePassword(String m_id, String newPw) {
		System.out.println("updatePassword");
		String sql = "update member set m_pw = ? where m_id = ?";
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, passwordEncoder.encode(newPw), m_id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setAccount(String m_id) {
		System.out.println("setAccount");
		CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();
		customUserDetailsService.loadUserByUsername(m_id);
	}

	public int updateAccount(String m_id, String m_lev) {
		System.out.println("updateAccount");
		String sql = "update member set m_lev = ? where m_id = ?";
		int result = -1;

		try {
			result = jdbcTemplate.update(sql, m_lev, m_id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();
		customUserDetailsService.loadUserByUsername(m_id);
		return result;
	}

}