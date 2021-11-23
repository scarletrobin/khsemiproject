package trade.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trade.model.service.TradeService;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet(name = "Transaction", urlPatterns = { "/transaction" })
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buyer = request.getParameter("postFrom");
		int tradeNo = Integer.parseInt(request.getParameter("tradeNo"));
		String seller = request.getParameter("trade-seller");
		int transactionPrice = Integer.parseInt(request.getParameter("transaction-price"));
		int tradeStatus = Integer.parseInt(request.getParameter("trade-status"));
		// 비즈니스 로직
		TradeService ts = new TradeService();
		int result = ts.transaction(buyer, tradeNo, seller, transactionPrice);
		int result2 = ts.transactionStatus(tradeNo, tradeStatus);
		// 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result2 > 0) {
			request.setAttribute("msg", "판매상태 변경 완료");
		} else {
			request.setAttribute("msg", "판매상태 변경 실패");
		}
		request.setAttribute("loc", "/mypage?reqPage=1&memberId="+seller);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
