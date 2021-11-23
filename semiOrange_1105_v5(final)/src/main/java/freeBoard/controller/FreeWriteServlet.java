package freeBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import freeBoard.model.service.FreeService;
import freeBoard.model.vo.FreeBoard;

/**
 * Servlet implementation class FreeWriteServlet
 */
@WebServlet(name = "FreeWrite", urlPatterns = { "/freeWrite" })
public class FreeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeWriteServlet() {
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
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view 
			= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "자유게시판 작성 오류입니다.");
			request.setAttribute("loc", "/");
			view.forward(request, response);
			return;
		}

		String root = getServletContext().getRealPath("/"); //파일업로드 경로 지정
		String saveDirectory = root+"upload/freeBoard";
		
		int maxSize = 10*1024*1024; //업로드 파일 최대크기
		
		MultipartRequest mRequest   // MultipartRequest객체로 변환
		= new MultipartRequest
		(request, saveDirectory, maxSize,"UTF-8",new DefaultFileRenamePolicy());
		
		FreeBoard f = new FreeBoard();
		
		f.setFreeWriter(mRequest.getParameter("freeWriter"));
		f.setFreeTitle(mRequest.getParameter("freeTitle"));
		f.setFreeContent(mRequest.getParameter("freeContent"));
		f.setFilename(mRequest.getOriginalFileName("upfile"));
		f.setFilepath(mRequest.getFilesystemName("upfile"));
		String priority = mRequest.getParameter("priority");
		if(priority == null) {
			f.setPriority(0);
		}else {
			f.setPriority(1);
		}
		
		//3. 비즈니스 로직
		int result = new FreeService().insertFree(f);
		
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/freeBoard?reqPage=1");
		if(result>0) {
			request.setAttribute("msg", "게시판 등록성공!!!");
		}else {
			request.setAttribute("msg", "게시판 등록실패");
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
