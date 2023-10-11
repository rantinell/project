package com.team1.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	
	private Long c_num;
	private Long mi_num;
	private Long m_num;
	private String c_comment;
	private int c_point;
	private Date regDate;
	private Date updateDate;

	// 회원 아이디
	private String m_id;
}
