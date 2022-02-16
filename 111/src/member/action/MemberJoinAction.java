package member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latteclass.member.db.MemberDAO;
import com.latteclass.member.db.MemberDTO;

public class MemberJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberJoinAction_execute() 호출");
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//DTO 객체
		MemberDTO dto = new MemberDTO();
		
		//전달된 정보 저장
		dto.setEmail(request.getParameter("email"));
		dto.setPassword(request.getParameter("password"));
		dto.setJoin_date(new Timestamp(System.currentTimeMillis()));

		System.out.println("M : " + dto);
		
		//DAO 객체
		MemberDAO dao = new MemberDAO();
		// 전달된 정보 DB저장
		dao.memberJoin(dto);
		System.out.println("M : 회원정보 저장완료");

		// 페이지 이동 -> 이동정보 저장까지만. 실제이동은 controller에서 함.
		// ActionForward 객체를 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);
		
		return forward; // controller로 감.
	}
}
