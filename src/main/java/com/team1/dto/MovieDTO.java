package com.team1.dto;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;

public class MovieDTO {
	
	
	// ��ȭ �帣
	private Long g_num;
	private String genre;

	// ��ȭ ��������
	private Long md_num;
//	private long mi_num;
//	private long g_num;
	private String md_director;
	private String md_actor;
	private String md_text;
	private String md_runtime;
	private String md_grade;

	// ��ȭ ���� ���̺�
	private Long mi_num;
//	private long g_num;
	private String mi_title;
	private float mi_total_point;
	private String mi_thumbnail;
	private Date mi_regDate;
	private Date mi_updatedate;
	private MultipartFile Poste;

	

}