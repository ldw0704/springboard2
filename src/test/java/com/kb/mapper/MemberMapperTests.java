package com.kb.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kb.domain.MemberVO;
import com.kb.domain.MemberCriteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {

	@Setter(onMethod_ = @Autowired)
	MemberMapper mapper;
	
	
	
	public void getListTest() {
		log.info(mapper.getList());
	}
	
	@Test
	public void testread() {
		MemberVO vo = mapper.readLogin("admin9");
		log.info(vo);
	}
	
	
	public void getListWithPaging() {
		MemberCriteria cri = new MemberCriteria();
		cri.setPageNum(1);
		cri.setAmount(20);
		cri.setType("uname");
		//cri.setType("content");
		cri.setKeyword("");
		List<MemberVO> list = mapper.getListWithPaging(cri);
		list.forEach(member -> log.info(member));
	}
	
	public void insert() {
		MemberVO member = new MemberVO();
		member.setNum(2);
		member.setUname("2성우");
		member.setSchoolname("2경북직훈");
		member.setGradeclass("3학년");
		member.setUid("kkk");
		member.setUpw("333");
		member.setRoute("3호");
		member.setBoardingplace("3학년");
		member.getJoindate();
		member.setCoupon(7);
		mapper.insert(member);
	}
	
	public void read() {
		log.info(mapper.read(1));
	}
	
	
	public void update() {
		MemberVO member = new MemberVO();
		member.setNum(1);
		member.setUname("수정성우");
		member.setSchoolname("수정 경북직훈");
		member.setGradeclass("2학년");
		member.setUid("k");
		member.setUpw("222");
		member.setRoute("2호");
		member.setBoardingplace("2학년");
		member.getJoindate();
		member.setCoupon(9);
		
		mapper.update(member);
	}
	
	
	public void delete() {
		mapper.delete(458731);
	}
}







