package trade.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trade.model.service.TradeService;
import trade.model.vo.TradePageData;

/**
 * Servlet implementation class TradeListServlet
 */
@WebServlet(name = "TradeList", urlPatterns = { "/tradeList" })
public class TradeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		// 비즈니스 로직
		TradePageData tpd = new TradeService().selectTradeList(reqPage);
		// 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/trade/tradeList.jsp");
		request.setAttribute("list", tpd.getList());
		request.setAttribute("pageNavi", tpd.getPageNavi());
		request.setAttribute("start", tpd.getStart());
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
