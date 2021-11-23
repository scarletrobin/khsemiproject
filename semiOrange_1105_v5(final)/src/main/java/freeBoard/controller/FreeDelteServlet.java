package freeBoard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeBoard.model.service.FreeService;
import freeBoard.model.vo.FreeBoard;

/**
 * Servlet implementation class FreeDelteServlet
 */
@WebServlet(name = "FreeDelete", urlPatterns = { "/freeDelete" })
public class FreeDelteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeDelteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		
		//2.값추출
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		
		//3.비즈니스로직
		FreeService service = new FreeService();
		FreeBoard f = service.getFree(freeNo);
		int result = service.freeDelte(freeNo);
		
		//4.화면출력
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			if(f.getFilepath() != null) {
				String root = getServletContext().getRealPath("/");
				String file = root + "upload/freeBoard/" + f.getFilepath();
				File delFile = new File(file);
				delFile.delete();
			}
			request.setAttribute("msg", "게시물 삭제 성공!!!");
			request.setAttribute("loc", "/freeBoard?reqPage=1");
		}else {
			request.setAttribute("msg", "게시물 삭제 실패");
			request.setAttribute("loc", "/freeView?freeNo="+freeNo);
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
