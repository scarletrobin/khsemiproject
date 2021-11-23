package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import freeBoard.model.vo.FreePageData;
import mypage.model.service.MyCommentService;
import mypage.model.vo.CommentPageData;
import qna.model.service.QnaService;
import qna.model.vo.QnaPageData;

/**
 * Servlet implementation class MyCommunityCommentServlet
 */
@WebServlet(name = "MyCommunityComment", urlPatterns = { "/myCommunityComment" })
public class MyCommunityCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCommunityCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		CommentPageData cpd = new MyCommentService().selectCommentList(reqPage, memberId);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(cpd, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
