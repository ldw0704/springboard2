package com.kb.service;

import java.util.List;

import com.kb.domain.MemberVO;
import com.kb.domain.AuthorVO;
import com.kb.domain.MemberCriteria;

public interface MemberService {

	public void register(MemberVO member);
	
	public MemberVO get(int num);
	
	public boolean modify(MemberVO member);
	
	public boolean remove(int num);
	
	public List<MemberVO> getList();
	
	public List<MemberVO> getListWithPaging(MemberCriteria cri);

	public int getListWithCnt(MemberCriteria cri);

	public List<AuthorVO> readAuthsByUid(String uid);

	public void insertAuthsByUid(AuthorVO vo);
}
