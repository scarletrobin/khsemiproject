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
import report.model.vo.AdminMember;

/**
 * Servlet implementation class MemberDetailServlet
 */
@WebServlet(name = "MemberDetail", urlPatterns = { "/memberDetail" })
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDetailServlet() {
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
		int memberNo=Integer.parseInt(request.getParameter("memberNo"));
		String memberId = request.getParameter("memberId");
		//3.
		AdminMember m = new ReportService().selectOneMember(memberNo);
		ArrayList<AllWrite> freeList =new ReportService().selectFreeNotice(memberId);
		ArrayList<AllWrite> tradeList =new ReportService().selectTradeNotice(memberId);
		ArrayList<AllWrite> commentList =new ReportService().selectCommentList(memberId);
		ArrayList<AllWrite> reviewList = new ReportService().selectReviewList(memberId);
		//4.
		if(m !=null) {
			request.setAttribute("freelist", freeList);
			request.setAttribute("tradelist", tradeList);
			request.setAttribute("commentlist", commentList);
			request.setAttribute("reviewlist", reviewList);
			request.setAttribute("member", m);
			request.getRequestDispatcher("/WEB-INF/views/admin/memberDetail.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/admin/adminAllMember.jsp").forward(request, response);
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
