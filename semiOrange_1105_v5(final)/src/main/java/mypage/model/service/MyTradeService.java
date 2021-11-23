package mypage.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypage.model.dao.MyTradeDao;
import mypage.model.vo.Transaction;
import mypage.model.vo.TransactionPageData;
import trade.model.vo.Trade;
import trade.model.vo.TradePageData;

public class MyTradeService {

	public TradePageData selectTradeList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		mypage.model.dao.MyTradeDao dao = new mypage.model.dao.MyTradeDao();
		ArrayList<Trade> list = dao.selectMyTradeList(conn, memberId, start,end);
		
		int totalCount = dao.selectTotalTradeCount(conn,memberId);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		if(pageNo != 1) {
			pageNavi += "<li class=\"page-item\">";
			pageNavi += "<a class=\"page-link\" href='/myTrade?reqPage="+(pageNo-1)+"&memberId="+memberId+"'>&laquo;</a></li>";
		}
		//페이지 숫자 표현
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class=\"page-item active\">";
				pageNavi += "<a class='page-link' href='/myTrade?reqPage="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/myTrade?reqPage="+pageNo+"&memberId="+memberId+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/myTrade?reqPage="+pageNo+"&memberId="+memberId+"'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		TradePageData tpd = new TradePageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		return tpd;
	}
	
	//구매내역
	public TransactionPageData tradeBuyList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		mypage.model.dao.MyTradeDao dao = new mypage.model.dao.MyTradeDao();
		ArrayList<Transaction> list = dao.selectMyTradeBuyList(conn, memberId, start,end);
		
		int totalCount = dao.selectTotalTradeBuyCount(conn,memberId);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		if(pageNo != 1) {
			pageNavi += "<li class=\"page-item\">";
			pageNavi += "<a class=\"page-link\" href='/myTradeBuy?reqPage="+(pageNo-1)+"&memberId="+memberId+"'>&laquo;</a></li>";
		}
		//페이지 숫자 표현
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class=\"page-item active\">";
				pageNavi += "<a class='page-link' href='/myTradeBuy?reqPage="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/myTradeBuy?reqPage="+pageNo+"&memberId="+memberId+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/myTradeBuy?reqPage="+pageNo+"&memberId="+memberId+"'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		TransactionPageData tpd = new TransactionPageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		return tpd;
	}

	public TradePageData selectTradePostList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		mypage.model.dao.MyTradeDao dao = new mypage.model.dao.MyTradeDao();
		ArrayList<Trade> list = dao.selectMyTradePostList(conn, memberId, start,end);
		
		int totalCount = dao.selectTotalTradePostCount(conn,memberId);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		if(pageNo != 1) {
			pageNavi += "<li class=\"page-item\">";
			pageNavi += "<a class=\"page-link\" href='/myPosts?reqPage="+(pageNo-1)+"&memberId="+memberId+"'>&laquo;</a></li>";
		}
		//페이지 숫자 표현
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class=\"page-item active\">";
				pageNavi += "<a class='page-link' href='/myPosts?reqPage="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/myPosts?reqPage="+pageNo+"&memberId="+memberId+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/myPosts?reqPage="+pageNo+"&memberId="+memberId+"'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		TradePageData tpd = new TradePageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		return tpd;
	}

	public int changeTradeStatus(int tradeNo, int tradeStatus) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		mypage.model.dao.MyTradeDao dao = new mypage.model.dao.MyTradeDao();
		result = dao.changeTradeStatus(conn, tradeNo, tradeStatus);
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int searchTransactionNo(int tradeNo) {
		Connection conn= JDBCTemplate.getConnection();
		int result = 0;
		result = new MyTradeDao().selectTransactionNo(conn, tradeNo);
		JDBCTemplate.close(conn);
		return result;
	}

}
