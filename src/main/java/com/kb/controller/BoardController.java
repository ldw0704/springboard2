package com.kb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kb.domain.BoardVO;
import com.kb.domain.BoardCriteria;
import com.kb.domain.BoardPageDTO;
import com.kb.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;

//	@GetMapping("list")
//	public void list(Model model) {
//		log.info("목록");		
//		model.addAttribute("list", service.getList());
//	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(BoardCriteria cri, Model model) {
		log.info(cri);
		model.addAttribute("list", service.getListWithPaging(cri));
		model.addAttribute("pageMaker", new BoardPageDTO(service.getListWithCnt(cri), cri));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register() {

	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(BoardVO board, RedirectAttributes rttr) {

		service.register(board);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	//public void get(int bno) {
	public void get(@RequestParam("bno") int bno, Model model) {
		
		model.addAttribute("board", service.get(bno));

	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	//public void get(int bno) {
	public String get(BoardVO board) {
		
		boolean result = service.modify(board);
		if(result) {
			return "redirect:/board/list";
		} else {
			return "redirect:/board/get?bno="+board.getBno();
		}

	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	//public void get(int bno) {
	public String remove(@RequestParam("bno") int bno) {
		
		service.remove(bno);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(BoardVO board) {
		
		service.remove(board.getBno());
		
		return "redirect:/board/list";
	}
	
}





