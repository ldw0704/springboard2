package com.kb.mapper;

import java.util.List;

import com.kb.domain.MemberVO;
import com.kb.domain.MemberCriteria;

public interface MemberMapper {
	
	public List<MemberVO> getList();
	
	public List<MemberVO> getListWithPaging(MemberCriteria cri);
	
	public void insert(MemberVO member);
	
	public MemberVO read(int num);
	
	public int update(MemberVO member);
	
	public int delete(int num);

	public int getListWithCnt(MemberCriteria cri);

}
