package member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.latteclass.member.db.MemberDAO;
import com.latteclass.member.db.MemberDTO;

public class MemberNaverLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberNaverLoginAction_execute() 호출");
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		
		//전달된 정보 저장
		String sns_id = request.getParameter("sns_id");
		
		//DAO 객체
		MemberDAO dao = new MemberDAO();
		// 전달된 정보 DB저장
		String result = dao.MemberSnsIdCheck(sns_id);
		
		if(result.equals("n")){
			//DTO 객체
			MemberDTO dto = new MemberDTO();
			//전달된 정보 저장
			dto.setEmail(request.getParameter("email"));
			dto.setSns_id(request.getParameter("sns_id"));
			dto.setNick_name(request.getParameter("nick_name"));
			dto.setJoin_date(new Timestamp(System.currentTimeMillis()));
			
			System.out.println("M : " + dto);
			
			// 전달된 정보 DB저장
			result = dao.memberNaverJoin(dto);
			System.out.println("M : 회원정보 저장완료");
		}

		// 세션값 생성(member_cd)
		HttpSession session = request.getSession();
		session.setAttribute("member_cd",result);
	
		return null;
	}
}
