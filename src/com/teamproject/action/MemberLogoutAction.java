package com.teamproject.action;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberLogoutAction_execute() 호출");
		
		// 세션정보 초기화 
		HttpSession session = request.getSession();
		session.invalidate();
		
		// 페이지 이동(메인페이지)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print(" alert('로그아웃 성공!');");
		out.print(" location.href='./Main.me'; ");
		out.print("</script>");
		out.close();
		return null;
	}
	
}
