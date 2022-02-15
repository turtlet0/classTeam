package com.latteclass.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	//  excute 메서드 : 실행시 request, response를 전달 받아서 처리후에 ActionForward를 리턴하는 메서드
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	
	
}
