package mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.service.MyTradeService;
import post.model.service.PostService;
import qna.model.service.QnaService;
import qna.model.vo.QnaPageData;
import trade.model.vo.Trade;
import trade.model.vo.TradePageData;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet(name = "MyPage", urlPatterns = { "/mypage" })
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int postCnt = new PostService().selectNotReadPost(memberId);
		TradePageData tpd = new MyTradeService().selectTradeList(reqPage, memberId);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp");
		int totalPrice = 0;
		for(Trade t : tpd.getList()) {
			totalPrice +=t.getPrice();
		}
		request.setAttribute("postCnt", postCnt);
		request.setAttribute("list", tpd.getList());		
		request.setAttribute("pageNavi", tpd.getPageNavi());
		request.setAttribute("start", tpd.getStart());
		request.setAttribute("totalPrice", totalPrice);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
