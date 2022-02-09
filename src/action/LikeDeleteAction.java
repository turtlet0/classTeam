package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LikesDAO;

public class LikeDeleteAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		
		
		LikesDAO ldao = new LikesDAO();
		ldao.deleteLike(cd);
		
		
		
		return null;
	}

}
