package com.team2.movie.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonException  {
	@ExceptionHandler(RuntimeException.class)
	private ModelAndView handleErrorCommon(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception" ,e);
		mav.setViewName("errorCommon");
        return mav; 
	}

}
