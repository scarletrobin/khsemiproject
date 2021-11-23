package freeBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeBoard.model.service.FreeService;
import freeBoard.model.vo.FreeBoard;
import freeBoard.model.vo.FreePageData;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet(name = "FreeBoard", urlPatterns = { "/freeBoard" })
public class FreeBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardServlet() {
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
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		
		//3.비즈니스로직
		FreePageData fpd = new FreeService().selectFreeList(reqPage);
		
		//4.화면출력										
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/freeBoard/freeBoard.jsp");
		request.setAttribute("list", fpd.getList());
		request.setAttribute("pageNavi", fpd.getPageNavi());
		request.setAttribute("start", fpd.getStart());
		request.setAttribute("totalCount", fpd.getTotalCount());
		request.setAttribute("fixPage", fpd.getFixPage());
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
