package com.kb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kb.domain.BoardVO;
import com.kb.domain.BoardCriteria;
import com.kb.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	

	@Override
	public void register(BoardVO board) {
		log.info("register");
		mapper.insert(board);
	}

	@Override
	public BoardVO get(int bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		// TODO Auto-generated method stub
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(int bno) {
		// TODO Auto-generated method stub
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList() {
		log.info("getList...................");
		
		return mapper.getList();
	}

	@Override
	public List<BoardVO> getListWithPaging(BoardCriteria cri) {
		log.info("getList...................");
		
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getListWithCnt(BoardCriteria cri) {
		log.info("getListWithCnt...................");
		
		return mapper.getListWithCnt(cri);
	}
	
	

}
