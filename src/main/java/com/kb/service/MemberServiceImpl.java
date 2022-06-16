package com.kb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kb.domain.MemberVO;
import com.kb.domain.AuthorVO;
import com.kb.domain.MemberCriteria;
import com.kb.mapper.AuthorMapper;
import com.kb.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private AuthorMapper authMapper;
	

	@Override
	@Transactional
	public void register(MemberVO member) {
		log.info("register");
		mapper.insert(member);
		authMapper.insert(member.getAuthList().get(0));
	}

	@Override
	public MemberVO get(int num) {
		return mapper.read(num);
	}

	@Override
	public boolean modify(MemberVO member) {
		// TODO Auto-generated method stub
		return mapper.update(member) == 1;
	}

	@Override
	public boolean remove(int num) {
		// TODO Auto-generated method stub
		return mapper.delete(num) == 1;
	}

	@Override
	public List<MemberVO> getList() {
		log.info("getList...................");
		
		return mapper.getList();
	}

	@Override
	public List<MemberVO> getListWithPaging(MemberCriteria cri) {
		log.info("getList...................");
		
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getListWithCnt(MemberCriteria cri) {
		log.info("getListWithCnt...................");
		
		return mapper.getListWithCnt(cri);
	}

	@Override
	public List<AuthorVO> readAuthsByUid(String uid) {
		
		List<AuthorVO> list = authMapper.readAuthsByUid(uid);
		
		return list;
	}

	@Override
	public void insertAuthsByUid(AuthorVO vo) {
		authMapper.insert(vo);
		
	}
	
	

}
