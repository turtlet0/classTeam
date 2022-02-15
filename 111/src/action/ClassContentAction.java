package action;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClassDAO;
import dao.ScheduleDAO;
import dto.ClassDTO;

public class ClassContentAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println(" @@ 클래스 내용 뿌림_Action");
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		String class_cd = request.getParameter("class_cd");
		System.out.println("클래스 코드 넘어왔음?" +class_cd);
		
		
		ClassDAO dao = new ClassDAO();
		ScheduleDAO sdao = new ScheduleDAO();
		
		
		ClassDTO dto = dao.getClass(class_cd);
		List scheduleDateList = sdao.getScheduleDate(cd, class_cd);
		String[] arr = new String [scheduleDateList.size()];
		
		
		request.setAttribute("dto", dto);
		request.setAttribute("scheduleDateList", scheduleDateList);
		
		System.out.println("정보넘기기전 확인" +dto+"이건?"+scheduleDateList);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./class/classcontent.jsp");
		forward.setRedirect(false);
		
		return forward;

	}

	
}
