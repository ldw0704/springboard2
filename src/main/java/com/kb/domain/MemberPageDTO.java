package com.kb.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberPageDTO {

	private int startPage;//시작페이지
	private int endPage;//끝페이지
	
	private boolean prev, next; //이전 다음
	
	private int total;//전체 레코드수
	private MemberCriteria cri;//현재페이지, 한페이지당 보여줄 갯수
	private final int PAGE_NUM = 5;
	
	
	public MemberPageDTO(int total, MemberCriteria cri) {
		this.total = total;
		this.cri = cri;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum()/(PAGE_NUM*1.0)))*PAGE_NUM;
		
		this.startPage = this.endPage - (PAGE_NUM-1);
		
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
	
	
	
}
