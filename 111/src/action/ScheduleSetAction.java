package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDAO;

public class ScheduleSetAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		String class_cd = request.getParameter("class_cd");
		
		
		ScheduleDAO sdao = new ScheduleDAO();
		List scheduleList = sdao.getSchedule(cd, class_cd);
		request.setAttribute("scheduleList", scheduleList);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./calendar/scheduleset.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

}
