package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.dao.UpdateMyInfoDao;
import mypage.model.service.UpdateMyInfoService;

/**
 * Servlet implementation class UpdateMannerServlet
 */
@WebServlet(name = "UpdateManner", urlPatterns = { "/updateManner" })
public class UpdateMannerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMannerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int reviewLike = Integer.parseInt(request.getParameter("reviewLike"));
		String reviewer = request.getParameter("reviewer");
		String reviewee = request.getParameter("reviewee");
		int tradeNo = Integer.parseInt(request.getParameter("tradeNo"));
		int result = 0;
		if(reviewLike==1) { //추천일 경우
			result = new UpdateMyInfoService().upgradeManner(reviewLike, reviewee);
		}else { //비추일 경우
			result = new UpdateMyInfoService().degradeManner(reviewLike, reviewee);
		}
		
		if(result>0) {
			request.setAttribute("msg", "매너점수 업데이트 완료");
			request.setAttribute("reviewDone", tradeNo);
			request.setAttribute("loc", "/mypage?reqPage=1&memberId="+reviewer);
			
		} else {
			request.setAttribute("msg", "매너오류 휴");
			request.setAttribute("loc", "/");
		}
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
