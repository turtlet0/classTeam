package classes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.db.ClassDTO;


// 동작 구현을 위한 클래스는 Action 인터페이스 상속받아 사용 - 추상메서드 execute 사용
public class ClassAddAction implements Action {
	/*클래스 등록 1 페이지 처리 class*/
	
	// 추상 메서드 excute 구현
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CL. ClassAddAction_execute() 호출!");
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// DTO 객체
			// 전달된 정보 저장
		HttpSession session = request.getSession(); 
			// tip) getSession() | (true) : HttpSession 객체 기존재 시 해당 객체 가져옴. 없으면 새로 생성  
		// System.out.println("session is new?: "+session.isNew()); // 새로 생성된 객체인지 여부 출력
		
		
		/*클래스 등록 정보 저장할 ClassDTO 객체 생성*/
		// session에 ClassDTO 객체 존재하면 해당 객체 가져오기. 없으면 새로 생성
		ClassDTO cdto = null;
		if(session.getAttribute("cdto") == null) {
			System.out.println("cdto 새로 생성");
			cdto = new ClassDTO();
		} else {
			System.out.println("cdto 기존");
			cdto = (ClassDTO) session.getAttribute("cdto");
		}
		
		// cdto에 정보 저장 : 정보 null 아닌경우에만
		if(request.getParameter("content") == null || request.getParameter("content").equals("") 
				|| request.getParameter("content").equals("null")) {
			System.out.println("content null");
		} else {
			System.out.println("content null x");
			cdto.setContent(request.getParameter("content"));
		}
		if(request.getParameter("address") == null || request.getParameter("address").equals("") 
				|| request.getParameter("address").equals("null")) {

			System.out.println("address null");
		} else {
			System.out.println("address null x");
			cdto.setAddress(request.getParameter("address"));
		}
		if(request.getParameter("className") == null || request.getParameter("className").equals("") 
				|| request.getParameter("className").equals("null")) {
			System.out.println("className null ");
		} else {
			System.out.println("className null x");
			cdto.setClassName(request.getParameter("className"));
		}
		if(request.getParameter("roadAddress") == null || request.getParameter("roadAddress").equals("") 
				|| request.getParameter("roadAddress").equals("null")) {
			System.out.println("roadAddress null ");
		} else {
			System.out.println("roadAddress null x");
			cdto.setRoadAddress(request.getParameter("roadAddress"));
		}
		
		// session에 DTO 객체 저장
		session.setAttribute("cdto", cdto);
		
		
		
		
		
		/*get방식으로 받은 idx의 값에 따라 이동 페이지 결정*/
		int idx = Integer.parseInt(request.getParameter("idx")) ;
		if(idx == 1) {
			// 글쓰기 등록 1페이지로 이동
			System.out.println("idx: " + idx);
			ActionForward forward = new ActionForward();
			forward.setPath("./ClassAdd1.cl");
			forward.setRedirect(true);
			
			return forward;
		}else if(idx == 2) {
			// 글쓰기 등록 2페이지로 이동
			System.out.println("idx: " + idx);
			ActionForward forward = new ActionForward();
			forward.setPath("./ClassAdd2.cl");
			forward.setRedirect(true);
			
			return forward;
		}
		
		// idx == 3
		// 클래스 등록 2 페이지로 이동
		System.out.println("idx: " + idx);
		ActionForward forward = new ActionForward();
		forward.setPath("./ClassAdd3.cl");
		forward.setRedirect(true);
		
		return forward;
		
		
		
		
	}

}
