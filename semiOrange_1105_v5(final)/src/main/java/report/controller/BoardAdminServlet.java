package report.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report.model.service.ReportService;
import report.model.vo.AllWrite;

/**
 * Servlet implementation class BoardAdminServlet
 */
@WebServlet(name = "BoardAdmin", urlPatterns = { "/boardAdmin" })
public class BoardAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		//2.값추출
		//3.비즈니스
		ArrayList<AllWrite> tradelist = new ReportService().selectBoardTop();
		ArrayList<AllWrite> freelist = new ReportService().selectFreeTop();
		ArrayList<AllWrite> qnalist = new ReportService().selectQna();
		//4.결과처리
		request.setAttribute("tradelist", tradelist);
		request.setAttribute("freelist", freelist);
		request.setAttribute("qnalist", qnalist);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminBoard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
