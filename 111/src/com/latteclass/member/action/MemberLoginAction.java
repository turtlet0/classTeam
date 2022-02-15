package com.latteclass.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.latteclass.member.db.MemberDAO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberLoginAction_execute() 호출");
		
		// 전달된 파라메터 저장
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// DAO 객체를 생성 - 로그인 체크 메서드
		MemberDAO dao = new MemberDAO();
		int result = dao.loginCheck(email, password);
		
		// 결과에 따른 페이지 이동 -> 자바스크립트
		if(result == 0){ // 비밀번호 오류
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호 오류');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null; // 이미 js 코드를 사용해서 페이지 이동완료.
						// 컨트롤러에서는 페이지이동X
		}else if(result == -1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('회원정보가 없습니다');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null; // 이미 js 코드를 사용해서 페이지 이동완료.
						// 컨트롤러에서는 페이지이동X			
		}else{
			//로그인 성공
			// 세션값 생성(로그인 메일)
			String member_cd = String.valueOf(result); 
			HttpSession session = request.getSession();
			session.setAttribute("member_cd", member_cd);
		}

		//페이지 이동 - ActionForward
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);

		return forward;
	}
	

}
