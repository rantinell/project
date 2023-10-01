package com.team1.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="요청한 영화를 찾을 수 없습니다.")
public class CategoryException extends RuntimeException{

}
