package com.latte.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.latte.db.QnaDAO;
import com.latte.db.QnaDTO;



public class QnaUpdateProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션체크
		HttpSession session = request.getSession();

		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 클래스 멤버코드
		int class_cd=Integer.parseInt(request.getParameter("class_cd"));
		int member_cd=Integer.parseInt(request.getParameter("member_cd"));
		
		// 이전페이지에서 전달된 정보 저장(DTO)
		QnaDTO dto = new QnaDTO();
		dto.setContent(request.getParameter("content"));
		dto.setQno(Integer.parseInt(request.getParameter("qno")));
		dto.setMember_cd(member_cd);
		dto.setClass_cd(class_cd);
		
		QnaDAO dao=new QnaDAO();
		int result=dao.updateQna(dto);
		
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
		out.print(" alert('수정완료');");
		out.print(" location.href='./QnA/"+class_cd+"'");
		out.print("</script>");
		out.close();
		return null;
	}

}
