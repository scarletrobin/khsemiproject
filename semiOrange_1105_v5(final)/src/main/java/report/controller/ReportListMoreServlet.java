package report.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report.model.service.ReportService;
import report.model.vo.Report;
import report.model.vo.ReportPageData;

/**
 * Servlet implementation class ReportListMoreServlet
 */
@WebServlet(name = "ReportListMore", urlPatterns = { "/reportListMore" })
public class ReportListMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportListMoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//1.
				request.setCharacterEncoding("utf-8");
				//2.
				int reqPage = Integer.parseInt(request.getParameter("reqPage"));
				//3.
				ReportPageData npd = new ReportService().selectAllReport(reqPage);
				ArrayList<Report> cntlist = new ReportService().selectCntList(); 
				//4.
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/reportListMore.jsp");
				request.setAttribute("list", npd.getList());
				request.setAttribute("pageNavi", npd.getPageNavi());
				request.setAttribute("start", npd.getStart());
				request.setAttribute("cntlist", cntlist);
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
