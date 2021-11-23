package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mypage.model.service.MyTradeService;

/**
 * Servlet implementation class SearchTransactionNoServlet
 */
@WebServlet(name = "SearchTransactionNo", urlPatterns = { "/searchTransactionNo" })
public class SearchTransactionNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTransactionNoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tradeNo = Integer.parseInt(request.getParameter("transactionNo"));
		int transactionNo = new MyTradeService().searchTransactionNo(tradeNo);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(transactionNo, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
