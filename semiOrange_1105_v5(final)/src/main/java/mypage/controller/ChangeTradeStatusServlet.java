package mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import mypage.model.service.MyTradeService;
import mypage.model.vo.Review;

/**
 * Servlet implementation class ChangeTradeStatusServlet
 */
@WebServlet(name = "ChangeTradeStatus", urlPatterns = { "/changeTradeStatus" })
public class ChangeTradeStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeTradeStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tradeNo = Integer.parseInt(request.getParameter("tradeNo"));
		int tradeStatus = Integer.parseInt(request.getParameter("status"));
		//3. 비즈니스 로직
		int result = new MyTradeService().changeTradeStatus(tradeNo, tradeStatus);
		//4. 결과처리
		if(result>0) { //회원등급 변경 성공
			ArrayList<Review> list = new ArrayList<Review>();
			Review r = new Review();
			r.setTransactionNo(tradeNo);
			list.add(r);
			request.setAttribute("msg", "변경 완료");
			request.setAttribute("reviewDone", list);
			response.sendRedirect("/myPosts?reqPage=1");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "에러났으니까 빨리 찾으세요");
			request.setAttribute("loc", "/adminPage");
			rd.forward(request, response); //페이지 이동
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
