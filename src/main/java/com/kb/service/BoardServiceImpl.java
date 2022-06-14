package com.kb.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kb.domain.BoardVO;
import com.kb.domain.BoardAttachVO;
import com.kb.domain.BoardCriteria;
import com.kb.mapper.BoardAttachMapper;
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
	
	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;
	

	@Override
	@Transactional
	public void register(BoardVO board) {
		log.info("register");
		mapper.insert(board);
		System.out.println("AI: "+board.getBno());
		Iterator<BoardAttachVO> it = board.getAttachList().iterator();
		while(it.hasNext()) {
			BoardAttachVO attachVO = it.next();
			attachVO.setBno(board.getBno());
			attachMapper.insert(attachVO);
		}
		System.out.println("board: "+board);
	}

	@Override
	public BoardVO get(int bno) {
		BoardVO vo = mapper.read(bno);
		List<BoardAttachVO> attachList = attachMapper.read(bno);
		vo.setAttachList(attachList);
		return vo;
	}

	@Override
	@Transactional
	public boolean modify(BoardVO board) {
		// TODO Auto-generated method stub
		mapper.update(board);
		attachMapper.delete(board);
		
		Iterator<BoardAttachVO> it = board.getAttachList().iterator();
		while(it.hasNext()) {
			BoardAttachVO attachVO = it.next();
			attachVO.setBno(board.getBno());
			attachMapper.insert(attachVO);
		}
		
		return true;
	}	
	
	@Override
	@Transactional
	public boolean remove(BoardVO board) {
		// TODO Auto-generated method stub
		attachMapper.delete(board);
		return mapper.delete(board.getBno()) == 1;
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
