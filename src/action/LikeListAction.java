package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LikesDAO;

public class LikeListAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		
		ActionForward forward = new ActionForward();
		
		LikesDAO ldao = new LikesDAO();
		List likeList = ldao.getLikeList(cd);
		request.setAttribute("likeList", likeList);
		
		System.out.println("찜 리스트 보냄");
		
		forward.setPath("./member/likepage.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
