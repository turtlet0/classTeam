package com.latte.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.latte.db.ReviewDAO;


public class ReviewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : ReviewAction_execute() ");
		
		// 세션제어
//		HttpSession session = request.getSession();
//		String id = (String ) session.getAttribute("id");	

		ActionForward forward = new ActionForward();
		// if(id == null || !id.equals("admin")){
		// forward.setPath("./MemberLogin.me");
		// forward.setRedirect(true);
		// return forward;
//				}
		
		// 주소에서 클래스 코드 뽑아오기
		String requestURI = request.getRequestURI();
		String last = requestURI.substring(requestURI.lastIndexOf("/"));
		int class_cd = Integer.parseInt(last.substring(1));
		System.out.println("class_cd "+class_cd);
		
		// DAO 객체 생성
		ReviewDAO dao = new ReviewDAO();
		System.out.println("실행됨 "+ class_cd);
		
		// 게시판 리스트뿌리기
		request.setAttribute("class_cd", class_cd);
		request.setAttribute("reviewList", dao.getReviewList(class_cd));
		int cnt=dao.getReviewCount(class_cd);
		request.setAttribute("cnt", cnt);
		//페이징
		int pageSize=5;
		String pageNum= request.getParameter("pageNum");
		if(pageNum == null){
			pageNum="1";
		}
		int currentPage= Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		request.setAttribute("cntReviewList", dao.getCntReviewList(startRow, pageSize,class_cd));
		//페이징끝
		
		//밑에 페이징 시작
		int pageCount=cnt/pageSize + (cnt%pageSize==0? 0: 1);
		int pageBlock = 5;
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage = startPage + pageBlock-1;
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		//밑에 페이징 끝
		
		System.out.println("실행됨 ReviewAction");
//		// review.jsp
		forward.setPath("../review/reviewList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
