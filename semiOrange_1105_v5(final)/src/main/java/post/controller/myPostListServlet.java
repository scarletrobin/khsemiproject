package post.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import post.model.service.PostService;
import post.model.vo.PostPageData;

/**
 * Servlet implementation class myPostListServlet
 */
@WebServlet(name = "myPostList", urlPatterns = { "/myPostList" })
public class myPostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myPostListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		Member mm = (Member)session.getAttribute("m");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String memberId = mm.getMemberId();
		int postCnt = new PostService().selectNotReadPost(memberId);
		//내가 보낸 쪽지함
		PostPageData myPpd = new PostService().selectMySendList(reqPage, memberId);
		
		//내가 보낸 쪽지함
		request.setAttribute("postCnt", postCnt);
		request.setAttribute("myList", myPpd.getList());
		request.setAttribute("myPageNavi", myPpd.getPageNavi());
		request.setAttribute("myStart", myPpd.getStart());
		request.setAttribute("myTotalCount", myPpd.getTotalPostCount());
		
		request.getRequestDispatcher("/WEB-INF/views/post/myPostList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
