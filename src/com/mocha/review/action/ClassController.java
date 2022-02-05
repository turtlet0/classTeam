package com.mocha.review.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ClassController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ClassFrontController_doProcess() 호출+");
		// *.me
		
		// -------------------------1. 가상 주소 계산-------------------------
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		String ctxPath= request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		String command=requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);
		System.out.println(" C : 1. 가상주소계산완료");
		// -------------------------1. 가상 주소 계산-------------------------
		
		// -----------------------2. 가상 주소 매핑(처리) --------------------
		Action action = null;
		ActionForward forward = null;

		//contents화면
		if (command.equals("/contents.me")) {	//class화면
			System.out.println("C : /contents.me 호출");
			
			forward = new ActionForward();
			forward.setPath("./board/contents.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/review.me")) {	// 리뷰더보기 눌렀을 때
			System.out.println("C : /review.me 호출");
			// 리뷰리스트 전달받아서 처리(DB)
			// ReviewAction객체
			action=new ReviewAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/reviewWrite.me")) {	// 리뷰작성
			System.out.println("C : /reviewWrite.me 호출");
			forward = new ActionForward();
			forward.setPath("./review/reviewWrite.jsp");
			forward.setRedirect(false);		
		}else if(command.equals("/reviewWriteAction.me")) {	//리뷰작성action
			System.out.println("C : /reviewWriteAction.me 호출");
			action=new ReviewWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		// -----------------------2. 가상 주소 매핑(처리) --------------------
		
		// --------------------------3. 페이지 이동---------------------------
		// 페이지 이동정보가 있을 때
		if (forward != null) {
			if (forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
				System.out.println(" C : 페이지 주소 -  " + forward.getPath());
				System.out.println(" C : 페이지 이동 (sendRedirect) ");
			} else { // false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				System.out.println(" C : 페이지 주소 -  " + forward.getPath());
				System.out.println(" C : 페이지 이동 (forward) ");

			}

		}
		System.out.println(" C : 3. 페이지 이동 끝\n\n\n");
		// --------------------------3. 페이지 이동---------------------------
	}//doProcess
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController_doGet() 호출+");
		// 페이지가 GET방식으로 호출될 때 실행되는 메서드
		doProcess(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController_doPost() 호출+");
		//페이지가 POST방식으로 호출될 때 실행되는 메서드
		doProcess(request,response);
	}
}//Controller
