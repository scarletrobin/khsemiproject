package trade.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import trade.model.vo.PostFrom;
import trade.model.vo.Trade;
import trade.model.vo.TradeMember;

public class TradeDao {

	public int insertTrade(Connection conn, Trade t) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query="INSERT INTO TRADE VALUES(TRADE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, 0, 1, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), ?, ?, DEFAULT, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, t.getTradeWriter());
			pstmt.setString(2, t.getTradeTitle());
			pstmt.setString(3, t.getTradeContent());
			pstmt.setString(4, t.getCategory());
			pstmt.setInt(5, t.getPrice());
			pstmt.setString(6, t.getFilename());
			pstmt.setString(7, t.getFilepath());
			pstmt.setInt(8, t.getTradeLocal());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Trade> selectTradeList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Trade> list = new ArrayList<Trade>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT B.*, A.SIGUNGU_DO, A.SIGUNGU_NAME FROM TRADE B LEFT JOIN SIGUNGU A ON (B.TRADE_LOCAL = A.SIGUNGU_NO)ORDER BY TRADE_NO DESC)N) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Trade t = new Trade();
				t.setTradeNo(rset.getInt("TRADE_NO"));
				t.setTradeWriter(rset.getString("TRADE_WRITER"));
				t.setTradeTitle(rset.getString("TRADE_TITLE"));
				t.setTradeContent(rset.getString("TRADE_CONTENT"));
				t.setCategory(rset.getString("CATEGORY"));
				t.setPrice(rset.getInt("PRICE"));
				t.setReadCount(rset.getInt("READ_COUNT"));
				t.setTradeStatus(rset.getInt("TRADE_STATUS"));
				t.setRegDate(rset.getString("REG_DATE"));
				t.setFilename(rset.getString("FILENAME"));
				t.setFilepath(rset.getString("FILEPATH"));
				t.setTradeDate(rset.getString("TRADE_DATE"));
				t.setTradeLocal(rset.getInt("TRADE_LOCAL"));
				t.setSigunguDo(rset.getString("SIGUNGU_DO"));
				t.setSigunguName(rset.getString("SIGUNGU_NAME"));
				list.add(t);
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

	public int selectTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM TRADE";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("CNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateReadCount(Connection conn, int tradeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE TRADE SET READ_COUNT = READ_COUNT + 1 WHERE TRADE_NO=?";
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

	public Trade selectOneTrade(Connection conn, int tradeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Trade t = null;
		String query = "SELECT B.*, A.SIGUNGU_DO, A.SIGUNGU_NAME FROM TRADE B LEFT JOIN SIGUNGU A ON (B.TRADE_LOCAL = A.SIGUNGU_NO) WHERE TRADE_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tradeNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				t = new Trade();
				t.setTradeNo(rset.getInt("TRADE_NO"));
				t.setTradeWriter(rset.getString("TRADE_WRITER"));
				t.setTradeTitle(rset.getString("TRADE_TITLE"));
				t.setTradeContent(rset.getString("TRADE_CONTENT"));
				t.setCategory(rset.getString("CATEGORY"));
				t.setPrice(rset.getInt("PRICE"));
				t.setReadCount(rset.getInt("READ_COUNT"));
				t.setTradeStatus(rset.getInt("TRADE_STATUS"));
				t.setRegDate(rset.getString("REG_DATE"));
				t.setFilename(rset.getString("FILENAME"));
				t.setFilepath(rset.getString("FILEPATH"));
				t.setTradeDate(rset.getString("TRADE_DATE"));
				t.setTradeLocal(rset.getInt("TRADE_LOCAL"));
				t.setSigunguDo(rset.getString("SIGUNGU_DO"));
				t.setSigunguName(rset.getString("SIGUNGU_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return t;
	}

	public TradeMember selectTradeMember(Connection conn, int tradeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TradeMember tm = null;
		String query = "SELECT MANNER, FILEPATH FROM MEMBER WHERE MEMBER_ID=(SELECT TRADE_WRITER FROM TRADE WHERE TRADE_NO=?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tradeNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				tm = new TradeMember();
				tm.setManner(rset.getInt("MANNER"));
				tm.setFilepath(rset.getString("FILEPATH"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return tm;
	}

	public int updateTrade(Connection conn, Trade t) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE TRADE SET TRADE_TITLE=?, TRADE_CONTENT=?, CATEGORY=?, PRICE=?, FILENAME=?, FILEPATH=?, TRADE_LOCAL=? WHERE TRADE_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, t.getTradeTitle());
			pstmt.setString(2, t.getTradeContent());
			pstmt.setString(3, t.getCategory());
			pstmt.setInt(4, t.getPrice());
			pstmt.setString(5, t.getFilename());
			pstmt.setString(6, t.getFilepath());
			pstmt.setInt(7, t.getTradeLocal());
			pstmt.setInt(8, t.getTradeNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteTrade(Connection conn, int tradeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM TRADE WHERE TRADE_NO=?";
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

	public ArrayList<Trade> selectTradeHistory(Connection conn, int start, int end, String tradeWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Trade> list = new ArrayList<Trade>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT * FROM TRADE WHERE TRADE_WRITER=? ORDER BY TRADE_NO DESC)N) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tradeWriter);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Trade t = new Trade();
				t.setTradeNo(rset.getInt("TRADE_NO"));
				t.setTradeWriter(rset.getString("TRADE_WRITER"));
				t.setTradeTitle(rset.getString("TRADE_TITLE"));
				t.setTradeContent(rset.getString("TRADE_CONTENT"));
				t.setCategory(rset.getString("CATEGORY"));
				t.setPrice(rset.getInt("PRICE"));
				t.setReadCount(rset.getInt("READ_COUNT"));
				t.setTradeStatus(rset.getInt("TRADE_STATUS"));
				t.setRegDate(rset.getString("REG_DATE"));
				t.setFilename(rset.getString("FILENAME"));
				t.setFilepath(rset.getString("FILEPATH"));
				t.setTradeDate(rset.getString("TRADE_DATE"));
				list.add(t);
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

	public int selectTotalTrade(Connection conn, String tradeWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM TRADE WHERE TRADE_WRITER=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tradeWriter);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("CNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Trade> selectTradeLoginList(Connection conn, String memberId, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Trade> list = new ArrayList<Trade>();
		String query = "SELECT * FROM" + 
				"(SELECT ROWNUM AS RNUM, N.* FROM" + 
				"(SELECT B.*, A.SIGUNGU_DO, A.SIGUNGU_NAME FROM TRADE B LEFT JOIN SIGUNGU A ON (B.TRADE_LOCAL = A.SIGUNGU_NO) WHERE TRADE_LOCAL=(SELECT LOCAL1 FROM MEMBER WHERE MEMBER_ID=?)" + 
				"OR TRADE_LOCAL=(SELECT LOCAL2 FROM MEMBER WHERE MEMBER_ID=?)" + 
				"OR TRADE_LOCAL=(SELECT LOCAL3 FROM MEMBER WHERE MEMBER_ID=?) ORDER BY TRADE_NO DESC)N) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberId);
			pstmt.setString(3, memberId);
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Trade t = new Trade();
				t.setTradeNo(rset.getInt("TRADE_NO"));
				t.setTradeWriter(rset.getString("TRADE_WRITER"));
				t.setTradeTitle(rset.getString("TRADE_TITLE"));
				t.setTradeContent(rset.getString("TRADE_CONTENT"));
				t.setCategory(rset.getString("CATEGORY"));
				t.setPrice(rset.getInt("PRICE"));
				t.setReadCount(rset.getInt("READ_COUNT"));
				t.setTradeStatus(rset.getInt("TRADE_STATUS"));
				t.setRegDate(rset.getString("REG_DATE"));
				t.setFilename(rset.getString("FILENAME"));
				t.setFilepath(rset.getString("FILEPATH"));
				t.setTradeDate(rset.getString("TRADE_DATE"));
				t.setTradeLocal(rset.getInt("TRADE_LOCAL"));
				t.setSigunguDo(rset.getString("SIGUNGU_DO"));
				t.setSigunguName(rset.getString("SIGUNGU_NAME"));
				list.add(t);
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
	
	public int changeStatus(Connection conn, int status, int tradeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE TRADE SET TRADE_STATUS=? WHERE TRADE_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, status);
			pstmt.setInt(2, tradeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<PostFrom> selectSeller(Connection conn, String tradeWriter, int tradeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PostFrom> list = new ArrayList<PostFrom>();
		String query = "SELECT DISTINCT POST_FROM FROM POST WHERE POST_TO=? AND TRADE_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tradeWriter);
			pstmt.setInt(2, tradeNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				PostFrom pf = new PostFrom();
				pf.setPostFrom(rset.getString("POST_FROM"));
				list.add(pf);
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

	public int transaction(Connection conn, String buyer, int tradeNo, String seller, int transactionPrice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO TRANSACTION VALUES(TRANSACTION_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, buyer);
			pstmt.setInt(2, tradeNo);
			pstmt.setString(3, seller);
			pstmt.setInt(4, transactionPrice);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int transactionStatus(Connection conn, int tradeNo, int tradeStatus) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE TRADE SET TRADE_STATUS=?, TRADE_DATE=TO_CHAR(SYSDATE, 'YYYY-MM-DD') WHERE TRADE_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tradeStatus);
			pstmt.setInt(2, tradeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertReport(Connection conn, String reportWriter, String reportedMember, String reportContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO REPORT VALUES(REPORT_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), DEFAULT)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reportWriter);
			pstmt.setString(2, reportedMember);
			pstmt.setString(3, reportContent);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Trade> mainTradeList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Trade> list = new ArrayList<Trade>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT B.*, A.SIGUNGU_DO, A.SIGUNGU_NAME FROM TRADE B LEFT JOIN SIGUNGU A ON (B.TRADE_LOCAL = A.SIGUNGU_NO) WHERE (TRADE_STATUS=1 OR TRADE_STATUS=2) ORDER BY READ_COUNT DESC)N) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Trade t = new Trade();
				t.setTradeNo(rset.getInt("TRADE_NO"));
				t.setTradeWriter(rset.getString("TRADE_WRITER"));
				t.setTradeTitle(rset.getString("TRADE_TITLE"));
				t.setTradeContent(rset.getString("TRADE_CONTENT"));
				t.setCategory(rset.getString("CATEGORY"));
				t.setPrice(rset.getInt("PRICE"));
				t.setReadCount(rset.getInt("READ_COUNT"));
				t.setTradeStatus(rset.getInt("TRADE_STATUS"));
				t.setRegDate(rset.getString("REG_DATE"));
				t.setFilename(rset.getString("FILENAME"));
				t.setFilepath(rset.getString("FILEPATH"));
				t.setTradeDate(rset.getString("TRADE_DATE"));
				t.setTradeLocal(rset.getInt("TRADE_LOCAL"));
				t.setSigunguDo(rset.getString("SIGUNGU_DO"));
				t.setSigunguName(rset.getString("SIGUNGU_NAME"));
				list.add(t);
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

	public ArrayList<Trade> selectSearch(Connection conn, int start, int end, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Trade> list = new ArrayList<Trade>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT B.*, A.SIGUNGU_DO, A.SIGUNGU_NAME FROM TRADE B LEFT JOIN SIGUNGU A ON (B.TRADE_LOCAL = A.SIGUNGU_NO)\r\n"
				+ "WHERE (TRADE_TITLE LIKE ?) OR (SIGUNGU_DO LIKE ?) OR (SIGUNGU_DO LIKE ?) ORDER BY TRADE_NO DESC)N) WHERE RNUM BETWEEN ? AND ?\r\n";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, '%'+search+'%');
			pstmt.setString(2, '%'+search+'%');
			pstmt.setString(3, '%'+search+'%');
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Trade t = new Trade();
				t.setTradeNo(rset.getInt("TRADE_NO"));
				t.setTradeWriter(rset.getString("TRADE_WRITER"));
				t.setTradeTitle(rset.getString("TRADE_TITLE"));
				t.setTradeContent(rset.getString("TRADE_CONTENT"));
				t.setCategory(rset.getString("CATEGORY"));
				t.setPrice(rset.getInt("PRICE"));
				t.setReadCount(rset.getInt("READ_COUNT"));
				t.setTradeStatus(rset.getInt("TRADE_STATUS"));
				t.setRegDate(rset.getString("REG_DATE"));
				t.setFilename(rset.getString("FILENAME"));
				t.setFilepath(rset.getString("FILEPATH"));
				t.setTradeDate(rset.getString("TRADE_DATE"));
				t.setTradeLocal(rset.getInt("TRADE_LOCAL"));
				t.setSigunguDo(rset.getString("SIGUNGU_DO"));
				t.setSigunguName(rset.getString("SIGUNGU_NAME"));
				list.add(t);
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

}
