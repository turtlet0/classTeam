package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClassDAO;

public class SettingAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		
		System.out.println("에러가 어디서 나는건지~~"+cd);
		
		ClassDAO cdao = new ClassDAO();
		List myClassList = cdao.getMyClassList(cd);
		request.setAttribute("myClassList", myClassList);
		
		ActionForward forward = new ActionForward();
		
		
		forward.setPath("./calendar/myclass.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
