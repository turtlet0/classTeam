package com.mocha.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mocha.review.db.ReviewDAO;

public class ReviewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : ReviewAction_execute() ");
		
		//세션제어
		HttpSession session = request.getSession();
//		String id = (String ) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
//		if(id == null || !id.equals("admin")){
//			forward.setPath("./MemberLogin.me");
//			forward.setRedirect(true);
//			return forward;
//		}
		
	
		// DAO 객체 생성
		ReviewDAO dao = new ReviewDAO();
		// getReviewList() - 전체리뷰내용가져오기
		// dao.getReviewList();
		// request 영역에 저장
		request.setAttribute("reviewList", dao.getReviewList());
		
		// ./review.jsp
		forward.setPath("./review/review.jsp");
		forward.setRedirect(false);
		return forward;
		
	}
	
}
