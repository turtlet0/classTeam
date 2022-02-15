package com.latte.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latte.action.ReviewAction;
import com.latte.action.ReviewDeleteProAction;
import com.latte.action.ReviewReplyAction;
import com.latte.action.ReviewUpdateAction;
import com.latte.action.ReviewUpdateProAction;
import com.latte.action.ReviewWriteAction;


public class Controller extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ReviewController_doProcess() 호출+");
		// /*
		
		// -------------------------1. 가상 주소 계산-------------------------
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		String ctxPath= request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		String command=requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);
		String recommand=command.substring(0, command.lastIndexOf("/"));		
		
		System.out.println(" C : 1. 가상주소계산완료\n");
		// -------------------------1. 가상 주소 계산-------------------------
		
		// -----------------------2. 가상 주소 매핑(처리) --------------------
		Action action = null;
		ActionForward forward = null;

		//main
		if (command.equals("/main")) {	//main페이지
			System.out.println(" C : /main 호출");
			
			forward = new ActionForward();
			forward.setPath("./board/main.jsp");
			forward.setRedirect(false);
		}
		/////////////////////////////////////////////////////////////
		else if(command.equals("/class.cl")) {	// 리뷰상세페이지
			System.out.println("C : /class.cl 호출");
			forward = new ActionForward();
			forward.setPath("./class/detail.jsp");
			forward.setRedirect(false);	
		}
		/////////////////////////////////////////////////////////////
		
		else if(recommand.equals("/review")) {	// 리뷰보기
			System.out.println("C : /review 호출");
			// 리뷰리스트 전달받아서 처리(DB)
			// ReviewAction객체
			action=new ReviewAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/reviewWrite")) {	// 리뷰작성
			System.out.println("C : reviewWrite 호출");

			forward = new ActionForward();
			forward.setPath("./review/reviewWrite.jsp");
			forward.setRedirect(false);	
		}
		else if(command.equals("/reviewWriteAction")) {	//리뷰작성action
			System.out.println("C : /reviewWriteAction 호출");
			action=new ReviewWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/reviewUpdate")){	//리뷰수정페이지뿌리기
			// DB 정보가져오기, view 페이지에 출력(el) 
			action = new ReviewUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/reviewUpdateProAction")){	//수정처리페이지
			// DB처리(정보수정), view페이지로 이동(main.jsp)
			action = new ReviewUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/reviewDelete")) {	// 리뷰삭제
			System.out.println("C : reviewDelete 호출");

			forward = new ActionForward();
			forward.setPath("./review/reviewDelete.jsp");
			forward.setRedirect(false);	
		}
		else if(command.equals("/reviewDeleteProAction")) {	// 리뷰삭제
			System.out.println(" C : /reviewDelete");
			// 리뷰리스트 전달받아서 처리(DB)
			// ReviewAction객체
			action=new ReviewDeleteProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/reviewReply")){//대댓글 창으로 이동
			forward = new ActionForward();
			forward.setPath("./review/reviewReply.jsp");
			forward.setRedirect(false);		
		}
		else if(command.equals("/reviewReplyAction")) {	// 리뷰대댓글
			System.out.println(" C : /reviewReplyAction");
			// 리뷰리스트 전달받아서 처리(DB)
			// ReviewAction객체
			action=new ReviewReplyAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//////QNA//////////////////////////////////////////////////////////////////////////////////////////////////////		
		else if(recommand.equals("/QnA")) {	// QnA보기
			System.out.println("C : /QnA 호출");
			// 리스트 전달받아서 처리(DB)
			// QnaAction객체
			action=new QnaAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/qnaWrite")) {	// q작성
			System.out.println("C : qnaWrite 호출");

			forward = new ActionForward();
			forward.setPath("./QnA/qnaWrite.jsp");
			forward.setRedirect(false);	
		}
		else if(command.equals("/qnaWriteAction")) {	//리뷰작성action
			System.out.println("C : /qnawWriteAction 호출");
			action=new QnaWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/qnaUpdate")){	//QnA수정페이지
			// DB 정보가져오기, view 페이지에 출력(el) 
			action = new QnaUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/qnaUpdateProAction")){	//수정처리페이지
			// DB처리(정보수정), view페이지로 이동(main.jsp)
			action = new QnaUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/qnaDelete")) {	// 리뷰삭제
			System.out.println("C : qnaDelete 호출");

			forward = new ActionForward();
			forward.setPath("./QnA/qnaDelete.jsp");
			forward.setRedirect(false);	
		}
		else if(command.equals("/qnaDeleteProAction")) {	// 리뷰삭제
			System.out.println(" C : /qnaDelete");
			// 리뷰리스트 전달받아서 처리(DB)
			// ReviewAction객체
			action=new QnaDeleteProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/qnaReply")){//대댓글 창으로 이동
			forward = new ActionForward();
			forward.setPath("./QnA/qnaReply.jsp");
			forward.setRedirect(false);		
		}
		else if(command.equals("/qnaReplyAction")) {	// 리뷰대댓글
			System.out.println(" C : /qnaReplyAction");
			// 리뷰리스트 전달받아서 처리(DB)
			// ReviewAction객체
			action=new QnaReplyAction();
			
			try {
				forward=action.execute(request, response);
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
	}
	//doProcess
	
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
