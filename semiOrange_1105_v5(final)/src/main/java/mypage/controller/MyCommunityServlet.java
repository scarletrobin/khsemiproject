package mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeBoard.model.vo.FreePageData2;
import mypage.model.service.MyFreeboardService;
import post.model.service.PostService;
import qna.model.service.QnaService;
import qna.model.vo.QnaPageData;

/**
 * Servlet implementation class MyCommunityServlet
 */
@WebServlet(name = "MyCommunity", urlPatterns = { "/myCommunity" })
public class MyCommunityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCommunityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String memberId = request.getParameter("memberId");//추가
		int postCnt = new PostService().selectNotReadPost(memberId);
		FreePageData2 fpd = new MyFreeboardService().selectFreeboardList(reqPage, memberId);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mypage6.jsp");
		request.setAttribute("postCnt", postCnt);//추가
		request.setAttribute("list", fpd.getList());		
		request.setAttribute("pageNavi", fpd.getPageNavi());
		request.setAttribute("start", fpd.getStart());
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
