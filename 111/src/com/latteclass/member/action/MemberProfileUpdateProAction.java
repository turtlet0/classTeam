package com.latteclass.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.latteclass.member.db.MemberDAO;
import com.latteclass.member.db.MemberDTO;

public class MemberProfileUpdateProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberProfileUpdateProAction_execute()");
		//한글처리
		request.setCharacterEncoding("UTF-8");		

		// 세션체크
		HttpSession session = request.getSession();
		String member_cd = (String)session.getAttribute("member_cd");
		
		ActionForward forward = new ActionForward();
		if(member_cd == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 이전페이지에서 전달된 정보 저장(DTO)

		//DTO 객체
		MemberDTO dto = new MemberDTO();
		//전달된 정보를 저장
		dto.setMember_cd(Integer.parseInt(member_cd));
		dto.setNick_name(request.getParameter("nick_name")); 
		dto.setPhone_num(Integer.parseInt(request.getParameter("phone_num")));

		
		// DAO 객체 생성 - 정보 수정 메서드 호출
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMemberProfile(dto);
		
		
		//처리결과에 따른 페이지 이동(1, -1) -> JS 이용
		if (result == -1 ){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('회원정보 없음');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('회원정보 수정완료');");
			out.print("location.href='./MemberProfile.me';");
			out.print("</script>");
			out.close();
			return null;
		}
	}

}
