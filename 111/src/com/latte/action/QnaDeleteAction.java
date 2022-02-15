package com.latte.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latte.db.QnaDTO;
import com.latte.db.ReviewDTO;


public class QnaDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// DTO 객체
		QnaDTO dto = new QnaDTO();
		
		int class_cd=Integer.parseInt(request.getParameter("class_cd"));
		int member_cd=Integer.parseInt(request.getParameter("member_cd"));
		
		
		// 전달된 정보 저장
		dto.setClass_cd(class_cd);
		dto.setMember_cd(member_cd);	//멤버코드
		
		return null;
	}

}
