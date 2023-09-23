package com.team5.domain;

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
}
