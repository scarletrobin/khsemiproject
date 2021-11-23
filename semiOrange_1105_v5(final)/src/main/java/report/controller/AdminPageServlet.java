package report.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import report.model.vo.AdminCount;
import report.model.vo.AdminMember;
import report.model.vo.MemberPageData;
import report.model.service.ReportService;
import report.model.vo.Report;

/**
 * Servlet implementation class AdminPageServlet
 */
@WebServlet(name = "AdminPage", urlPatterns = { "/adminPage" })
public class AdminPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPageServlet() {
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
				MemberPageData mpd = new ReportService().selectAllMember(reqPage);
				AdminCount ac=new ReportService().adminCount();
				//4.결과처리
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/adminAllMember.jsp");
				HttpSession session = request.getSession();
				session.setAttribute("ac", ac);
				request.setAttribute("list", mpd.getList());
				request.setAttribute("pageNavi", mpd.getPageNavi());
				request.setAttribute("start", mpd.getStart());
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
