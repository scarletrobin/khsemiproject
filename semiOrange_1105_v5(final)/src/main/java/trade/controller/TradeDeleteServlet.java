package trade.controller;

import java.io.File;
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
 * Servlet implementation class TradeDeleteServlet
 */
@WebServlet(name = "TradeDelete", urlPatterns = { "/tradeDelete" })
public class TradeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tradeNo = Integer.parseInt(request.getParameter("tradeNo"));
		// 비즈니스 로직
		TradeService service = new TradeService();
		Trade t = service.getTrade(tradeNo);
		int result = service.deleteTrade(tradeNo);
		// 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result > 0) {
			if (t.getFilepath() != null) {
				String root = getServletContext().getRealPath("/");
				String file = root + "upload/trade" + t.getFilepath();
				File delFile = new File(file);
				delFile.delete();
			}
			request.setAttribute("msg", "삭제 성공");
			request.setAttribute("loc", "/tradeLoginList?reqPage=1&memberId=" + t.getTradeWriter());
		} else {
			request.setAttribute("msg", "삭제 실패");
			request.setAttribute("loc", "/tradeView?noticeNo=" + tradeNo);
		}
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
