package com.team1.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int mi_num;
	private int g_num;
	private String mi_title;
	private int condition;
	private String thumbnail;
	private Date regDate;
	private Date updateDate;
}
