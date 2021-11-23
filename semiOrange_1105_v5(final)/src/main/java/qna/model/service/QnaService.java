package qna.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import qna.model.dao.QnaDao;
import qna.model.vo.Qna;
import qna.model.vo.QnaComment;
import qna.model.vo.QnaPageData;
import qna.model.vo.QnaViewData;

public class QnaService {
	
	public int insertQna(Qna q) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new QnaDao().insertQna(conn,q);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public QnaPageData selectQnaList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end=reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		QnaDao dao = new QnaDao();
		ArrayList<Qna> list = dao.selectQnaList(conn,start,end);
		
		int totalCount = dao.selectTotalCount(conn);
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
			pageNavi += "<a class=\"page-link\" href='/qnaList?reqPage="+(pageNo-1)+"'>&laquo;</a></li>";
		}
		//페이지 숫자 표현
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class=\"page-item active\">";
				pageNavi += "<a class='page-link' href='/qnaList?reqPage="+pageNo+"'>"+pageNo+"</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/qnaList?reqPage="+pageNo+"'>";
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
			pageNavi += "<a class='page-link' href='/qnaList?reqPage="+pageNo+"'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		QnaPageData qpd = new QnaPageData(list,pageNavi,start);
		return qpd;
	}

	public QnaPageData searchQna(int reqPage, String type, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		QnaDao dao = new QnaDao();
		ArrayList<Qna> list = dao.selectSearchQna(conn,type,keyword,start,end);
		
		int totalCount = dao.selectTotalCount(conn,type,keyword);
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
			pageNavi += "<a class=\"page-link\" href='/qnaList?reqPage="+(pageNo-1)+"'>&laquo;</a></li>";
		}
		//페이지 숫자 표현
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class=\"page-item active\">";
				pageNavi += "<a class='page-link' href='/qnaList?reqPage="+pageNo+"'>"+pageNo+"</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/qnaList?reqPage="+pageNo+"'>";
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
			pageNavi += "<a class='page-link' href='/qnaList?reqPage="+pageNo+"'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		QnaPageData qpd = new QnaPageData(list,pageNavi,start);
		return qpd;
	}
	public QnaViewData selectOneQna(int qnaNo) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		Qna q = dao.selectOneQna(conn,qnaNo);
		ArrayList<QnaComment> list = dao.selectCommentList(conn,qnaNo);
		QnaViewData qd = new QnaViewData(list, q);
		JDBCTemplate.close(conn);
		return qd;
	}

	public Qna getQna(int qnaNo) {
		Connection conn = JDBCTemplate.getConnection();
		Qna q = new QnaDao().selectOneQna(conn, qnaNo);
		JDBCTemplate.close(conn);
		return q;
	}

	public int deleteQna(int qnaNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new QnaDao().deleteQna(conn,qnaNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateQna(Qna q) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new QnaDao().updateQna(conn,q);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertComment(QnaComment qc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new QnaDao().insertComment(conn,qc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteComment(int qcNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new QnaDao().deleteComment(conn,qcNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.close(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateComment(int commentNo, String commentContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new QnaDao().updateComment(conn,commentNo,commentContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else{
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
