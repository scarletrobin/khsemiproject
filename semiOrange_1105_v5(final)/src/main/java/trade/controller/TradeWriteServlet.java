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
 * Servlet implementation class TradeWriteServlet
 */
@WebServlet(name = "TradeWrite", urlPatterns = { "/tradeWrite" })
public class TradeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "enctype확인");
			request.setAttribute("loc", "/tradeList");
			view.forward(request, response);
			return;
		}
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/trade";
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		Trade t = new Trade();
		t.setTradeTitle(mRequest.getParameter("tradeTitle"));
		t.setCategory(mRequest.getParameter("category"));
		t.setPrice(Integer.parseInt(mRequest.getParameter("price")));
		t.setTradeLocal(Integer.parseInt(mRequest.getParameter("tradeLocal")));
		t.setTradeContent(mRequest.getParameter("tradeContent"));
		t.setTradeWriter(mRequest.getParameter("tradeWriter"));
		t.setFilename(mRequest.getOriginalFileName("tradeImg"));
		t.setFilepath(mRequest.getFilesystemName("tradeImg"));
		// 비즈니스 로직
		int result = new TradeService().insertTrade(t);
		// 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result > 0) {
			request.setAttribute("msg", "게시물 등록 완료");
		} else {
			request.setAttribute("msg", "게시물 등록 실패");
		}
		request.setAttribute("loc", "/myPosts?reqPage=1");
		//request.setAttribute("loc", "/tradeHistory?reqPage=1&tradeWriter=" + t.getTradeWriter());
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
