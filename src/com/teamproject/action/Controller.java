package com.teamproject.action;

import java.io.IOException;




import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






public class Controller extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러_doprocess 호출");
		
		
		// -----------------------1. 가상 주소 계산 -------------------------
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);
		System.out.println(" C : 1. 가상 주소 계산 끝! ");
		// -----------------------1. 가상 주소 계산 -------------------------
		
		// -----------------------2. 가상 주소 매핑(처리) --------------------
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Main.me")){
			System.out.println("C : /Main.me 호출");
			
			action = new ClassListAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/MemberLogin.me")){
			// 로그인 페이지 (view페이지로 이동)
			
			forward = new ActionForward();
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false);			
		}else if(command.equals("/MemberLoginAction.me")){
			// 로그인정보를 전달받아서 처리(DB)
			// MemberLoginAction 객체
			action = new MemberLoginAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberLogout.me")){
			// DB정보 X, view 페이지 X
			// MemberLogoutAction 객체 
			action = new MemberLogoutAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Contents.me")){
			System.out.println("!!!!!!!!!content 호출");
			action = new ClassContentAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// -----------------------2. 가상 주소 매핑(처리) --------------------
		
		
		// -----------------------3. 페이지 이동 ---------------------------
		if(forward != null){
			if(forward.isRedirect()){ // true
				response.sendRedirect(forward.getPath());
				System.out.println(" C : 페이지 주소 - "+forward.getPath());
				System.out.println(" C : 페이지 이동 (sendRedirect) ");
			}else{ // false
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				System.out.println(" C : 페이지 주소 - "+forward.getPath());
				System.out.println(" C : 페이지 이동 (forward) ");
			}
		}
		System.out.println(" C : 3. 페이지 이동 끝 \n\n\n ");	
		// -----------------------3. 페이지 이동 ---------------------------	
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러_doGet() 호출! ");
		// 페이지가 GET방식으로 호출될때 실행되는 메서드
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러_doPost() 호출! ");
		// 페이지가 POST방식으로 호출될때 실행되는 메서드
		doProcess(request, response);
	}

}
