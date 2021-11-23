package mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import freeBoard.model.vo.Comment1;
import freeBoard.model.vo.FreeBoard;

public class MyCommentDao {

	public ArrayList<Comment1> selectMyCommentList(Connection conn, String memberId, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Comment1> list = new ArrayList<Comment1>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, C.* FROM(SELECT * FROM COMMENT1 WHERE MEMBER_ID =? ORDER BY COMMENT_NO DESC)C) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Comment1 c = new Comment1();
				c.setCommentContent(rset.getString("COMMENT_CONTENT"));
				c.setBoardNo(rset.getInt("BOARD_NO"));
				c.setBoardType(rset.getInt("BOARD_TYPE"));
				c.setMemberId(rset.getString("MEMBER_ID"));
				c.setRegDate(rset.getString("REG_DATE"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int selectMyTotalCommentCount(Connection conn, String memberId) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "select count(*) from comment1 where member_id=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, memberId);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

	public int searchTradeComment(Connection conn, int tradeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select count(*) from comment1 where board_type=2 and board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tradeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	}

