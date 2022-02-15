package com.latteclass.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ClassFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
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
		
		
		
		if(command.equals("/classwrite.do")) {
			System.out.println(" C : /classwrite.do 호출!");
			
			// DB(x)->화면 출력
			forward = new ActionForward();
			forward.setPath("./class/write.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/classwriteProAction.do")) {
			System.out.println(" C : /classwriteProAction.do 호출!");
			
			// 전달 정보 저장, DB에 전달, 다음페이지로 이동
			
			
			// WriteProAction 객체 생성
			action = new WriteProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				
		}else if(command.equals("/main.do")){
			System.out.println(" C : /main.do 호출");
			
			// DB정보 없이 view 페이지로 이동
			
			forward = new ActionForward();
			forward.setPath("./class/main.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/classlist.do")) {
			System.out.println(" C : /classlist.do 호출");
			
			// DB정보를 가져와서, 해당 view페이지 출력
			
			// ListAction() 객체 생성
			action = new ListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}else if(command.equals("/classcontent.do")) {
			System.out.println(" C : /classcontent.do 호출");

			// DB정보를 가져와서, 해당 view페이지 출력
			
			// ContentAction() 객체 생성
			action = new ContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/classfiledown.do")) {
			System.out.println("C : /classfiledown.do 호출");
			
			action = new FiledownAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println(" C : 2. 가상 주소 매핑(처리) 끝 (페이지 이동 X)");
		
		
		// ------------ 2. 가상주소 매핑(처리) ------------------

		// ------------------ 3. 페이지 이동 ------------------
		
		// 페이지 이동정보가 있을 때
		if(forward != null) {
			if(forward.isRedirect()) { // true
				
				response.sendRedirect(forward.getPath());
				System.out.println(" C : 페이지 주소 - " + forward.getPath() );
				System.out.println(" C : 페이지 이동 (sendRedirect)");
			
			}else {	//false
				
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				
				System.out.println(" C : 페이지 주소 - " + forward.getPath());
				System.out.println(" C : 페이지 이동(forward)");
			
			}
		}
		
		System.out.println(" C : 3. 페이지 이동 끝 \n\n\n");
		// ------------------ 3. 페이지 이동 ------------------
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ClassFrontController_doGet() 호출!");
		//페이지가 GET방식으로 호출될때 실행되는 메서드
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ClassFrontController_doPost() 호출!");
		//페이지가 POST방식으로 호출될때 실행되는 메서드
		doProcess(request, response);
	}
	
	
}
