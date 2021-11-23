package mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.service.MyQnaService;
import post.model.service.PostService;
import qna.model.service.QnaService;
import qna.model.vo.QnaPageData;

/**
 * Servlet implementation class MyQnaServlet
 */
@WebServlet(name = "MyQna", urlPatterns = { "/myQna" })
public class MyQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyQnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String memberId = request.getParameter("memberId");
		int postCnt = new PostService().selectNotReadPost(memberId);
		QnaPageData qpd = new MyQnaService().selectQnaList(reqPage, memberId);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mypage4.jsp");
		request.setAttribute("postCnt", postCnt);
		request.setAttribute("list", qpd.getList());		
		request.setAttribute("pageNavi", qpd.getPageNavi());
		request.setAttribute("start", qpd.getStart());
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
