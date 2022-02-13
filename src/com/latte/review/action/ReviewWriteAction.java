package com.latte.review.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latte.review.db.ReviewDAO;
import com.latte.review.db.ReviewDTO;


public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// DTO 객체
		ReviewDTO dto = new ReviewDTO();
		int class_cd=Integer.parseInt(request.getParameter("class_cd"));
		
		// 전달된 정보 저장
		dto.setClass_cd(class_cd);
		dto.setMember_cd(Integer.parseInt(request.getParameter("member_cd")));	//멤버코드
		dto.setRating(request.getParameter("rating"));	//별점
		dto.setContent(request.getParameter("content"));	//내용
		dto.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("dto : "+dto);
		
		ReviewDAO dao = new ReviewDAO();
		dao.insertReview(dto);
		System.out.println(" M : 회원정보 저장완료 ");
		
		// 페이지 이동 -> 이동정보 저장
		// ActionForward 객체를 생성
		ActionForward forward = new ActionForward();
		forward.setPath("../itwillbs1/review/"+class_cd); 
		forward.setRedirect(true);
		return forward;
		
	}
	// 리뷰작성
	
	
}
