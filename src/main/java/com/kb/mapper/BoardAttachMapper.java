package com.kb.mapper;

import java.util.List;

import com.kb.domain.BoardAttachVO;
import com.kb.domain.BoardVO;

public interface BoardAttachMapper {

	public void insert(BoardAttachVO vo);

	public List<BoardAttachVO> read(int bno);

	public void delete(BoardVO board);
}
