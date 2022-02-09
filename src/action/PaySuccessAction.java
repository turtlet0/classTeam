package action;



import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PaymentDAO;
import dto.PaymentDTO;

public class PaySuccessAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		int price = Integer.parseInt(request.getParameter("price"));
		
		
		PaymentDTO pdto = new PaymentDTO();
		
		pdto.setPayment_class_cd(request.getParameter("class_cd"));
		pdto.setPayment_member_cd(cd);
		pdto.setPrice(price);
		
		PaymentDAO pdao = new PaymentDAO();
		pdao.insertPayment(pdto);
		
		System.out.println(" 결제 정보 저장 완 ");
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./pay/calculate.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
