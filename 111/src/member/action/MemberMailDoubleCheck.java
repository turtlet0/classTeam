package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latteclass.member.db.MemberDAO;

public class MemberMailDoubleCheck implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberJoinAction_execute() 호출");
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//전달된 정보 저장
		String email = request.getParameter("email");
		
		//DAO 객체
		MemberDAO dao = new MemberDAO();
		// 전달된 정보 DB저장
		String result = dao.memberMailDoubleCheck(email);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(result);
		out.flush();

		return null;
	}
}
