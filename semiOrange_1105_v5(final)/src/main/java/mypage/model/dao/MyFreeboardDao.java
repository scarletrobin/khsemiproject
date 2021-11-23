package mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import freeBoard.model.vo.FreeBoard;

public class MyFreeboardDao {

	public ArrayList<FreeBoard> selectMyFreeboardList(Connection conn, String memberId, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FreeBoard> list = new ArrayList<FreeBoard>();
		String query = "SELECT FF.*,(select count(*) from comment1 where board_no=free_no and board_type=1) AS NC_COUNT FROM (SELECT ROWNUM AS RNUM, f.* FROM(SELECT * FROM freeboard where priority = 0 AND FREE_WRITER=? ORDER BY REG_DATE DESC)f)FF WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FreeBoard f = new FreeBoard();
				f.setFreeNo(rset.getInt("free_no"));
				f.setFreeTitle(rset.getString("free_title"));
				f.setFreeWriter(rset.getString("free_writer"));
				f.setNcCount(rset.getInt("nc_count"));
				f.setReadCount(rset.getInt("read_count"));
				f.setRegDate(rset.getString("reg_date"));
				list.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;

	}

	public int selectMyTotalFreeboardCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select count(*) from\r\n"
				+ "(SELECT *\r\n"
				+ "FROM (\r\n"
				+ "            SELECT ROW_NUMBER() OVER(PARTITION BY FREE_WRITER ORDER BY FREE_NO DESC) AS row_num,\r\n"
				+ "                    FREEBOARD.FREE_NO, FREEBOARD.FREE_TITLE, FREEBOARD.FREE_WRITER, FREEBOARD.REG_DATE, FREEBOARD.READ_COUNT, \r\n"
				+ "                    coalesce(COMMENT_COUNT_TABLE.COMMENT_COUNT, 0) AS comment_count\r\n"
				+ "            FROM FREEBOARD\r\n"
				+ "            LEFT JOIN (\r\n"
				+ "                            SELECT BOARD_NO, COUNT(*) COMMENT_COUNT\r\n"
				+ "                         FROM COMMENT1\r\n"
				+ "                         WHERE BOARD_TYPE=3\r\n"
				+ "                         GROUP BY BOARD_NO\r\n"
				+ "                         ) COMMENT_COUNT_TABLE\r\n"
				+ "            ON FREEBOARD.FREE_NO = COMMENT_COUNT_TABLE.BOARD_NO\r\n"
				+ "            WHERE FREE_WRITER =?\r\n"
				+ "        ) rownum_table)";
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

	}
