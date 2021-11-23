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
import freeBoard.model.vo.LikeIt;
import freeBoard.model.vo.freeViewData;

/**
 * Servlet implementation class FreeViewServlet
 */
@WebServlet(name = "FreeView", urlPatterns = { "/freeView" })
public class FreeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. 값추출
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		
		//3. 비즈니스로직
		freeViewData fvd = new FreeService().selectOneFree(freeNo);

		//4. 결과처리
		if(fvd != null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/freeBoard/freeView.jsp");
			request.setAttribute("f", fvd.getF());
			request.setAttribute("list", fvd.getList());
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "조회 오류");
			request.setAttribute("loc", "/freeBoard?reqPage=1");
			view.forward(request, response);
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
