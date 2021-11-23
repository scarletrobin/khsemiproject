package trade.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import trade.model.dao.TradeDao;
import trade.model.vo.PostFrom;
import trade.model.vo.Trade;
import trade.model.vo.TradeMember;
import trade.model.vo.TradeMemberData;
import trade.model.vo.TradePageData;

public class TradeService {

	public int insertTrade(Trade t) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new TradeDao().insertTrade(conn, t);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public TradePageData selectTradeList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 12;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		TradeDao dao = new TradeDao();
		ArrayList<Trade> list = dao.selectTradeList(conn, start, end);
		
		// 페이지 네비게이션
		int totalCount = dao.selectTotalCount(conn);
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = (totalCount / numPerPage) + 1;
		}
		int pageNaviSize = 5;
		int pageNo = (((reqPage - 1) / pageNaviSize) * pageNaviSize) + 1;
		
		String pageNavi = "<ul class='pagination'>";
		
		if (pageNo != 1) {
			pageNavi += "<li class='page-item disabled'>";
			pageNavi += "<a class='page-link' href='/tradeList?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "&laquo;</a></li>";
		}
		
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/tradeList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/tradeList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/tradeList?reqPage=" + pageNo + "'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		
		TradePageData tpd = new TradePageData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return tpd;
	}

	public TradeMemberData selectOneTrade(int tradeNo) {
		Connection conn = JDBCTemplate.getConnection();
		TradeDao dao = new TradeDao();
		int result = dao.updateReadCount(conn, tradeNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		Trade t = dao.selectOneTrade(conn, tradeNo);
		TradeMember tm = dao.selectTradeMember(conn, tradeNo);
		ArrayList<PostFrom> pf = dao.selectSeller(conn, t.getTradeWriter(), tradeNo);
		TradeMemberData tmd = new TradeMemberData(t, tm, pf);
		JDBCTemplate.close(conn);
		return tmd;
	}

	public Trade getTrade(int tradeNo) {
		Connection conn = JDBCTemplate.getConnection();
		Trade t = new TradeDao().selectOneTrade(conn, tradeNo);
		JDBCTemplate.close(conn);
		return t;
	}

	public int updateTrade(Trade t) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new TradeDao().updateTrade(conn, t);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteTrade(int tradeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new TradeDao().deleteTrade(conn, tradeNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public TradePageData selectTradeHistory(int reqPage, String tradeWriter) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 5;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		TradeDao dao = new TradeDao();
		ArrayList<Trade> list = dao.selectTradeHistory(conn, start, end, tradeWriter);
		
		// 페이지 네비게이션
		int totalCount = dao.selectTotalTrade(conn, tradeWriter);
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = (totalCount / numPerPage) + 1;
		}
		int pageNaviSize = 5;
		int pageNo = (((reqPage - 1) / pageNaviSize) * pageNaviSize) + 1;
		
		String pageNavi = "<ul class='pagination'>";
		
		if (pageNo != 1) {
			pageNavi += "<li class='page-item disabled'>";
			pageNavi += "<a class='page-link' href='/tradeHistory?reqPage=" + (pageNo - 1) + "&tradeWriter=" + tradeWriter + "'>";
			pageNavi += "&laquo;</a></li>";
		}
		
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/tradeHistory?reqPage=" + pageNo + "&tradeWriter=" + tradeWriter + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/tradeHistory?reqPage=" + pageNo + "&tradeWriter=" + tradeWriter + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/tradeHistory?reqPage=" + pageNo + "&tradeWriter=" + tradeWriter + "'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		
		TradePageData tpd = new TradePageData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return tpd;
	}

	public TradePageData selectTradeLoginList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 12;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		TradeDao dao = new TradeDao();
		ArrayList<Trade> list = dao.selectTradeLoginList(conn, memberId, start, end);
		
		// 페이지 네비게이션
		int totalCount = dao.selectTotalCount(conn);
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = (totalCount / numPerPage) + 1;
		}
		int pageNaviSize = 5;
		int pageNo = (((reqPage - 1) / pageNaviSize) * pageNaviSize) + 1;
		
		String pageNavi = "<ul class='pagination'>";
		
		if (pageNo != 1) {
			pageNavi += "<li class='page-item disabled'>";
			pageNavi += "<a class='page-link' href='/tradeLoginList?reqPage=" + (pageNo - 1) + "&memberId=" + memberId + "'>";
			pageNavi += "&laquo;</a></li>";
		}
		
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/tradeLoginList?reqPage=" + pageNo + "&memberId=" + memberId + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/tradeLoginList?reqPage=" + pageNo + "&memberId=" + memberId + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/tradeLoginList?reqPage=" + pageNo + "&memberId=" + memberId + "'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		
		TradePageData tpd = new TradePageData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return tpd;
	}

	public int changeStatus(int status, int tradeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new TradeDao().changeStatus(conn, status, tradeNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int transaction(String buyer, int tradeNo, String seller, int transactionPrice) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new TradeDao().transaction(conn, buyer, tradeNo, seller, transactionPrice);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int transactionStatus(int tradeNo, int tradeStatus) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new TradeDao().transactionStatus(conn, tradeNo, tradeStatus);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertReport(String reportWriter, String reportedMember, String reportContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new TradeDao().insertReport(conn, reportWriter, reportedMember, reportContent);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Trade> MainTradeList() {
		Connection conn = JDBCTemplate.getConnection();
		int start = 1;
		int end = 8;
		ArrayList<Trade> list = new TradeDao().mainTradeList(conn, start, end);
		JDBCTemplate.close(conn);
		return list;
	}

	public TradePageData selectSearch(int reqPage, String search) {
		Connection conn= JDBCTemplate.getConnection();
		int numPerPage = 12;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		TradeDao dao = new TradeDao();
		ArrayList<Trade> list = dao.selectSearch(conn, start, end, search);
		
		// 페이지 네비게이션
		int totalCount = dao.selectTotalCount(conn);
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = (totalCount / numPerPage) + 1;
		}
		int pageNaviSize = 5;
		int pageNo = (((reqPage - 1) / pageNaviSize) * pageNaviSize) + 1;
		
		String pageNavi = "<ul class='pagination'>";
		
		if (pageNo != 1) {
			pageNavi += "<li class='page-item disabled'>";
			pageNavi += "<a class='page-link' href='/searchWish?reqPage=" + (pageNo - 1) + "&search=" + search + "'>";
			pageNavi += "&laquo;</a></li>";
		}
		
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/searchWish?reqPage=" + pageNo + "&search=" + search + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/searchWish?reqPage=" + pageNo + "&search=" + search + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/searchWish?reqPage=" + pageNo + "&search=" + search + "'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";

		
		TradePageData tpd = new TradePageData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return tpd;
	}

}
