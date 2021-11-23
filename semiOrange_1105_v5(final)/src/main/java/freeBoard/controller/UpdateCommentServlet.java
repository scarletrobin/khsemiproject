package freeBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeBoard.model.service.FreeService;

/**
 * Servlet implementation class UpdateCommentServlet
 */
@WebServlet(name = "UpdateComment", urlPatterns = { "/updateComment" })
public class UpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCommentServlet() {
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
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		System.out.println(commentNo);
		String commentContent = request.getParameter("commentContent");
		
		
		//3.비즈니스로직
		int result = new FreeService().updateComment(commentContent,commentNo);
		
		//4.화면출력
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "수정완료");
		}else {
			request.setAttribute("msg", "수정실패");
		}
		request.setAttribute("loc", "/freeView?freeNo="+freeNo);
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
