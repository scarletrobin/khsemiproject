package trade.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trade.model.service.TradeService;
import trade.model.vo.TradeMemberData;

/**
 * Servlet implementation class TradeViewServlet
 */
@WebServlet(name = "TradeView", urlPatterns = { "/tradeView" })
public class TradeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tradeNo = Integer.parseInt(request.getParameter("tradeNo"));
		// 비즈니스 로직
		TradeMemberData tmd = new TradeService().selectOneTrade(tradeNo);
		// 결과 처리
		if (tmd != null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/trade/tradeView.jsp");
			request.setAttribute("t", tmd.getT());
			request.setAttribute("tm", tmd.getTm());
			request.setAttribute("pf", tmd.getPf());
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "조회 오류");
			request.setAttribute("loc", "/tradeList?reqPage=1");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
