package com.team1.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MovieVo {

	// 영화 장르
	private Long g_num;
	private String genre;

	// 영화 세부정보
	private Long md_num;
//	private long mi_num;
//	private long g_num;
	private String md_director;
	private String md_actor;
	private String md_text;
	private String md_runtime;
	private String md_grade;

	// 영화 정보 테이블
	private Long mi_num;
//	private long g_num;
	private String mi_title;
	private int mi_condition;
	private float mi_total_point;
	private String mi_thumbnail;
	private Date mi_regDate;
	private Date mi_updatedate;
	private MultipartFile fileName;
	
}
