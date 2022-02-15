package com.latteclass.member.action;

// 페이지를 이동할때 필요한 정보를 저장하는 객체
// 1) 페이지 이동정보(주소)
// 2) 페이지 이동방식

public class ActionForward {

	private String path;
	private boolean isRedirect;
	// true - sendRedirect 방식
	// false - forward방식
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
	
	
	
}
