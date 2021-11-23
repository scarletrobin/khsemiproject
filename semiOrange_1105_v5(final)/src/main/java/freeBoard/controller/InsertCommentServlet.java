package freeBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeBoard.model.service.FreeService;
import freeBoard.model.vo.Comment1;


/**
 * Servlet implementation class InsertCommentServlet
 */
@WebServlet(name = "InsertComment", urlPatterns = { "/insertComment" })
public class InsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		
		//2.값추출
		Comment1 fc = new Comment1();
		fc.setMemberId(request.getParameter("memberId"));
		fc.setCommentContent(request.getParameter("commentContent"));
		fc.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		
		//3.비즈니스로직
		int result = new FreeService().insertComment(fc);
		
		//4.화면출력
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글 등록 성공!!!");
		}else {
			request.setAttribute("msg", "댓글 등록 실패");
		}
		request.setAttribute("loc", "/freeView?freeNo="+fc.getBoardNo());
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
