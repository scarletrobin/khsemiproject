package trade.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trade.model.service.TradeService;
import trade.model.vo.Trade;

/**
 * Servlet implementation class TradeUpdateFrmServlet
 */
@WebServlet(name = "TradeUpdateFrm", urlPatterns = { "/tradeUpdateFrm" })
public class TradeUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeUpdateFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tradeNo = Integer.parseInt(request.getParameter("tradeNo"));
		// 비즈니스 로직
		Trade t = new TradeService().getTrade(tradeNo);
		// 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/trade/tradeUpdateFrm.jsp");
		request.setAttribute("t", t);
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
