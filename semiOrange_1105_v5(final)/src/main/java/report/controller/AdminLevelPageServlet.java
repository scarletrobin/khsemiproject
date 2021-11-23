package report.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report.model.vo.AdminMember;
import report.model.vo.MemberPageData;
import report.model.service.ReportService;
import report.model.vo.Report;

/**
 * Servlet implementation class AdminPageServlet
 */
@WebServlet(name = "AdminLevelPage", urlPatterns = { "/adminLevelPage" })
public class AdminLevelPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLevelPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//1.인코딩
				request.setCharacterEncoding("utf-8");
				//2.결과값추출
				int reqPage = Integer.parseInt(request.getParameter("reqPage"));
				String keyword = request.getParameter("keyword");
				//3.비즈니스로직
				MemberPageData mpd = new ReportService().selectAllMember2(reqPage);
				//4.결과처리
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/adminAllMember.jsp");
				request.setAttribute("list", mpd.getList());
				request.setAttribute("pageNavi", mpd.getPageNavi());
				request.setAttribute("start", mpd.getStart());
				request.setAttribute("keyword", keyword);
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
