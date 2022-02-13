package com.latte.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.latte.review.db.ReviewDAO;
import com.latte.review.db.ReviewDTO;



public class ReviewUpdateAction implements Action{
	// 리뷰업데이트
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션체크
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		// DAO 객체 생성 - id에 해당하는 회원정보 가져오기
		ReviewDAO dao = new ReviewDAO();

		// 추가적으로 dto 객체를 사용하는 경우 // 리뷰번호가져오기
		int num = Integer.parseInt(request.getParameter("cno"));
		ReviewDTO dto = dao.getReview(num);
		request.setAttribute("dto", dto);

		// 페이지 이동 (updateForm.jsp)
		
		forward.setPath("./review/reviewUpdate.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
