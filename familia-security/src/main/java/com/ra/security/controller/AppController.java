package com.ra.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/" + request.getContextPath();
	}

	@ExceptionHandler(value = AccessDeniedException.class)
	public ModelAndView accessDenied() {
		return new ModelAndView("401");
	}

	@RequestMapping(value = {"","/","*"}, method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			System.out.println("NOT AUTH");
		} else {
			response.sendRedirect("search.jsp");
			System.out.println("OK AUTH");
		}
		return "index";
	}

}
