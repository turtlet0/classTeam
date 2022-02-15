package com.latteclass.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	// Model(DB처리 동작) 동작이 필요할 때 사용하는 객체
	// 처리하는 동작의 형태(틀)을 강제로 부여
	
	// 상수, 추상메서드
	
	// execute 메서드는 실행시 request, response를 전달받아서
	// 처리후에 ActionForward를 리턴하는 메서드
	
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

}
