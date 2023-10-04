package com.team1.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;
//import lombok.extern.log4j.Log4j2;

@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.warn("Login Success");

		List<String> roleNames = new ArrayList<>();

		auth.getAuthorities().forEach(authority ->{
			roleNames.add(authority.getAuthority());
		});

		log.warn("ROLE NAMES: "+roleNames);
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/movie/admin");
			return;
		}
		if(roleNames.contains("ROLE_MANAGER")) {
			response.sendRedirect("/movie/manager");
			return;
		}
		if(roleNames.contains("ROLE_MEMBER")) {
//			response.sendRedirect("/movie/member/home");
			response.sendRedirect("/movie");
			return;
		}
		response.sendRedirect("/");
	}

}