package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "Login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		//view
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		//비즈니스
		Member member = new MemberService().selectOneMember(memberId, memberPw);
		
		//결과처리
		if(member != null) { //해당 회원이 DB내에 존재할때
			if(member.getMemberLevel()==3) { //로그인이 됐고 블랙리스트에 사고까지 쳐서 로그인 차단
				HttpSession session = request.getSession(false);
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				if(session!=null) {
					session.invalidate();
				}
				request.setAttribute("msg", "블랙리스트에 등록됐습니다. 관리자에게 문의하세요.");
				request.setAttribute("loc", "/loginFrm");			
				view.forward(request, response);
			}else { //그 외 케이스
				HttpSession session = request.getSession();
				session.setAttribute("m", member);
				response.sendRedirect("/");							
			}
		}else { //아이디, 비밀번호가 일치하지 않을 때
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "아이디 혹은 비밀번호를 확인해주세요");
			request.setAttribute("loc", "/loginFrm");			
			view.forward(request, response);
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
