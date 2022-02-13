package com.latte.review.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latte.review.db.ReviewDAO;
import com.latte.review.db.ReviewDTO;


public class ReviewReplyAction implements Action {
	// 대댓글 작성
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		// DTO 객체
		ReviewDTO dto = new ReviewDTO();
		
		//클래스코드
		int class_cd=Integer.parseInt(request.getParameter("class_cd"));
		int member_cd=Integer.parseInt(request.getParameter("member_cd"));
		System.out.println(class_cd +":class_cd");
		System.out.println(member_cd +":member_cd");

		// 전달된 정보 저장
		dto.setClass_cd(class_cd);
		dto.setMember_cd(member_cd);	//멤버코드
		dto.setContent(request.getParameter("content")); // 내용
		dto.setReg_date(new Timestamp(System.currentTimeMillis())); // 날짜
		dto.setC_ref(Integer.parseInt(request.getParameter("c_ref")));
		dto.setC_lev(Integer.parseInt(request.getParameter("c_lev")));
		dto.setC_seq(Integer.parseInt(request.getParameter("c_seq")));

		System.out.println("전달된 dto " + dto);
		ReviewDAO dao = new ReviewDAO();
		dao.reInsertReview(dto);
		System.out.println("정보저장완료");

		// 페이지 이동 -> 이동정보 저장
		// ActionForward 객체를 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./review/"+class_cd); // classcd따로 받기
		forward.setRedirect(true);
		return forward;
		
	}
}


