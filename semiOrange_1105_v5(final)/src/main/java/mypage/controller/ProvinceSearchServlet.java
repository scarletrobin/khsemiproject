package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class ProvinceSearchServlet
 */
@WebServlet(name = "ProvinceSearch", urlPatterns = { "/provinceSearch" })
public class ProvinceSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProvinceSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. 값 추출
		String province = request.getParameter("province");
		// 3. 비즈니스로직
		ArrayList<Sigungu> list = new SigunguService().searchProvince(province);
		// 4. 결과처리
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(list, response.getWriter()); //list 안의 모두 json 타입으로 바꿔줌
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
