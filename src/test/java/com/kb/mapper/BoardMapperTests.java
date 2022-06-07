package com.kb.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kb.domain.BoardVO;
import com.kb.domain.BoardCriteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	BoardMapper mapper;
	
	public void getListTest() {
		log.info(mapper.getList());
	}
	
	@Test
	public void getListWithPaging() {
		BoardCriteria cri = new BoardCriteria();
		cri.setPageNum(1);
		cri.setAmount(20);
		cri.setType("title");
		//cri.setType("content");
		cri.setKeyword("테스트");
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
	
	public void insert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성한 글");
		board.setContent("새로 작성한 내용");
		board.setWriter("newbie");
		mapper.insert(board);
	}
	
	public void read() {
		log.info(mapper.read(458731));
	}
	
	
	public void update() {
		BoardVO board = new BoardVO();
		board.setBno(458731);
		board.setTitle("수정 작성한 글");
		board.setContent("수정 작성한 내용");
		board.setWriter("newbie");
		mapper.update(board);
	}
	
	
	public void delete() {
		mapper.delete(458731);
	}
}







