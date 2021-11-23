package mypage.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypage.model.dao.MyQnaDao;
import qna.model.vo.Qna;
import qna.model.vo.QnaPageData;

public class MyQnaService {
	public QnaPageData selectQnaList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		MyQnaDao dao = new MyQnaDao();
		ArrayList<Qna> list = dao.selectSearchQna(conn, memberId, start,end);
		
		int totalCount = dao.selectTotalCount(conn,memberId);
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
			pageNavi += "<a class=\"page-link\" href='/myQna?reqPage="+(pageNo-1)+"&memberId="+memberId+"'>&laquo;</a></li>";
		}
		//페이지 숫자 표현
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class=\"page-item active\">";
				pageNavi += "<a class='page-link' href='/myQna?reqPage="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/myQna?reqPage="+pageNo+"&memberId="+memberId+"'>";
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
			pageNavi += "<a class='page-link' href='/myQna?reqPage="+pageNo+"&memberId="+memberId+"'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		QnaPageData qpd = new QnaPageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		return qpd;
	}

	public QnaPageData selectAnsweredQnaList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		MyQnaDao dao = new MyQnaDao();
		ArrayList<Qna> list = dao.selectAnsweredQna(conn, memberId, start,end);
		
		int totalCount = dao.selectTotalAnsweredCount(conn,memberId);
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
			pageNavi += "<a class=\"page-link\" href='/myQnaAnswered?reqPage="+(pageNo-1)+"&memberId="+memberId+"'>&laquo;</a></li>";
		}
		//페이지 숫자 표현
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class=\"page-item active\">";
				pageNavi += "<a class='page-link' href='/myQnaAnswered?reqPage="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/myQnaAnswered?reqPage="+pageNo+"&memberId="+memberId+"'>";
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
			pageNavi += "<a class='page-link' href='/myQnaAnswered?reqPage="+pageNo+"&memberId="+memberId+"'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		QnaPageData qpd = new QnaPageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		return qpd;
	}
}
