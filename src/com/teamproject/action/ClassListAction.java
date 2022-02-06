package com.teamproject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teamproject.db.ClassDAO;

public class ClassListAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println(" C : ClassListAction_execute() ");
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		
		ActionForward forward = new ActionForward();
		
		ClassDAO dao = new ClassDAO();
		request.setAttribute("classList", dao.getClassList());
		
		System.out.println("@@@@@@@@@클래스리스트 보냄;");
		
		forward.setPath("./class/contents.jsp");
		forward.setRedirect(false);
		return forward;	
		
	}

}
