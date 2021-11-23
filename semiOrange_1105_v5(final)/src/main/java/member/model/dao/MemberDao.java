package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.JDBCTemplate;
import email.controller.MailSender;
import member.model.vo.Member;

public class MemberDao {

	//회원가입 insert
	public int insertMember(Connection conn, Member m) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, 1, to_char(sysdate, 'yyyy-mm-dd'), 100, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getPostcode());
			pstmt.setString(6, m.getAddress1());
			pstmt.setString(7, m.getAddress2());
			pstmt.setString(8, m.getEmail());
			
			pstmt.setString(9, m.getFilePath());
			pstmt.setInt(10, m.getSms());
			pstmt.setInt(11, m.getLocal1());
			pstmt.setInt(12, m.getLocal2());
			pstmt.setInt(13, m.getLocal3());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//로그인
	public Member selectOneMember(Connection conn, String memberId, String memberPw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id=? and member_pw=?";
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPw(rset.getString("member_pw"));
				member.setMemberName(rset.getString("member_name"));
				member.setPhone(rset.getString("phone"));
				member.setPostcode(rset.getString("postcode"));
				member.setAddress1(rset.getString("address1"));
				member.setAddress2(rset.getString("address2"));
				member.setEmail(rset.getString("email"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setEnrollDate(rset.getString("enroll_date"));
				
				member.setManner(rset.getInt("manner"));
				member.setFilePath(rset.getString("filepath"));
				member.setSms(rset.getInt("sms"));
				
				member.setLocal1(rset.getInt("local1"));
				member.setLocal2(rset.getInt("local2"));
				member.setLocal3(rset.getInt("local3"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	//아이디 찾기
	public Member selectOneMemberId(Connection conn, String memberName, String email) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_name=? and email=?";
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberName);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("member_id"));
			}
			//이메일 설정부분
			if(member != null) {
				new MailSender().findIdMailAuthCodeSend(email, memberName, member.getMemberId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	//비밀번호 찾기
	public Member selectOneMemberPw(Connection conn, String memberId, String email) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from member where member_id=? and email=?";
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				member = new Member();
				member.setMemberPw(rset.getString("member_pw"));
			}
			
			//이메일 설정부분
			if(member != null) {
				new MailSender().findPwMailAuthCodeSend(email, memberId, member.getMemberPw());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	//회원수정
	public int updateMember(Connection conn, Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw=?, member_name=?, phone=?, postcode=?, address1=?, address2=?, email=?, filepath=?, sms=?, local1=?, local2=?, local3=? where member_id=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getPostcode());
			pstmt.setString(5, member.getAddress1());
			pstmt.setString(6, member.getAddress2());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getFilePath());
			pstmt.setInt(9, member.getSms());
			
			pstmt.setInt(10, member.getLocal1());
			pstmt.setInt(11, member.getLocal2());
			pstmt.setInt(12, member.getLocal3());
			
			pstmt.setString(13, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//회원가입 아이디 중복 체크
	public Member selectOneMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id=?";
		//결과처리 멤버객체
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPw(rset.getString("member_pw"));
				member.setMemberName(rset.getString("member_name"));
				member.setPhone(rset.getString("phone"));
				member.setPostcode(rset.getString("postcode"));
				member.setAddress1(rset.getString("address1"));
				member.setAddress2(rset.getString("address2"));
				member.setEmail(rset.getString("email"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setEnrollDate(rset.getString("enroll_date"));
				member.setManner(rset.getInt("manner"));
				member.setFilePath(rset.getString("filepath"));
				member.setSms(rset.getInt("sms"));
				
				//관심지역
				member.setLocal1(rset.getInt("local1"));
				member.setLocal2(rset.getInt("local2"));
				member.setLocal3(rset.getInt("local3"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	//회원 탈퇴
	public int deleteMember(Connection conn, int memberNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member where member_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		// TODO Auto-generated method stub
		return result;
	}

	//이메일 중복 체크 10월 29일 추가
	public Member selectOneEmail(Connection conn, String email) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where email=?";
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPw(rset.getString("member_pw"));
				member.setMemberName(rset.getString("member_name"));
				member.setPhone(rset.getString("phone"));
				member.setPostcode(rset.getString("postcode"));
				member.setAddress1(rset.getString("address1"));
				member.setAddress2(rset.getString("address2"));
				member.setEmail(rset.getString("email"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setEnrollDate(rset.getString("enroll_date"));
				member.setManner(rset.getInt("manner"));
				member.setFilePath(rset.getString("filepath"));
				member.setSms(rset.getInt("sms"));
				
				//관심지역
				member.setLocal1(rset.getInt("local1"));
				member.setLocal2(rset.getInt("local2"));
				member.setLocal3(rset.getInt("local3"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

}
