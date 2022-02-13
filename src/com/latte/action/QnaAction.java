package com.latte.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.latte.db.QnaDAO;


public class QnaAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaAction_execute() ");

		// 세션제어
		// HttpSession session = request.getSession();
		// String id = (String ) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		// if(id == null || !id.equals("admin")){
		// forward.setPath("./MemberLogin.me");
		// forward.setRedirect(true);
		// return forward;
		// }

		// 주소에서 클래스 코드 뽑아오기
		String requestURI = request.getRequestURI();
		String last = requestURI.substring(requestURI.lastIndexOf("/"));
		int class_cd = Integer.parseInt(last.substring(1));
		System.out.println("class_cd "+class_cd);
		
		// DAO 객체 생성
		QnaDAO dao = new QnaDAO();

		System.out.println("실행됨 " + class_cd);
		// 게시판 리스트뿌리기
		request.setAttribute("class_cd", class_cd);
		request.setAttribute("qnaList", dao.getQnaList(class_cd));
		System.out.println("실행됨");

		// // review.jsp
		forward.setPath("../QnA/qnaList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
