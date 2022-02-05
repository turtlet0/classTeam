package com.mocha.review.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mocha.review.db.ReviewDAO;
import com.mocha.review.db.ReviewDTO;

public class ReviewWriteAction implements Action {
	//리뷰작성

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : ReviewWriteAction_execute() 호출");
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		// DTO 객체
		ReviewDTO dto = new ReviewDTO();
		// 전달된 정보 저장
		dto.setRating(request.getParameter("rating"));	//별점
		dto.setContent(request.getParameter("content"));	//내용
		dto.setRegdate(new Timestamp(System.currentTimeMillis()));
		
		System.out.println(" M : "+dto);
		
		ReviewDAO dao = new ReviewDAO();
		dao.insertReview(dto);
		System.out.println(" M : 회원정보 저장완료 ");
		
		// 페이지 이동 -> 이동정보 저장
		// ActionForward 객체를 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./review.me");
		forward.setRedirect(true);
		return forward;
	}
	
	
}
