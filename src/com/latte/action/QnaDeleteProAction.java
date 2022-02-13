package com.latte.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latte.db.QnaDAO;
import com.latte.db.QnaDTO;


public class QnaDeleteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// DTO 객체
		QnaDTO dto = new QnaDTO();
		int class_cd=Integer.parseInt(request.getParameter("class_cd"));
		int qno = Integer.parseInt(request.getParameter("qno"));
		int member_cd=Integer.parseInt(request.getParameter("member_cd"));
		int q_ref=Integer.parseInt(request.getParameter("q_ref"));
		int q_lev=Integer.parseInt(request.getParameter("q_lev"));
		System.out.println("lev값 >> "+q_lev);
		
		//dto
		System.out.println(class_cd +" / " +qno+" / " +member_cd);
		
		
		// 전달된 정보 저장
		dto.setQno(qno);
		dto.setMember_cd(member_cd); // 멤버코드
		dto.setQ_lev(q_lev);
		dto.setQ_ref(q_ref);
		dto.setQ_seq(Integer.parseInt(request.getParameter("q_seq")));
		QnaDAO rdao=new QnaDAO();
		
		if(q_lev==0) {	//q_lev=0 부모댓글
			int result=rdao.reDeleteQna(qno, member_cd, q_ref);
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
			out.print(" location.href='./QnA/" + class_cd + "'");
			out.print("</script>");
			out.close();
			return null;
		}else {	//자식댓글
			int result=rdao.deleteQna(qno, member_cd);
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
			out.print(" location.href='./QnA/" + class_cd + "'");
			out.print("</script>");
			out.close();
			return null;
		}
		
		
		
		
	}

}
