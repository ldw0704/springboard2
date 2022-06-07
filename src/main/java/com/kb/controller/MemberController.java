package com.kb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kb.domain.MemberVO;
import com.kb.domain.MemberCriteria;
import com.kb.domain.MemberPageDTO;
import com.kb.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/*")
@AllArgsConstructor
public class MemberController {
	
	private MemberService service;

//	@GetMapping("list")
//	public void list(Model model) {
//		log.info("목록");		
//		model.addAttribute("list", service.getList());
//	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(MemberCriteria cri, Model model) {
		log.info(cri);
		model.addAttribute("list", service.getListWithPaging(cri));
		model.addAttribute("pageMaker", new MemberPageDTO(service.getListWithCnt(cri), cri));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register() {

	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(MemberVO member, RedirectAttributes rttr) {

		service.register(member);
		
		return "redirect:/member/list";
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	//public void get(int num) {
	public void get(@RequestParam("num") int num, Model model) {
		
		model.addAttribute("member", service.get(num));

	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	//public void get(int num) {
	public String get(MemberVO member) {
		
		boolean result = service.modify(member);
		if(result) {
			return "redirect:/member/list";
		} else {
			return "redirect:/member/get?num="+member.getNum();
		}

	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	//public void get(int num) {
	public String remove(@RequestParam("num") int num) {
		
		service.remove(num);
		
		return "redirect:/member/list";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(MemberVO member) {
		
		service.remove(member.getNum());
		
		return "redirect:/member/list";
	}
	
}





