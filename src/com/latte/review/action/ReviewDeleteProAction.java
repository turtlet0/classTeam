package com.latte.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latte.review.db.ReviewDAO;
import com.latte.review.db.ReviewDTO;

public class ReviewDeleteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// DTO 객체
		ReviewDTO dto = new ReviewDTO();
		int class_cd=Integer.parseInt(request.getParameter("class_cd"));
		int cno = Integer.parseInt(request.getParameter("cno"));
		int member_cd=Integer.parseInt(request.getParameter("member_cd"));
		//dto
		System.out.println(class_cd +" / " +cno+" / " +member_cd);
		
		
		// 전달된 정보 저장
		dto.setCno(cno);
		dto.setMember_cd(member_cd); // 멤버코드

		ReviewDAO rdao=new ReviewDAO();
		int result=rdao.deleteReview(cno,member_cd);
		// 처리결과에 따른 페이지 이동
				// 1-성공 0-실패

				if (result == 0) { // 실패
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();

					out.print("<script>");
					out.print(" alert('정보없음');");
					out.print(" history.back(); ");
					out.print("</script>");

					out.close();
					return null;
				}

				// result==1
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.print("<script>");
				out.print(" alert('댓글삭제');");
				out.print(" location.href='./review/"+class_cd+"'");
				out.print("</script>");
				out.close();
				return null;
			}
	

}
