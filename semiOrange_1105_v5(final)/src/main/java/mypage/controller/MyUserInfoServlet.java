package mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import mypage.model.service.SigunguService;
import mypage.model.vo.Sigungu;
import post.model.service.PostService;


/**
 * Servlet implementation class MyUserInfoServlet
 */
@WebServlet(name = "MyUserInfo", urlPatterns = { "/myUserInfo" })
public class MyUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member mm = (Member)session.getAttribute("m");
		String memberId = mm.getMemberId();
		int postCnt = new PostService().selectNotReadPost(memberId);
		Sigungu s1 = new Sigungu();
		Sigungu s2 = new Sigungu();
		Sigungu s3 = new Sigungu();
		s1.setSigunguNo(Integer.parseInt(request.getParameter("local1")));
		s2.setSigunguNo(Integer.parseInt(request.getParameter("local2")));
		s3.setSigunguNo(Integer.parseInt(request.getParameter("local3")));
		ArrayList<Sigungu> list = new ArrayList<Sigungu>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		ArrayList<Sigungu> sList = new SigunguService().selectSigungu(list);
		request.setAttribute("postCnt", postCnt);
		request.setAttribute("sList", sList);
		request.getRequestDispatcher("/WEB-INF/views/mypage/mypage7.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
