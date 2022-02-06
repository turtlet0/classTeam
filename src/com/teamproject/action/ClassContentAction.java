package com.teamproject.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.teamproject.db.ClassDAO;
import com.teamproject.db.ClassDTO;

public class ClassContentAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println(" @@ 클래스 내용 뿌림_Action");
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		String class_cd = request.getParameter("class_cd");
		System.out.println("클래스 코드 넘어왔음?" +class_cd);
		ActionForward forward = new ActionForward();
		
		ClassDAO dao = new ClassDAO();
		ClassDTO dto = dao.getClass(class_cd);
		
		
		request.setAttribute("dto", dto);
		
		System.out.println("정보넘기기전 확인" +dto);
		
		forward.setPath("./class/classcontent.jsp");
		forward.setRedirect(false);
		
		return forward;

	}

}
