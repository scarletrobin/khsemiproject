package mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import qna.model.vo.Qna;

public class MyQnaDao {
	public ArrayList<Qna> selectSearchQna(Connection conn, String memberId, int start, int end) {
		PreparedStatement ptst = null;
		ResultSet rset = null;
		ArrayList<Qna> list = new ArrayList<Qna>();
		String query = "\r\n"
				+ "SELECT QNA_NO, MEMBER_ID , QNA_TITLE, REG_DATE, row_num FROM (SELECT QNA_NO, MEMBER_ID , QNA_TITLE, REG_DATE , ROW_NUMBER() OVER(ORDER BY QNA_NO DESC) AS row_num FROM (SELECT qna.QNA_NO, qna.MEMBER_ID, qna.QNA_TITLE, qna.REG_DATE, coalesce(  COMMENT_COUNT_TABLE.COMMENT_COUNT, 0) AS comment_count FROM qna LEFT JOIN (SELECT BOARD_NO, COUNT(*) COMMENT_COUNT FROM COMMENT1 WHERE BOARD_TYPE=3 GROUP BY BOARD_NO) COMMENT_COUNT_TABLE ON QNA.QNA_NO = COMMENT_COUNT_TABLE.BOARD_NO WHERE MEMBER_ID =? AND COMMENT_COUNT IS NULL) ROW_COUNT ) filtering_table_count WHERE ROW_NUM BETWEEN ? AND ?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, memberId);
			ptst.setInt(2, start);
			ptst.setInt(3, end);
			rset = ptst.executeQuery();
			while(rset.next()) {
				Qna q = new Qna();
				q.setQnaNo(rset.getInt("qna_no"));
				q.setQnaTitle(rset.getString("qna_title"));
				q.setMemberId(rset.getString("member_id"));
				q.setRegDate(rset.getString("reg_date"));
				q.setQnaCount(0);
				list.add(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(ptst);
		}
		return list;
	}
	public int selectTotalCount(Connection conn, String memberId) {
		PreparedStatement ptst = null;
		ResultSet rset = null;
		int result = 0;
		String query ="SELECT COUNT(*) AS CNT\r\n"
				+ "FROM (\r\n"
				+ "            SELECT QNA_NO, MEMBER_ID , QNA_TITLE, REG_DATE , \r\n"
				+ "                        ROW_NUMBER() OVER(ORDER BY QNA_NO DESC) AS row_num\r\n"
				+ "            FROM (SELECT qna.QNA_NO, qna.MEMBER_ID, qna.QNA_TITLE, qna.REG_DATE, \r\n"
				+ "                                     coalesce(  COMMENT_COUNT_TABLE.COMMENT_COUNT, 0) AS comment_count\r\n"
				+ "                        FROM qna\r\n"
				+ "                        LEFT JOIN (\r\n"
				+ "                                        SELECT BOARD_NO, COUNT(*) COMMENT_COUNT\r\n"
				+ "                                     FROM COMMENT1\r\n"
				+ "                                     WHERE BOARD_TYPE=3\r\n"
				+ "                                     GROUP BY BOARD_NO\r\n"
				+ "                                     ) COMMENT_COUNT_TABLE\r\n"
				+ "                        ON QNA.QNA_NO = COMMENT_COUNT_TABLE.BOARD_NO\r\n"
				+ "                        WHERE MEMBER_ID = ? AND COMMENT_COUNT = 0\r\n"
				+ "                        ) ROW_COUNT\r\n"
				+ "         ) filtering_table_count";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, memberId);
			rset = ptst.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(ptst);
		}
		return result;
	}
	public ArrayList<Qna> selectAnsweredQna(Connection conn, String memberId, int start, int end) {
		PreparedStatement ptst = null;
		ResultSet rset = null;
		ArrayList<Qna> list = new ArrayList<Qna>();
		String query = "SELECT QNA_NO, MEMBER_ID , QNA_TITLE, REG_DATE, row_num FROM (SELECT QNA_NO, MEMBER_ID , QNA_TITLE, REG_DATE , ROW_NUMBER() OVER(ORDER BY QNA_NO DESC) AS row_num FROM (SELECT qna.QNA_NO, qna.MEMBER_ID, qna.QNA_TITLE, qna.REG_DATE, coalesce(  COMMENT_COUNT_TABLE.COMMENT_COUNT, 0) AS comment_count FROM qna LEFT JOIN (SELECT BOARD_NO, COUNT(*) COMMENT_COUNT FROM COMMENT1 WHERE BOARD_TYPE=3 GROUP BY BOARD_NO) COMMENT_COUNT_TABLE ON QNA.QNA_NO = COMMENT_COUNT_TABLE.BOARD_NO WHERE MEMBER_ID = ? AND COMMENT_COUNT > 0) ROW_COUNT )filtering_table_count WHERE ROW_NUM BETWEEN ? AND ?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, memberId);
			ptst.setInt(2, start);
			ptst.setInt(3, end);
			rset = ptst.executeQuery();
			while(rset.next()) {
				Qna q = new Qna();
				q.setQnaNo(rset.getInt("qna_no"));
				q.setQnaTitle(rset.getString("qna_title"));
				q.setMemberId(rset.getString("member_id"));
				q.setRegDate(rset.getString("reg_date"));
				q.setQnaCount(1);
				list.add(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(ptst);
		}
		return list;
	}
	public int selectTotalAnsweredCount(Connection conn, String memberId) {
		PreparedStatement ptst = null;
		ResultSet rset = null;
		int result = 0;
		String query ="SELECT COUNT(*) AS CNT\r\n"
				+ "FROM (\r\n"
				+ "            SELECT QNA_NO, MEMBER_ID , QNA_TITLE, REG_DATE , \r\n"
				+ "                        ROW_NUMBER() OVER(ORDER BY QNA_NO DESC) AS row_num\r\n"
				+ "            FROM (SELECT qna.QNA_NO, qna.MEMBER_ID, qna.QNA_TITLE, qna.REG_DATE, \r\n"
				+ "                                     coalesce(  COMMENT_COUNT_TABLE.COMMENT_COUNT, 0) AS comment_count\r\n"
				+ "                        FROM qna\r\n"
				+ "                        LEFT JOIN (\r\n"
				+ "                                        SELECT BOARD_NO, COUNT(*) COMMENT_COUNT\r\n"
				+ "                                     FROM COMMENT1\r\n"
				+ "                                     WHERE BOARD_TYPE=3\r\n"
				+ "                                     GROUP BY BOARD_NO\r\n"
				+ "                                     ) COMMENT_COUNT_TABLE\r\n"
				+ "                        ON QNA.QNA_NO = COMMENT_COUNT_TABLE.BOARD_NO\r\n"
				+ "                        WHERE MEMBER_ID = ? AND COMMENT_COUNT > 0 \r\n"
				+ "                        ) ROW_COUNT\r\n"
				+ "         ) filtering_table_count";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, memberId);
			rset = ptst.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(ptst);
		}
		return result;
	}

}
