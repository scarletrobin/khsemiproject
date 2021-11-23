package report.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report.model.service.ReportService;

/**
 * Servlet implementation class ChkChangeLevelServlet
 */
@WebServlet(name = "ChkChangeLevel", urlPatterns = { "/chkChangeLevel" })
public class ChkChangeLevelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChkChangeLevelServlet() {
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
		int page = Integer.parseInt(request.getParameter("page"));
		String num = request.getParameter("num");
		String level=request.getParameter("level");
		System.out.println("num : "+num);
		System.out.println("level: "+level);
	//3.비즈니스로직
		boolean result = new ReportService().chkChangeLevel(num,level);
	//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result) {
			request.setAttribute("msg", "변경완료");
		}else {
			request.setAttribute("msg", "변경실패");
		}
		if(page==1) {
			request.setAttribute("loc", "/adminPage?reqPage=1");
		}else {
			request.setAttribute("loc", "/reportListMore?reqPage=1");
		}
		
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
