package com.kb.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	private int num;
	private String uname;
	private String schoolname;
	private String gradeclass;
	private String uid;
	private String upw;
	private String route;
	private String boardingplace;
	private Date joindate;
	private int coupon;
	
	private List<AuthorVO> authList;
}
