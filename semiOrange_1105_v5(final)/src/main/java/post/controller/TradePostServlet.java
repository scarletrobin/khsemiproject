package post.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TradePostServlet
 */
@WebServlet(name = "TradePost", urlPatterns = { "/tradePost" })
public class TradePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String postFrom = request.getParameter("postFrom");
		int tradeNo = Integer.parseInt(request.getParameter("tradeNo"));
		
		//비지니스 로직
		
		//결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/post/postTradeWriteFrm.jsp");
		
		//중복체크된 아이디를 결과 페이지에서 사용 하기위해 등록을 한다
		request.setAttribute("postFrom", postFrom);
		request.setAttribute("tradeNo", tradeNo);
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
