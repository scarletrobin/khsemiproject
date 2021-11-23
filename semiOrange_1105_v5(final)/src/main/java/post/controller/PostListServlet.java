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
 * Servlet implementation class PostListServlet
 */
@WebServlet(name = "PostList", urlPatterns = { "/postList" })
public class PostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostListServlet() {
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
		//내가 받은 쪽지함
		PostPageData ppd = new PostService().selectPostList(reqPage, memberId);

		//내가 받은 쪽지함
		request.setAttribute("postCnt", postCnt);
		request.setAttribute("list", ppd.getList());
		request.setAttribute("pageNavi", ppd.getPageNavi());
		request.setAttribute("start", ppd.getStart());
		request.setAttribute("totalCount", ppd.getTotalPostCount());
		
		request.getRequestDispatcher("/WEB-INF/views/post/postList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
