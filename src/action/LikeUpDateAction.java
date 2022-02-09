package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import dao.LikesDAO;

public class LikeUpDateAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		String class_cd = request.getParameter("class_cd");

		System.out.println("좋아요::::::::::::세션" +cd);
		System.out.println("좋아요::::클래스:::::코드" +class_cd);
		
		
		LikesDAO ldao = new LikesDAO();
		
		// 좋아요 중복 체크
		int result = ldao.likeCheck(cd, class_cd);
		if(result == 1){
			ldao.likeUpdate(cd, class_cd);
		}else{
			ldao.likeDelete(cd, class_cd);
		}
				
		
		
		
		
		
		return null;
	}

	
}
