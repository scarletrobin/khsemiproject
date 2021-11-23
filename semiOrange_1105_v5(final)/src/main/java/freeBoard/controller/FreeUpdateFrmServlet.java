package freeBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeBoard.model.service.FreeService;
import freeBoard.model.vo.FreeBoard;

/**
 * Servlet implementation class FreeUpdateFrmServlet
 */
@WebServlet(name = "FreeUpdateFrm", urlPatterns = { "/freeUpdateFrm" })
public class FreeUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeUpdateFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값 추출
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		//3.비즈니스로직
		FreeBoard f = new FreeService().getFree(freeNo);
		//4.값추출
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/freeBoard/freeUpdateFrm.jsp");
		request.setAttribute("f", f);
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
