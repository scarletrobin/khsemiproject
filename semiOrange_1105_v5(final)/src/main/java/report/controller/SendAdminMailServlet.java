package report.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendMailServlet
 */
@WebServlet(name = "SendAdminMail", urlPatterns = { "/sendAdminMail" })
public class SendAdminMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendAdminMailServlet() {
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
				String email = request.getParameter("email");
				System.out.println(email);
				String emailTitle = request.getParameter("emailTitle");
				String emailContent = request.getParameter("emailContent");
				//3.
				boolean result = new AdminMailSender().mailSend(email, emailTitle, emailContent);
				//4.
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				if(result) {
					request.setAttribute("msg", "전송성공");
				}else {
					request.setAttribute("msg", "전송실패");
				}
				request.setAttribute("loc", "/adminPage?reqPage=1");
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
