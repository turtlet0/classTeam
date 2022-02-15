package com.latteclass.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.latteclass.member.db.MemberDAO;
import com.latteclass.member.db.MemberDTO;

public class MemberProfileUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberUpdateAction_excute()");
		
		// 세션체크
		HttpSession session = request.getSession();
		String member_cd = (String)session.getAttribute("member_cd");
		
		ActionForward forward = new ActionForward();
		if(member_cd == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(member_cd);	
		
		// 해당정보 request 영역에 저장
		request.setAttribute("dto", dto);
		
		//페이지 이동(memberProfileUpdate.jsp)
		forward.setPath("./member/memberProfileUpdate.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
