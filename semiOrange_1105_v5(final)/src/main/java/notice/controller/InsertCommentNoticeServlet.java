package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.NoticeComment;

/**
 * Servlet implementation class InsertCommentNoticeServlet
 */
@WebServlet(name = "InsertCommentNotice", urlPatterns = { "/insertCommentNotice" })
public class InsertCommentNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCommentNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeComment nc = new NoticeComment();
		nc.setCommentContent(request.getParameter("commentContent"));
		nc.setBoardType(4);
		nc.setMemberId(request.getParameter("memberId"));
		nc.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		int result = new NoticeService().insertComment(nc);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글등록");
		}else {
			request.setAttribute("msg", "등록실패");
		}
		request.setAttribute("loc", "noticeView?noticeNo="+nc.getBoardNo());
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
