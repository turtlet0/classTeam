package com.test.tclass.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClassFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ClassFrontController_doProcess() 호출!");
		// 페이지가 GET/POST방식 상관없이 호출될 때 실행되는 메서드
		// *.do
		
		// ------------1. 가상주소 계산 ------------------------
		// 가상주소 가져오기
		
		String requestURI = request.getRequestURI();
		
		System.out.println(" C : requestURI - "+ requestURI);
		// C : => controller에서 실행
		
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ ctxPath);
		
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+ command);
		
		System.out.println(" C :  1. 가상 주소 계산 끝!");
		// ------------1. 가상주소 계산 ------------------------
		// ------------2. 가상주소 매핑(처리) ------------------
		
		Action action = null;
		ActionForward forward = null;
		if(command.equals("/writeForm.do")) {
			System.out.println(" C : /WriteForm.do 호출!");
			// DB(x)->화면 출력
			forward = new ActionForward();
			forward.setPath("./class/writeForm.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/writePro.do")) {
			System.out.println(" C : /WritePro.do 호출!");
			// 전달 정보 저장, DB에 전달, 다음페이지로 이동
			// WriteProAction 객체 생성
			action = new WriteProAction();
			
			
		}
		
		// ------------2. 가상주소 매핑(처리) ------------------
	}
}
