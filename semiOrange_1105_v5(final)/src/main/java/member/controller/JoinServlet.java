package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet(name = "Join", urlPatterns = { "/join" })
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");

		String root = getServletContext().getRealPath("/");
		System.out.println(root);

		String saveDirectory = root + "upload/member";
		System.out.println("파일 저장 경로는 : " + saveDirectory);

		int maxSize = 10 * 1024 * 1024; // 10메가로 바꿈

		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		
		// 2. 뷰에서 넘어온 데이터 추출
		Member m = new Member();
		m.setMemberId(mRequest.getParameter("memberId"));
		m.setMemberPw(mRequest.getParameter("memberPw"));
		m.setMemberName(mRequest.getParameter("memberName"));
		m.setPhone(mRequest.getParameter("phone"));
		m.setPostcode(mRequest.getParameter("postcode"));
		m.setAddress1(mRequest.getParameter("address1"));
		m.setAddress2(mRequest.getParameter("address2"));
		m.setEmail(mRequest.getParameter("email"));
		m.setFilePath(mRequest.getFilesystemName("filepath"));
		m.setSms(Integer.parseInt(mRequest.getParameter("sms")));
		m.setLocal1(Integer.parseInt(mRequest.getParameter("local1")));
		m.setLocal2(Integer.parseInt(mRequest.getParameter("local2")));
		m.setLocal3(Integer.parseInt(mRequest.getParameter("local3")));
		
		// 3. 비지니스 로직
		// 회원가입 하기위해 insert함
		int result = new MemberService().insertMember(m);

		// 4. 결과처리
		if (result > 0) { // 성공
			response.sendRedirect("/"); // 성공하면 섹션으로 이동할지
		} else { // 실패시 실패 메세지를 띄우고, 회원가입 서블릿으로 다시 이동
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "회원 가입 실패했습니다.");
			request.setAttribute("loc", "/joinFrm");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
