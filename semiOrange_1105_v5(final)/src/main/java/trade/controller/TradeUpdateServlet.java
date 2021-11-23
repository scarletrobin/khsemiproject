package trade.controller;

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

import trade.model.service.TradeService;
import trade.model.vo.Trade;

/**
 * Servlet implementation class TradeUpdateServlet
 */
@WebServlet(name = "TradeUpdate", urlPatterns = { "/tradeUpdate" })
public class TradeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "게시판 작성 오류 [enctype확인]");
			request.setAttribute("loc", "/photoList");
			view.forward(request, response);
			return;
		}
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/trade";
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		Trade t = new Trade();
		t.setTradeNo(Integer.parseInt(mRequest.getParameter("tradeNo")));
		t.setTradeTitle(mRequest.getParameter("tradeTitle"));
		t.setCategory(mRequest.getParameter("category"));
		t.setPrice(Integer.parseInt(mRequest.getParameter("price")));
		t.setTradeLocal(Integer.parseInt(mRequest.getParameter("tradeLocal")));
		t.setTradeContent(mRequest.getParameter("tradeContent"));
		t.setFilename(mRequest.getOriginalFileName("tradeImg"));
		t.setFilepath(mRequest.getFilesystemName("tradeImg"));
		// 비즈니스 로직
		int result = new TradeService().updateTrade(t);
		// 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result > 0) {
			request.setAttribute("msg", "게시물 수정이 완료되었습니다.");
		} else {
			request.setAttribute("msg", "게시물 수정을 실패하였습니다.");
		}
		request.setAttribute("loc", "/tradeView?tradeNo=" + t.getTradeNo());
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
