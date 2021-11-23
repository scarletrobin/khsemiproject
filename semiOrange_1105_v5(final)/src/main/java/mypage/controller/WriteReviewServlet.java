package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.service.MyReviewService;
import mypage.model.vo.Review;


/**
 * Servlet implementation class WriteReviewServlet
 */
@WebServlet(name = "WriteReview", urlPatterns = { "/writeReview" })
public class WriteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Review r = new Review();
		r.setReviewWriter(request.getParameter("buyer"));
		r.setTransactionNo(Integer.parseInt(request.getParameter("transactionNo")));
		r.setReviewContent(request.getParameter("reviewComment"));
		r.setReviewee(request.getParameter("seller"));
		r.setReviewLike(Integer.parseInt(request.getParameter("reviewLike")));
		int result = new MyReviewService().insertReview(r);
		if(result>0) {
			request.setAttribute("msg", "리뷰 등록 성공!");
			request.setAttribute("loc", "/updateManner?reviewLike="+r.getReviewLike()+"&reviewer="+r.getReviewWriter()+"&reviewee="+r.getReviewee()+"&tradeNo="+r.getTransactionNo());
		} else {
			request.setAttribute("msg", "등록 실패 ㅠ");
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
