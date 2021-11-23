package qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.QnaService;
import qna.model.vo.QnaComment;

/**
 * Servlet implementation class InsertCommentServlet
 */
@WebServlet(name = "InsertCommentQna", urlPatterns = { "/insertCommentQna" })
public class InsertCommentQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCommentQnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnaComment qc = new QnaComment();
		qc.setCommentContent(request.getParameter("commentContent"));
		qc.setBoardType(3);
		qc.setMemberId(request.getParameter("memberId"));
		qc.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		int result = new QnaService().insertComment(qc);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글등록");
		}else {
			request.setAttribute("msg", "등록실패");
		}
		request.setAttribute("loc", "/qnaView?qnaNo="+qc.getBoardNo());
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
