package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러_doprocess 호출");
		
		
		// -----------------------1. 가상 주소 계산 -------------------------
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);
		System.out.println(" C : 1. 가상 주소 계산 끝! ");
		// -----------------------1. 가상 주소 계산 -------------------------
		
		// -----------------------2. 가상 주소 매핑(처리) --------------------
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Contents.me")){
			System.out.println("!!!!!!!!!content 호출");
			action = new ClassContentAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Payment.me")){
			String class_cd = request.getParameter("class_cd");
			System.out.println("######결제 호출"+class_cd);
			
			action = new PaymentAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/LikeUpdate.me")){
			System.out.println("좋아요추가_action 호출");
			
			action = new LikeUpDateAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/LikeCount.me")){
			System.out.println("좋아요 개수_action 호출");
			
			action = new LikeCountAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Likes.me")){
			System.out.println("좋아요 페이지_action 호출");
			
			action = new LikeListAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/PaySuccess.me")){
			System.out.println("결제 성공_action 호출");
			
			action = new PaySuccessAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/PayCalculate.me")){
			System.out.println("정산_action 호출");
			
			action = new PaymentListAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/LikeDelete.me")){
			action = new LikeDeleteAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Setting.me")){
			action = new SettingAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/ScheduleSet.me")){
			action = new ScheduleSetAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else if(command.equals("/SetOneday.me")){
			action = new SetOnedayAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/SetMultiple.me")){
			action = new SetMultipleAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				System.out.println("완성 중");
				e.printStackTrace();
			}
			
		}else if(command.equals("/deleteSchedule.me")){
			action = new deleteScheduleAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}else if(command.equals("/SelectSchedule.me")){
			System.out.println("호출@@@@@@@@@@@@@@@@@@@@@@");
			action = new SelectscheduleAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// -----------------------2. 가상 주소 매핑(처리) --------------------
		
		
		// -----------------------3. 페이지 이동 ---------------------------
		if(forward != null){
			if(forward.isRedirect()){ // true
				response.sendRedirect(forward.getPath());
				System.out.println(" C : 페이지 주소 - "+forward.getPath());
				System.out.println(" C : 페이지 이동 (sendRedirect) ");
			}else{ // false
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				System.out.println(" C : 페이지 주소 - "+forward.getPath());
				System.out.println(" C : 페이지 이동 (forward) ");
			}
		}
		System.out.println(" C : 3. 페이지 이동 끝 \n\n\n ");	
		// -----------------------3. 페이지 이동 ---------------------------	
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러_doGet() 호출! ");
		// 페이지가 GET방식으로 호출될때 실행되는 메서드
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러_doPost() 호출! ");
		// 페이지가 POST방식으로 호출될때 실행되는 메서드
		doProcess(request, response);
	}

}
