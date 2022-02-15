package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDAO;

public class deleteScheduleAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String schedulecd = request.getParameter("schedule_cd");
		int schedule_cd = Integer.parseInt(schedulecd);
		
		System.out.println(schedule_cd+"잘뜨나용");
		
		ScheduleDAO sdao = new ScheduleDAO();
		sdao.deleteSchedule(schedule_cd);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/ScheduleSet.me");
		forward.setRedirect(false);
		
		return forward;
	}

}
