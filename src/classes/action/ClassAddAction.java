package classes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 동작 구현을 위한 클래스는 Action 인터페이스 상속받아 사용 - 추상메서드 execute 사용
public class ClassAddAction implements Action {

	// 추상 메서드 excute 구현
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CL. ClassAddAction_execute() 호출!");
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// DTO 객체
			// 전달된 정보 저장
		return null;
	}

}
