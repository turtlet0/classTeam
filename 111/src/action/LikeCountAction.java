package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import dao.LikesDAO;

public class LikeCountAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		String class_cd = request.getParameter("class_cd");

		System.out.println("좋아요++++++++세션" +cd);
		System.out.println("좋아요+++클래스++++코드" +class_cd);
		
		
		
		
		LikesDAO ldao = new LikesDAO();
		int ccnt = ldao.likeCount(class_cd);
		
		out.println(ccnt);
		out.close();
		
		
		System.out.println("이거걍넘기면안되나"+ccnt);
		
		return null;
	}

}
