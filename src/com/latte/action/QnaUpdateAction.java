package com.latte.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.latte.db.QnaDAO;
import com.latte.db.QnaDTO;





public class QnaUpdateAction implements Action{
	// 리뷰업데이트
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션체크
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		// DAO 객체 생성 - id에 해당하는 회원정보 가져오기
		QnaDAO dao = new QnaDAO();

		// 추가적으로 dto 객체를 사용하는 경우 // 리뷰번호가져오기
		int num = Integer.parseInt(request.getParameter("qno"));
		QnaDTO dto = dao.getQna(num);
		request.setAttribute("dto", dto);

		// 페이지 이동 (updateForm.jsp)
		
		forward.setPath("./QnA/qnaUpdate.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
