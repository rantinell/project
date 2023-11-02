package com.team1.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.team1.dto.Criteria;
import com.team1.dto.MovieVo;
import com.team1.dto.ReplyPageDTO;
import com.team1.dto.ReplyVO;
import com.team1.service.MovieService;
import com.team1.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@Log4j
@RequestMapping("/movie/reply/*")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {

		log.info("ReplyVO: " + vo);

		int insertCount = service.register(vo);

		log.info("Reply INSERT COUNT: " + insertCount);

		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/{c_num}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ReplyVO> get(@PathVariable("c_num") Long c_num) {

		log.info("get: " + c_num);

		return new ResponseEntity<>(service.get(c_num), HttpStatus.OK);
	}

	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH }, value = "/{c_num}", consumes = "application/json", produces = {
			MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("c_num") Long c_num) {

		vo.setC_num(c_num);

		log.info("c_num: " + c_num);
		log.info("modify: " + vo);

		return service.modify(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping(value = "/{c_num}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("c_num") Long c_num) {

		log.info("remove: " + c_num);

		return service.remove(c_num) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}


//	@GetMapping(value = "/pages/{mi_num}/{page}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("mi_num") Long mi_num) {
//
//		Criteria cri = new Criteria(page, 10);
//		
//		log.info("get Reply List mi_num: " + mi_num);
//
//		log.info("cri:" + cri);
//
//		return new ResponseEntity<>(service.getListPage(cri, mi_num), HttpStatus.OK);
//	}

}

