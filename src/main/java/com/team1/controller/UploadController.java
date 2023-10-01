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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.team1.dto.MovieVo;
import com.team1.service.MovieService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
@RequestMapping(value = "/movie/*", method = RequestMethod.GET)
public class UploadController {

	@Autowired
	private ServletContext ctx;

	@Autowired
	private MovieService service;

	@GetMapping("/insert")
	public String uploadForm() {
		log.info("upload form");

		String nextPage = "admin";
		return nextPage;
	}

	@PostMapping(value = "/insertMovie")
	public String uploadFormPost(@ModelAttribute MovieVo movieVo) {

		log.info(movieVo.getMi_title());
		log.info(movieVo.getMi_condition());
		log.info(movieVo.getMd_director());
		log.info(movieVo.getMd_text());
		log.info(movieVo.getG_num());
		log.info(movieVo.getMd_runtime());
		log.info(movieVo.getMd_actor());
		log.info(movieVo.getMi_thumbnail());
//		log.info(movieVo.getFileName());

		String uploadFolder = "C:\\springMVC_STS\\project\\project\\src\\main\\webapp\\resources\\images\\poster";

		MultipartFile multipartFile = movieVo.getMi_thumbnail();

		log.info("file name : " + multipartFile.getOriginalFilename());
		log.info("file size : " + multipartFile.getSize());

		File SaveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(SaveFile);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		service.insertMovieInfo(movieVo);
		service.insertMovieDetails(movieVo);

		return "redirect:/movie";
	}

}
