package classes.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClassFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// 페이지가 GET/POST 방식 상관없이 호출될 때 실행되는 메서드
		System.out.println("ClassFrontController_doProcess() 호출!");
		// -----------------  1. 가상 주소 계산 ----------------------
		String requestURI = request.getRequestURI();
		System.out.println("CFC. requestURI : " + requestURI); // >  // > /mocaclass/*.cl
		String ctxPath = request.getContextPath(); // 프로젝트(컨텍스트) 경로 반환
		System.out.println("CFC. ctxPath : " + ctxPath); // > /mocaclass
		
		// 실제 사용할 가상 주소 
		String command = requestURI.substring(ctxPath.length());
		System.out.println("CFC. command: " + command); // > /*.cl
		
		
		// -----------------  2. 가상 주소 매핑(처리) ----------------------
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/ClassAdd1.cl")) {
			System.out.println("CFC. /ClassAdd1.cl 주소 호출!");
			// 클래스 등록1 페이지
			// DB 사용 XX -> View-JSP 출력 : forward 방식
			  
			forward = new ActionForward();
			forward.setPath("./class/ClassAdd1Form.jsp");
			// 상대 주소 ./의 의미
				// JSP 실제 파일 : WepContent
				// 가상 주소 : /LateClass 프로젝트
			forward.setRedirect(false);
		} else if(command.equals("/ClassAdd2.cl")) {
			System.out.println("CFC. /ClassAdd2.cl 주소 호출!");
			
			forward = new ActionForward();
			forward.setPath("./class/ClassAdd2Form.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/ClassAdd3.cl")) {
			System.out.println("CFC. /ClassAdd3.cl 주소 호출!");
			
			forward = new ActionForward();
			forward.setPath("./class/ClassAdd3Form.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/ClassAddAction.cl")) {
			System.out.println("CFC. /ClassAddAction.cl 주소 호출!");
			// 페이지 이동, 화면 이동 -> Redirect 방식
			
			//  ClassAdd1Action 객체 생성
			action = new ClassAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Main 페이지
		else if(command.equals("/Main.cl")) {
			forward = new ActionForward();
			forward.setPath("./class/Main.jsp");
			forward.setRedirect(false);
		}
		
		
		System.out.println("CFC. 가상 주소 매핑(처리) 끝 (페이지 이동은 아직 X)");
		// -----------------  3. 페이지 이동 ----------------------
		// 페이지 이동 정보가 있을 때 동작 : forward 객체 존재할때
		if(forward !=null) {
			if(forward.isRedirect()) { // true  : sendRedirect 방식
				response.sendRedirect(forward.getPath());
				System.out.println("CFC. 페이지 이동 완료! - sendRedirect");
				System.out.println("CFC. 페이지 주소 : " + forward.getPath());
			} else { // false : forward 방식
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				System.out.println("CFC. 페이지 이동 완료! - forward");
				System.out.println("CFC. 페이지 주소 : " + forward.getPath());
			}
		}
		System.out.println("C. 3. 페이지 이동 끝 \n\n\n");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이지가 GET 방식으로 호출될 때 실행되는 메서드
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이지가 POST 방식으로 호출될 때 실행되는 메서드
		doProcess(request, response);
	}
	
	
}
