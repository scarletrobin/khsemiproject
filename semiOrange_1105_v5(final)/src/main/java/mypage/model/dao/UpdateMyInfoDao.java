package mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import member.model.vo.Member;

public class UpdateMyInfoDao {

	public int updateMember(Connection conn, Member member) {
		// TODO Auto-generated method stub
				PreparedStatement pstmt = null;
				int result = 0;
				String query = "update member set member_pw=?, phone=?, filepath=?, local2=?, local3=?, sms=? where member_id=?";
				
				try {
					pstmt=conn.prepareStatement(query);
					pstmt.setString(1, member.getMemberPw());
					pstmt.setString(2, member.getPhone());
					pstmt.setString(3, member.getFilePath());
					pstmt.setInt(4, member.getLocal2());
					pstmt.setInt(5, member.getLocal3());
					pstmt.setInt(6, member.getSms());
					pstmt.setString(7, member.getMemberId());
					result = pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					JDBCTemplate.close(pstmt);
				}
				return result;
			}

	public int upgradeMember(Connection conn, int reviewLike, String reviewee) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set manner=manner+5 where member_id=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, reviewee);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int degradeMember(Connection conn, int reviewLike, String reviewee) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set manner=manner-5 where member_id=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, reviewee);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public boolean selectUpManner(Connection conn, String reviewee) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		boolean b = false;
		String query = "select manner from member where member_id=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, reviewee);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				int manner = rset.getInt("manner");
				if(manner+5>100) {
					b = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return b;
	}

	public boolean selectDownManner(Connection conn, String reviewee) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		boolean b = false;
		String query = "select manner from member where member_id=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, reviewee);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				int manner = rset.getInt("manner");
				if(manner-5<0) {
					b = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return b;
	}

	public int setMaxManner(Connection conn, String reviewee) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set manner=100 where member_id=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, reviewee);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int setMinManner(Connection conn, String reviewee) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set manner=0 where member_id=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, reviewee);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
