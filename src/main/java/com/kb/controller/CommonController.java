package com.kb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
//@RequestMapping("/test/*")
public class CommonController {

	@GetMapping("/test/all")
	public void doAll() {
		log.info("누구나 접속가능함");
	}
	
	@GetMapping("/test/member")
	public void doMember() {
		log.info("로그인한 회원만 접속가능함");
	}
	
	@GetMapping("/test/admin")
	public void doAdmin() {
		log.info("관리자만 접속가능함");
	}
	
	@GetMapping("/error_page/err_403")
	public void error403(Authentication auth, Model model) {
		log.info("access denied: " + auth);
		model.addAttribute("msg", auth);
	}
	
	@GetMapping("/customLogin")
	public void loginInput() {
		
	}
	
}
