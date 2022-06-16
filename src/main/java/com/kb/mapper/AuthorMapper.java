package com.kb.mapper;

import java.util.List;

import com.kb.domain.AuthorVO;

public interface AuthorMapper {
	public void insert(AuthorVO vo);

	public List<AuthorVO> readAuthsByUid(String uid);

}
