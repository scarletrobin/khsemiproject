package freeBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeBoard.model.service.FreeService;
import freeBoard.model.vo.FreePageData;
import freeBoard.model.vo.FreePageData2;

/**
 * Servlet implementation class SearchFreeServlet
 */
@WebServlet(name = "SearchFree", urlPatterns = { "/searchFree" })
public class SearchFreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFreeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		
		//2.값추출_라디오박스 검색
		String optionsRadios = request.getParameter("optionsRadios");
		String freeSearch = request.getParameter("freeSearch");
		//System.out.println("optionsRadios: " + optionsRadios);
		//System.out.println("freeSearch: " + freeSearch);
		
		int reqPage = 0;
		try {  // 정상적일때
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage = 1;
		}

		//3. 비즈니스 로직
		FreePageData2 fpd = new FreeService().searchFree(reqPage,optionsRadios,freeSearch);
		
		//4. 결과처리
		RequestDispatcher view 
		= request.getRequestDispatcher("/WEB-INF/views/freeBoard/freeBoard.jsp");
		request.setAttribute("list", fpd.getList());
		request.setAttribute("pageNavi", fpd.getPageNavi());
		request.setAttribute("start", fpd.getStart());
		request.setAttribute("optionsRadios", optionsRadios);
		request.setAttribute("search", freeSearch);
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
