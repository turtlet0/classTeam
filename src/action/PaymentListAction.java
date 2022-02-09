package action;

import java.util.List
;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PaymentDAO;


public class PaymentListAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String cd = (String) session.getAttribute("cd");
		
		PaymentDAO pdao = new PaymentDAO();
		List paymentList = pdao.getPaymentList(cd);
		request.setAttribute("paymentList", paymentList);
		
		System.out.println("정산 내역 보냄");
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./pay/calculate.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
