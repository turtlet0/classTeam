package file.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FileFrontController_doProcess() 호출");
		
		// ------------------- 1. 가상 주소 계산 -------------------
		String requestURI = request.getRequestURI();
		System.out.println("FFC. requestURI: "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println("FFC. ctxPath: "+ctxPath);
		
		String command = requestURI.substring(ctxPath.length());
		System.out.println("FFC. command: "+command);
		
		
		// ------------------- 2. 가상 주소 매핑(처리) -------------
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/FileUploadAction.fi")) {
			System.out.println("FFC. /FileUploadAction.fi 주소 호출!");
			// DB 처리 -> imgUploadPro.jsp 페이지 이동
			action = new FileUploadAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// ------------------- 3. 페이지 이동 ----------------------
		// 페이지 이동 정보가 있을 때 동작 : forward 객체 존재할때
		if(forward !=null) {
			if(forward.isRedirect()) { // true  : sendRedirect 방식
				response.sendRedirect(forward.getPath());
				System.out.println("FFC. 페이지 이동 완료! - sendRedirect");
				System.out.println("FFC. 페이지 주소 : " + forward.getPath());
			} else { // false : forward 방식
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				System.out.println("FFC. 페이지 이동 완료! - forward");
				System.out.println("FFC. 페이지 주소 : " + forward.getPath());
			}
		}
		System.out.println("FFC. 3. 페이지 이동 끝 \n\n\n");
				
	}
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
}
