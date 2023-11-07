package com.team1.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team1.dto.MemberVO;
import com.team1.dto.MovieVo;
import com.team1.service.MemberService;
import com.team1.service.MovieService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
@RequestMapping(value = "/movie/upload/*", method = {RequestMethod.GET, RequestMethod.POST})
public class UploadController {
	
	@Autowired
	private MovieService service;
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/insert")
	public String uploadForm(MemberVO memberVO, Model model) {
		log.info("upload form");
		
		System.out.println("[MemberController] memberSelect()");

		List<MemberVO> list = memberService.getMember();

		model.addAttribute("member", list);

//		 나중에 주석처리 
		for (MemberVO member: list) {
			log.info(member);
		}
		
		String nextPage = "admin";
		return nextPage;
	}

	@PostMapping(value = "/insertMovie")
	public String uploadFormPost(@ModelAttribute MovieVo movieVo) {

		log.info("title : " +movieVo.getMi_title());
		log.info("condition : " +movieVo.getMi_condition());
		log.info("director : " +movieVo.getMd_director());
		log.info("text : " +movieVo.getMd_text());
		log.info("g_num : " +movieVo.getG_num());
		log.info("runtime : " +movieVo.getMd_runtime());
		log.info("actor : " +movieVo.getMd_actor());
//		log.info(movieVo.getMi_thumbnail());
		log.info("filename : " +movieVo.getFileName());

//		String uploadFolder = "C:\\Users\\sdedu\\git\\project\\src\\main\\webapp\\resources\\images\\poster";
		String uploadFolder = "C:\\poster";
		
		
		MultipartFile multipartFile = movieVo.getFileName();

		log.info("file name : " + multipartFile.getOriginalFilename());
		log.info("file size : " + multipartFile.getSize());

		File SaveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
		try {
			if(SaveFile.exists() == false) {
				SaveFile.mkdirs();
			}
			multipartFile.transferTo(SaveFile);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.info("insertMovieInfo....");
		service.insertMovieInfo(movieVo);
		log.info("insertMovieDetails....");
		service.insertMovieDetails(movieVo);

		return "redirect:/movie";
	}

}
