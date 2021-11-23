package trade.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trade.model.service.TradeService;

/**
 * Servlet implementation class ReportMemberServlet
 */
@WebServlet(name = "ReportMember", urlPatterns = { "/reportMember" })
public class ReportMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tradeNo = Integer.parseInt(request.getParameter("tradeNo"));
		String reportWriter = request.getParameter("reportWriter");
		String reportedMember = request.getParameter("reportedMember");
		String reportContent = request.getParameter("reportContent");
		int result = new TradeService().insertReport(reportWriter, reportedMember, reportContent);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/msg.jsp");
		if (result > 0) {
			request.setAttribute("msg", "신고 등록 완료");
		} else {
			request.setAttribute("msg", "신고 등록 실패");
		}
		request.setAttribute("loc", "/tradeView?tradeNo=" + tradeNo);
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
