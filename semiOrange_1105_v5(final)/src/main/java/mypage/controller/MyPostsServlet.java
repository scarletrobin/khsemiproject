package mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import mypage.model.service.MyTradeService;
import post.model.service.PostService;
import qna.model.service.QnaService;
import qna.model.vo.QnaPageData;
import trade.model.vo.TradePageData;

/**
 * Servlet implementation class MyPostsServlet
 */
@WebServlet(name = "MyPosts", urlPatterns = { "/myPosts" })
public class MyPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPostsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("m");
		TradePageData tpd = new MyTradeService().selectTradePostList(reqPage, m.getMemberId());
		int postCnt = new PostService().selectNotReadPost(m.getMemberId());
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mypage2.jsp");
		request.setAttribute("postCnt", postCnt);
		request.setAttribute("list", tpd.getList());
		request.setAttribute("pageNavi", tpd.getPageNavi());
		request.setAttribute("start", tpd.getStart());
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
