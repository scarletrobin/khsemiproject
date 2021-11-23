package mypage.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import freeBoard.model.vo.FreeBoard;
import freeBoard.model.vo.FreePageData2;
import mypage.model.dao.MyFreeboardDao;


public class MyFreeboardService {

	public FreePageData2 selectFreeboardList(int reqPage, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		MyFreeboardDao dao = new MyFreeboardDao();
		ArrayList<FreeBoard> list = dao.selectMyFreeboardList(conn, memberId, start,end);
		
		int totalCount = dao.selectMyTotalFreeboardCount(conn,memberId);
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
			pageNavi += "<a class=\"page-link\" href='/myCommunity?reqPage="+(pageNo-1)+"&memberId="+memberId+"'>&laquo;</a></li>";
		}
		//페이지 숫자 표현
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class=\"page-item active\">";
				pageNavi += "<a class='page-link' href='/myCommunity?reqPage="+pageNo+"&memberId="+memberId+"'>"+pageNo+"</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/myCommunity?reqPage="+pageNo+"&memberId="+memberId+"'>";
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
			pageNavi += "<a class='page-link' href='/myCommunity?reqPage="+pageNo+"&memberId="+memberId+"'>";
			pageNavi += "&raquo;</a></li>";
		}
		pageNavi += "</ul>";
		FreePageData2 fpd = new FreePageData2(list,pageNavi,start);
		JDBCTemplate.close(conn);
		return fpd;
	}

	

}
