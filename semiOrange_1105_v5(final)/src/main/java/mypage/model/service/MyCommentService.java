package mypage.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import freeBoard.model.vo.Comment1;
import mypage.model.dao.MyCommentDao;
import mypage.model.dao.SigunguDao;
import mypage.model.vo.CommentPageData;
import mypage.model.vo.Sigungu;

public class MyCommentService {

	public CommentPageData selectCommentList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		MyCommentDao dao = new MyCommentDao();
		ArrayList<Comment1> list = dao.selectMyCommentList(conn, memberId, start,end);
		
		int totalCount = dao.selectMyTotalCommentCount(conn,memberId);
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
			pageNavi += "<a class=\"page-link\" href='/myCommunityComment?reqPage="+(pageNo-1)+"&memberId="+memberId+"'>&laquo;</a></li>";
		}
		//페이지 숫자 표현
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class=\"page-item active\">";
				pageNavi += "<a class='page-link' href='/myCommunityComment?reqPage="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/myCommunityComment?reqPage="+pageNo+"&memberId="+memberId+"'>";
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
			pageNavi += "<a class='page-link' href='/myCommunityComment?reqPage="+pageNo+"&memberId="+memberId+"'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		CommentPageData cpd = new CommentPageData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return cpd;
	}

	public int searchTradeComment(int tradeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int commentCount = new MyCommentDao().searchTradeComment(conn, tradeNo);
		JDBCTemplate.close(conn);
		return commentCount;
	}

}
