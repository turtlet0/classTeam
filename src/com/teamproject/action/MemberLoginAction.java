package com.teamproject.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamproject.db.MemberDAO;
import com.teamproject.db.MemberDTO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		
		System.out.println(" M :MemberLoginAction_execute() 호출");
		
		// 전달된 파라메터 저장
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// DAO 객체를 생성 - 로그인 체크 메서드
		MemberDAO dao = new MemberDAO();
		int result = dao.loginCheck(id, pw);
		MemberDTO dto = dao.getMember(id);
		String cd = dto.getMember_cd();
		
		System.out.println("@@@@@@@@@세션값" + cd);
		
		
		// 결과에 따른 페이지 이동 -> 자바스크립트
		if(result == 0){ // 비밀번호 오류
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print(" alert('비밀번호 오류!');");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			return null;
			// 이미 js 코드를 사용해서 페이지 이동완료
			// 컨트롤러에서는 페이지이동 X
		}
		else if(result == -1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print(" alert('아이디 없음!');");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			return null;
		}
		
		// 로그인 성공!
		// 세션값 생성(로그인 ID)	
		HttpSession session= request.getSession();
		session.setAttribute("cd", cd);
		// 페이지 이동-ActionForward
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);		
		return forward;
	} //execute

	
	

}
