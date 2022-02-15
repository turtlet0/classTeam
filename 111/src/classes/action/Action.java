package classes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// 인터페이스 : 상수, 추상 메서드 두가지 종류의 데이터 사용 가능
	// 인터페이스 사용 이유 : 인터페이스는 new 객체 생성 불가. implement 통해 상속해야함
	// -> 추상 메서드 구현을 강제함. 즉, 추상 메서드 구현(overriding)해야 사용 가능한 것
	// ex) 추상 메서드는 빈 컵. 그 컵에 무엇을 담을지는 각 개발자, 동작마다 다름 but 같은 컵을 사용한다는 것은 동일
	// => 동일한 틀 안에서 다양한 형태 (ex) 회원가입, 수정, 삭제..)로 개발하기 위해 사용하는 것
	
	// => Model 동작(DB 처리 동작)이 필요할 때 사용하는 객체
	// -> 상수, 추상 메서드 중 추상 메서드 사용(처리하는 동작의 형태(틀) 강제 부여)
	
	// 매개변수 request, response를 전달받아 처리 후 ActionForward를 리턴하는 추상 메서드
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
}
