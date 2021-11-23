package mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypage.model.vo.Transaction;
import trade.model.vo.Trade;

public class MyTradeDao {

	public ArrayList<Trade> selectMyTradeList(Connection conn, String memberId, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Trade> list = new ArrayList<Trade>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM,T.* FROM(SELECT * FROM TRADE WHERE TRADE_WRITER = ? AND TRADE_STATUS=3 ORDER BY TRADE_NO DESC)T) WHERE RNUM BETWEEN ? AND ?"; 
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Trade t= new Trade();
				t.setTradeNo(rset.getInt("trade_no"));
				t.setTradeTitle(rset.getString("trade_title"));
				t.setCategory(rset.getString("category"));
				t.setTradeWriter(rset.getString("trade_writer"));
				t.setRegDate(rset.getString("reg_date"));
				t.setPrice(rset.getInt("price"));
				t.setTradeDate(rset.getString("trade_date"));
				t.setFilepath(rset.getString("filepath"));
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

	public int selectTotalTradeCount(Connection conn, String memberId) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = 0;
			String query ="select count(*) as cnt from\r\n"
					+ "(SELECT * FROM (SELECT ROWNUM AS RNUM,T.* FROM(SELECT * FROM TRADE WHERE TRADE_WRITER = ? AND TRADE_STATUS=3 ORDER BY TRADE_NO DESC)T))";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, memberId);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					result = rset.getInt("cnt");
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

	public ArrayList<Transaction> selectMyTradeBuyList(Connection conn, String memberId, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Transaction> list = new ArrayList<Transaction>();
		String query = "SELECT ROWNUM_TABLE.*,\r\n"
				+ "    (select count(*) from review where transaction_no = trade_no) as review_count \r\n"
				+ "    FROM \r\n"
				+ "    (SELECT ROW_NUMBER() OVER(ORDER BY TRADE.TRADE_NO DESC) AS ROW_NUM,  TRADE.* \r\n"
				+ "    FROM TRADE INNER JOIN \r\n"
				+ "    (SELECT TRANSACTION_NO, TRADE_NO, BUYER FROM TRANSACTION WHERE BUYER=? )BUYER_TRANSACTION_TABLE ON TRADE.TRADE_NO = BUYER_TRANSACTION_TABLE.TRADE_NO) ROWNUM_TABLE \r\n"
				+ "    where row_num between ? and ?"; 
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Transaction t= new Transaction();
				t.setTradeNo(rset.getInt("trade_no"));
				t.setTradeTitle(rset.getString("trade_title"));
				t.setCategory(rset.getString("category"));
				t.setTransactionDate(rset.getString("trade_date"));
				t.setSeller(rset.getString("trade_writer"));
				t.setTransactionPrice(rset.getInt("price"));
				t.setFilePath(rset.getString("filepath"));
				t.setReviewCount(rset.getInt("review_count"));
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

	public ArrayList<Trade> selectMyTradePostList(Connection conn, String memberId, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Trade> list = new ArrayList<Trade>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM,T.* FROM(SELECT * FROM TRADE WHERE TRADE_WRITER = ? AND TRADE_STATUS IN (1,2) ORDER BY TRADE_NO DESC)T) WHERE RNUM BETWEEN ? AND ?"; 
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Trade t= new Trade();
				t.setTradeNo(rset.getInt("trade_no"));
				t.setTradeTitle(rset.getString("trade_title"));
				t.setCategory(rset.getString("category"));
				t.setTradeWriter(rset.getString("trade_writer"));
				t.setRegDate(rset.getString("reg_date"));
				t.setPrice(rset.getInt("price"));
				t.setTradeStatus(rset.getInt("trade_status"));
				t.setFilepath(rset.getString("filepath"));
				t.setFilename(rset.getString("filename"));
				t.setTradeLocal(rset.getInt("trade_local"));
				t.setReadCount(rset.getInt("read_count"));
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

	public int selectTotalTradePostCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query ="select count(*) as cnt from\r\n"
				+ "(SELECT * FROM (SELECT ROWNUM AS RNUM,T.* FROM(SELECT * FROM TRADE WHERE TRADE_WRITER = ? AND TRADE_STATUS IN (1,2) ORDER BY TRADE_NO DESC)T))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
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

	public int selectTotalTradeBuyCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select count(*) from (SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY TRADE.TRADE_NO DESC) AS ROW_NUM,  TRADE.* FROM TRADE INNER JOIN (SELECT TRANSACTION_NO, TRADE_NO, BUYER FROM TRANSACTION WHERE BUYER=? )BUYER_TRANSACTION_TABLE ON TRADE.TRADE_NO = BUYER_TRANSACTION_TABLE.TRADE_NO) ROWNUM_TABLE)";
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

	public int changeTradeStatus(Connection conn, int tradeNo, int tradeStatus) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update trade set trade_status=? where trade_no=?";
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

	public int selectTransactionNo(Connection conn, int tradeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select transaction_no from transaction where trade_no =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tradeNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	}

