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
 * Servlet implementation class CheckDeleteMemberServlet
 */
@WebServlet(name = "ChkDeleteMember", urlPatterns = { "/chkDeleteMember" })
public class ChkDeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChkDeleteMemberServlet() {
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
		int delete = Integer.parseInt(request.getParameter("delete"));
	//3.비즈니스로직
		boolean result = new ReportService().chkDeleteMember(num);
	//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result && delete==1) {
			request.setAttribute("msg", "탈퇴완료");
			request.setAttribute("loc", "/adminPage?reqPage=1");
		}else if(result &&delete==2){ 
			request.setAttribute("msg", "탈퇴완료");
			request.setAttribute("loc", "/reportListMore?reqPage=1");
		}else{
			request.setAttribute("msg", "탈퇴실패");
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
