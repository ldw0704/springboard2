package com.kb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kb.domain.BoardVO;
import com.kb.domain.BoardCriteria;
import com.kb.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceImplTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성한 글");
		board.setContent("새로 작성한 내용");
		board.setWriter("newbie");
		service.register(board);
	}
	
	public void getList() {
		service.getList();
	}

	@Test
	public void getListWithPaging() {
		BoardCriteria cri = new BoardCriteria();
		cri.setType("title");
		//cri.setType("content");
		cri.setKeyword("지상");
		service.getListWithPaging(cri);
	}
	
	
	public void get() {
		service.get(458732);
	}
	
	public void modify() {
		BoardVO board = new BoardVO();
		board.setBno(458732);
		board.setTitle("수정 작성한 글");
		board.setContent("수정 작성한 내용");
		board.setWriter("newbie");
		service.modify(board);
	}
	
	
	public void remove() {
		service.remove(458732);
	}

}





