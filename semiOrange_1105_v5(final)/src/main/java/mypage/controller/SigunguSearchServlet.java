package mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mypage.model.service.SigunguService;
import mypage.model.vo.Sigungu;

/**
 * Servlet implementation class SigunguSearchServlet
 */
@WebServlet(name = "SigunguSearch", urlPatterns = { "/sigunguSearch" })
public class SigunguSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigunguSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				int tradeLocalNo = Integer.parseInt(request.getParameter("tradeLocalNo"));
				// 3. 비즈니스로직
				Sigungu s = new SigunguService().searchSigungu(tradeLocalNo);
				// 4. 결과처리
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				new Gson().toJson(s, response.getWriter()); //list 안의 모두 json 타입으로 바꿔줌
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
