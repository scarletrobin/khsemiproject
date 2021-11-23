package mypage.controller;

import java.io.IOException; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.model.service.MemberService;
import member.model.vo.Member;
import mypage.model.service.UpdateMyInfoService;


/**
 * Servlet implementation class UpdateMyInfoServlet
 */
@WebServlet(name = "UpdateMyInfo", urlPatterns = { "/updateMyInfo" })
public class UpdateMyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//프로필사진 저장경로 설정	
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/member/";
		
		int maxSize = 10*1024*1024;
		
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		Member member = new Member();
		String oldFilePath = mRequest.getParameter("oldFilePath");
		System.out.println(oldFilePath);
		System.out.println(mRequest.getFilesystemName("filepath"));
		member.setMemberId(mRequest.getParameter("memberId"));
		member.setMemberPw(mRequest.getParameter("memberPw"));
		member.setPhone(mRequest.getParameter("phone"));
		
		if(mRequest.getFilesystemName("filepath") != null) {
			member.setFilePath(mRequest.getFilesystemName("filepath"));
		}else {
			member.setFilePath(oldFilePath);	
		}
		member.setSms(Integer.parseInt(mRequest.getParameter("sms")));
				
		member.setLocal1(Integer.parseInt(mRequest.getParameter("local1")));
		member.setLocal2(Integer.parseInt(mRequest.getParameter("local2")));
		member.setLocal3(Integer.parseInt(mRequest.getParameter("local3")));
		
		int result = new UpdateMyInfoService().updateMember(member);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/myUserInfo?local1="+member.getLocal1()+"&local2="+member.getLocal2()+"&local3="+member.getLocal3());
		if(result>0) {
			
			//정보변경 성공했을때 페이지에 변경된거 반영이안됐음 그래서 회원정보 다시 조회해서 세션에 다시 넣어줌
			request.setAttribute("msg", "정보 변경이 완료되었습니다.");
			//view.forward(request, response);
			
			Member m = new MemberService().selectOneMember(member.getMemberId());
			HttpSession session = request.getSession(false);
			session.setAttribute("m", m); //세션의 member를 조회할때 멤버레벨 이런게 누락됐음 주의할것 "m"인지 "member"인지 확인해볼것
		}else {
			request.setAttribute("msg", "정보 변경 실패함");
			//view.forward(request, response);	
		}
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
