package com.teamproject.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaySuccessAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		String class_cd = request.getParameter("class_cd");
		String price = request.getParameter("price");
		
		System.out.println(cd);
		System.out.println("@"+class_cd);
		System.out.println("@@"+price);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./pay/calculate.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
