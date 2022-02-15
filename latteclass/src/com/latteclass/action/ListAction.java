package com.latteclass.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.latteclass.db.ClassDAO;

public class ListAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("ClassListAction_execute()");
		
		//세션제어
		HttpSession session = request.getSession();
		
		ActionForward forward = new ActionForward();
		
		//
		String keyWord = request.getParameter("keyWord");
		if(keyWord==null) {
			keyWord = "";
		}
		
		String category = request.getParameter("category");
		if(category == null) {
			category = "all";
		}
		
		String subcategory = request.getParameter("subcategory");
		if(subcategory == null) {
			subcategory = "전체";
		}
		
		String sido = request.getParameter("sido");
		if(sido ==  null) {
			sido = "all";
		}
		
		// DAO 객체 생성
		ClassDAO dao = new ClassDAO();
		int count = dao.getClassCount(keyWord, sido, category, subcategory); //전체 글 개수
		
		// 페이징 처리
		// 한 페이지에 출력될 글 개수
		int pageSize = 12;
		
		// 한 페이지 정보 설정
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		// 첫 행번호를 계산
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		
		request.setAttribute("classlist", dao.getClassList(startRow, pageSize, keyWord, sido, category, subcategory));
		
		// getClassList
		
		// 해당 뷰에서 사용할 속성
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("category", category);
		request.setAttribute("subcategory", subcategory);
		
		request.setAttribute("sido", sido);
		request.setAttribute("keyWord", keyWord);

		
		// ./class/list.jsp
		forward.setPath("./class/list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
