package freeBoard.controller;

import java.io.File;
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
 * Servlet implementation class FreeUpdateServlet
 */
@WebServlet(name = "FreeUpdate", urlPatterns = { "/freeUpdate" })
public class FreeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2.값추출
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "오류입니다.");
			request.setAttribute("loc", "/");
			view.forward(request, response);
			return;
		}
		
		//파일업로드
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/freeboard";
		
		//파일업로드 최대 크기 지정(10MB)
		int maxSize = 10*1024*1024;
		
		//request -> MultipartRequest로 변환
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		FreeBoard f = new FreeBoard();
		f.setFreeNo(Integer.parseInt(mRequest.getParameter("freeNo")));
		f.setFreeTitle(mRequest.getParameter("freeTitle"));
		f.setFreeContent(mRequest.getParameter("freeContent"));
		f.setFilename(mRequest.getOriginalFileName("upfile"));
		f.setFilepath(mRequest.getFilesystemName("upfile"));
		String priority = mRequest.getParameter("priority");
		// System.out.println(priority);
		if(priority == null) {
			f.setPriority(0);
		}else {
			f.setPriority(1);
		}
		
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		
		int status = Integer.parseInt(mRequest.getParameter("status"));
		if(status == 2) {
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
		}else if(oldFilename != null) {
			f.setFilename(oldFilename);
			f.setFilepath(oldFilename);
		}

		//3.비즈니스로직 
		int result = new FreeService().updateFree(f);
		
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "자유게시판 수정 완료!!!");
		}else {
			request.setAttribute("msg", "자유게시판 수정 실패");
		}
		request.setAttribute("loc", "freeView?freeNo="+f.getFreeNo());
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
