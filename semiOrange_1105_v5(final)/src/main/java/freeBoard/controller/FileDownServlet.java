package freeBoard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeBoard.model.service.FreeService;
import freeBoard.model.vo.FreeBoard;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet(name = "FileDown", urlPatterns = { "/fileDown" })
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServlet() {
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
		FreeBoard f = new FreeService().getFree(freeNo);
		
		//4.결과처리
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/freeBoard/";
		String file = saveDirectory + f.getFilepath();
		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		String resFilename = "";
		boolean bool = 
				request.getHeader("user-agent").indexOf("MSIE") != -1 ||
				request.getHeader("user-agent").indexOf("Trident") != -1;
		if(bool) {  //브라우저가 IE인경우
			resFilename = URLEncoder.encode(f.getFilename(),"UTF-8");
			resFilename = resFilename.replaceAll("\\\\", "%20");//\\\\
		}else {     //그외 다른 브라우저인 경우
			resFilename = new String(f.getFilename().getBytes("UTF-8"),"ISO-8859-1");
		}
		response.setContentType("application/octet-stream");
		//다운로드할 파일 이름 지정
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
		
		//파일전송
		while(true) {
			int read = bis.read();
			if(read != -1) {
				bos.write(read);
			}else {
				break;
			}
		}
		bis.close();
		bos.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
