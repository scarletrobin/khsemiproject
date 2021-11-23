package member.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	//회원가입
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().insertMember(conn, m); //dao에서 쿼리 처리
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//로그인
	public Member selectOneMember(String memberId, String memberPw) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMember(conn, memberId, memberPw);
		JDBCTemplate.close(conn);
		return member;
	}
	
	//아이디 찾기
	public Member selectOneMemberId(String memberName, String email) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMemberId(conn, memberName, email);
		JDBCTemplate.close(conn);
		return member;
	}

	//비밀번호 찾기 
	public Member selectOneMemberPw(String memberId, String email) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMemberPw(conn, memberId, email);
		JDBCTemplate.close(conn);
		return member;
	}

	//회원정보 수정
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateMember(conn, member);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//회원수정 후 수정된것 불러오는거, 아이디 중복 체크
	public Member selectOneMember(String memberId) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMember(conn, memberId);
		JDBCTemplate.close(conn);
		return member;
	}

	
	//회원탈퇴
	public int deleteMember(int memberNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteMember(conn, memberNo);
		
		if(result >0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	//이메일 중복 체크 10월 29일 추가
	public Member selectOneEmail(String email) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneEmail(conn, email);
		JDBCTemplate.close(conn);
		return member;
	}


	
	
	
	
	
	
	

}
