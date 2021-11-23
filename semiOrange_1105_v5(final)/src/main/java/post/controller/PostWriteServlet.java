package post.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.model.service.PostService;
import post.model.vo.Post;

/**
 * Servlet implementation class PostWriteServlet
 */
@WebServlet(name = "PostWrite", urlPatterns = { "/postWrite" })
public class PostWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//인코딩
		request.setCharacterEncoding("utf-8");	
		
		//뷰에서 넘어온 값추출
		Post p = new Post();
		p.setPostTitle(request.getParameter("postTitle"));
		p.setPostFrom(request.getParameter("postFrom"));
		p.setPostTo(request.getParameter("postTo"));
		p.setPostContent(request.getParameter("postContent"));
		p.setTradeNo(Integer.parseInt(request.getParameter("tradeNo")));
		
		
		//비지니스 로직
		int result = new PostService().insertPost(p);
		//결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/postList?reqPage=1");

		if(result>0) {
			request.setAttribute("msg", "쪽지 작성 성공");
		}else {
			request.setAttribute("msg", "쪽지 작성 실패");
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
