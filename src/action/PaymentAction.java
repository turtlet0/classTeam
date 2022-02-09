package action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClassDAO;
import dao.MemberDAO;
import dto.ClassDTO;
import dto.MemberDTO;

public class PaymentAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		
		
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		String class_cd = request.getParameter("class_cd");

		System.out.println("~~~~~~세션"+cd);
		System.out.println("~~~~~~~~~~~~~제발"+class_cd);
		ActionForward forward = new ActionForward();
		
		ClassDAO cdao = new ClassDAO();
		ClassDTO cdto = cdao.getClass(class_cd);
		
		request.setAttribute("cdto", cdto);
		System.out.println("1111111클래스 정보 뿌림");
		
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = mdao.getClassMember(cd);
		
		request.setAttribute("mdto", mdto);
		System.out.println("222222멤버 정보 뿌림");
		
		
		forward.setPath("./pay/payment.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
