package mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mypage.model.service.MyQnaService;
import qna.model.vo.Qna;
import qna.model.vo.QnaPageData;

/**
 * Servlet implementation class MyQnaAnsweredServlet
 */
@WebServlet(name = "MyQnaAnswered", urlPatterns = { "/myQnaAnswered" })
public class MyQnaAnsweredServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyQnaAnsweredServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		QnaPageData qpd = new MyQnaService().selectAnsweredQnaList(reqPage, memberId);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(qpd, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
