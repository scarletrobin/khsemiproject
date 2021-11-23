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
 * Servlet implementation class ChkDeleteReportServlet
 */
@WebServlet(name = "ChkDeleteReport", urlPatterns = { "/chkDeleteReport" })
public class ChkDeleteReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChkDeleteReportServlet() {
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
			String num = request.getParameter("num");
		//3.비즈니스로직
			boolean result = new ReportService().chkDeleteReport(num);
		//4.결과처리
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			if(result) {
				request.setAttribute("msg", "삭제완료");
			}else {
				request.setAttribute("msg", "삭제실패");
			}
			request.setAttribute("loc", "/reportListMore?reqPage=1");
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
