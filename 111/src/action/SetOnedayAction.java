package action;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDAO;
import dto.ScheduleDTO;

public class SetOnedayAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		String class_cd = request.getParameter("class_cd");
		
		// string 20221102
//		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-mm-dd");
//		Date setDate = transFormat.parse(request.getParameter("setDate"));
//		System.out.println(setDate);
//		System.out.println(request.getParameter("setDate"));
		// 검색할 때 string to date or date to string java 

		ScheduleDTO sdto = new ScheduleDTO();
		sdto.setSchedule_startDate(request.getParameter("setDate"));
		sdto.setSchedule_endDate(request.getParameter("setDate"));
		sdto.setSchedule_startTime(request.getParameter("startTime"));
		sdto.setSchedule_endTime(request.getParameter("endTime"));
		sdto.setSchedule_class_cd(class_cd);
		
		ScheduleDAO sdao = new ScheduleDAO();
		sdao.insertShedule(sdto);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./ScheduleSet.me");
		forward.setRedirect(false);
		
		return forward;
	}

}
