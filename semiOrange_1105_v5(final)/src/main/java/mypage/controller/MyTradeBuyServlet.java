package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mypage.model.service.MyTradeService;
import mypage.model.vo.TransactionPageData;
import trade.model.vo.TradePageData;

/**
 * Servlet implementation class MyTradeBuyServlet
 */
@WebServlet(name = "MyTradeBuy", urlPatterns = { "/myTradeBuy" })
public class MyTradeBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyTradeBuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		TransactionPageData tpd = new MyTradeService().tradeBuyList(reqPage, memberId);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(tpd, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
