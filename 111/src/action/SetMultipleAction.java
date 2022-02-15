package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDAO;
import dto.ScheduleDTO;

public class SetMultipleAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		String class_cd = request.getParameter("class_cd");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date startDate = transFormat.parse(request.getParameter("startDate"));
		Date endDate = transFormat.parse(request.getParameter("endDate"));
		
		Calendar s1 = Calendar.getInstance();
		Calendar s2 = Calendar.getInstance();
		
		s1.setTime(startDate);
		s2.setTime(endDate);
		
		List dateList = new ArrayList();
		
		while(!s1.equals(s2)){
			s1.add(Calendar.DATE, 1);
			dateList.add(transFormat.format(s1.getTime()));
		}
		
		System.out.println("ㅇㅇ지랄1"+transFormat.format(startDate));
		System.out.println("dd지랄2"+dateList);
		
		ScheduleDTO sdto = new ScheduleDTO();
		sdto.setSchedule_startDate(transFormat.format(startDate));
		sdto.setSchedule_endDate(transFormat.format(startDate));
		sdto.setSchedule_class_cd(class_cd);
		sdto.setSchedule_startTime(startTime);
		sdto.setSchedule_endTime(endTime);
		ScheduleDAO sdao = new ScheduleDAO();
		sdao.insertShedule(sdto);
		System.out.println("sdto1번"+transFormat.format(startDate));
		
		
		for(int i=0; i<dateList.size(); i++){
			
			
			sdto.setSchedule_startDate((String)dateList.get(i));
			sdto.setSchedule_endDate((String)dateList.get(i));
			sdto.setSchedule_class_cd(class_cd);
			sdto.setSchedule_startTime(startTime);
			sdto.setSchedule_endTime(endTime);
			sdao.insertShedule(sdto);
			System.out.println("sdto2번"+dateList.get(i));
		}
		
		
		
	
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./ScheduleSet.me");
		forward.setRedirect(false);
		
		return forward;
	}

}
