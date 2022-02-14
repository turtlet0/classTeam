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
		int cnt=dao.getQnaCount(class_cd);
		request.setAttribute("cnt", cnt);
		// 페이징
		int pageSize = 5;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		request.setAttribute("cntQnaList", dao.getCntQnaList(startRow, pageSize, class_cd));
		// 페이징끝
		System.out.println("실행됨");

		// 밑에 페이징 시작
		int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);
		int pageBlock = 5;
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		// 밑에 페이징 끝
		
		// // review.jsp
		forward.setPath("../QnA/qnaList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
