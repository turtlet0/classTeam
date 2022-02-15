package com.latteclass.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//페이지가 Get/Post방식 상관없이 호출될때 실행되는 메서드
public class MemberFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController_doProcess()호출!");

		// --------------------------------1. 가상 주소계산 -------------------------------------
		
		String requestURI = request.getRequestURI(); 
			System.out.println("C : requestURI - "+requestURI); 
		String ctxPath = request.getContextPath();
		String command = requestURI.substring(ctxPath.length());
		
		// --------------------------------2. 가상 주소매핑(처리) ------------------------------
		Action action = null;
		ActionForward forward = null;
		
		//회원가입 페이지 (DB수정 X, DB정보 X, view 페이지로 이동)
		if(command.equals("/MemberJoin.me")){
			System.out.println("C : /MemberJoin.me 호출!");
			
			forward = new ActionForward();
			forward.setPath("./member/memberJoin.jsp"); 
			forward.setRedirect(false);

		//회원가입 처리 (DB수정 O, view 페이지로 이동)
		}else if(command.equals("/MemberJoinAction.me")){
			System.out.println("C : /MemberJoinAction.me 호출!");
			
			action = new MemberJoinAction(); 
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		// 이메일 중복체크 ajex이용 (DB수정 X, DB정보 O, view 페이지로 이동X)
		}else if(command.equals("/MemberMailDoubleCheck.me")){
			System.out.println("C : /MemberMailDoubleCheck.me 호출!");
			
			action = new MemberMailDoubleCheck(); 
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		// 이용약관 페이지(DB수정 X, view 페이지로 이동)
		}else if(command.equals("/TermOfUse.me")){
			
			forward = new ActionForward();
			forward.setPath("./member/memberTermOfUse.jsp");
			forward.setRedirect(false);

		// 개인정보수집·이용 페이지(DB수정 X, view 페이지로 이동)
		}else if(command.equals("/PrivacyPolicy.me")){
			
			forward = new ActionForward();
			forward.setPath("./member/memberPrivacyPolicy.jsp");
			forward.setRedirect(false);

		// 로그인 페이지(DB수정 X, view 페이지로 이동)
		}else if(command.equals("/MemberLogin.me")){
			
			forward = new ActionForward();
			forward.setPath("./member/memberLogin.jsp");
			forward.setRedirect(false);
		
		//로그인 정보를 전달받아서 처리(DB수정 X, DB정보 O, view 페이지로 이동)
		}else if(command.equals("/MemberLoginAction.me")){

			//MemberLoginAction 객체
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		//카카오 로그인 정보를 전달받아서 처리(DB수정 O, DB정보 O, view 페이지로 이동)
		}else if(command.equals("/MemberKakaoLoginAction.me")){
			
			//MemberLoginAction 객체
			action = new MemberKakaoLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		//네이버 콜백페이지 이동(DB수정 X, view 페이지로 이동O)
		}else if(command.equals("/NaverCallBack.me")){
			
			forward = new ActionForward();
			forward.setPath("./member/naverCallBack.jsp");
			forward.setRedirect(false);
			
		//네이버 로그인 정보를 전달받아서 처리(DB수정 O, DB정보 O, view 페이지로 이동)
		}else if(command.equals("/MemberNaverLoginAction.me")){
			
			//MemberLoginAction 객체
			action = new MemberNaverLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		//로그아웃(DB수정 X, view 페이지로 이동O)
		}else if(command.equals("/MemberLogout.me")){

			forward = new ActionForward();
			forward.setPath("./member/memberLogout.jsp");
			forward.setRedirect(false);
		
		// 프로필조회(DB수정 X,  DB정보 O, view 페이지로 이동O)
		}else if(command.equals("/MemberProfile.me")){
			action = new MemberProfileAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		//프로필수정페이지(DB수정  X,  DB정보 O, view 페이지로 이동O)
		}else if(command.equals("/MemberProfileUpdate.me")){
			action = new MemberProfileUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		//프로필수정(DB수정 O, DB정보 O, view 페이지로 이동O)
		}else if(command.equals("/MemberProfileUpdateProAction.me")){
			action = new MemberProfileUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 메인 페이지(DB X, view 페이지로 이동)
		}else if(command.equals("/Main.me")){
			forward = new ActionForward();
			forward.setPath("./main.jsp");
			forward.setRedirect(false);			
		}
		
	
		
		//가상 주소매핑(처리)
		System.out.println("C : 2. 가상 주소매핑(처리) 끝 . (페이지 이동 아직X)");
		
		
		// --------------------------------3. 페이지 이동 ---------------------------------------
		
		//페이지 이동정보가 있을때
		if(forward !=null){
			if(forward.isRedirect()){ //true
				response.sendRedirect(forward.getPath());
				
				System.out.println("C : 페이지 주소 - " + forward.getPath());
				System.out.println("C : 페이지 이동(sendRedirect방식)");

			}else{//false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				
				System.out.println("C : 페이지 주소 - " + forward.getPath());
				System.out.println("C : 페이지 이동(forward방식)");
			}
		}//페이지 이동 
		System.out.println("C : 3. 페이지이동끝 \n");
	}//doProcess
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController_doGet()호출!");
		//페이지가 GET방식으로 호출될때 실행되는 메서드
		doProcess(request, response); // doProcess로 가서 실제로 실행됨
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController_doPost()호출!");
		//페이지가 POST방식으로 호출될때 실행되는 메서드
		doProcess(request, response); // doProcess로 가서 실제로 실행됨
	}

	
	
}

