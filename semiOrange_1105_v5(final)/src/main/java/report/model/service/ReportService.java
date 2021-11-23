package report.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import report.model.dao.ReportDao;
import report.model.vo.AllWrite;
import report.model.vo.ChartData;
import report.model.vo.AdminCount;
import report.model.vo.AdminMember;
import report.model.vo.MemberPageData;
import report.model.vo.Report;
import report.model.vo.ReportPageData;

public class ReportService {


	public int insertReport(Report r) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ReportDao().insertMember(conn,r);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public MemberPageData selectAllMember(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage +1;
		ArrayList<AdminMember> list = new ReportDao().selectAllMember(conn,start,end);
		int totalCount = new ReportDao().selectTotalCount(conn);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize +1;
		String pageNavi = "<ul class ='pagination pagination-lg'>";
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/adminPage?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>"; 
		}
		
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class ='page-item active'>";
				pageNavi += "<a class='page-link' href='/adminPage?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class ='page-item'>";
				pageNavi += "<a class='page-link' href='/adminPage?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		
		if(pageNo <= totalPage) {
			pageNavi += "<li class ='page-item'>";
			pageNavi += "<a class='page-link' href='/adminPage?reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
			
		}
		pageNavi +="</ul>";
		MemberPageData mpd = new MemberPageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		
		return mpd;
		
	}

	public ArrayList<Report> selectAllReport() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Report> list = new ReportDao().selectAllReport(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public AdminMember selectOneMember(int memberNo) {
		Connection conn=JDBCTemplate.getConnection();
		AdminMember m = new ReportDao().selectOneMember(conn,memberNo);
		JDBCTemplate.close(conn);
		return m;
	}

	public boolean chkChangeLevel(String num, String level) {
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(num,"/");
		StringTokenizer sT2 = new StringTokenizer(level,"/");
		ReportDao dao = new ReportDao();
		boolean result = true; 
		while(sT1.hasMoreTokens()) { 
			int memberNo =Integer.parseInt(sT1.nextToken());
			int memberLevel =Integer.parseInt(sT2.nextToken());
			int result1 = dao.changeLevel(conn,memberNo,memberLevel);
			if(result1 == 0) { 
				result=false; 
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ReportPageData selectAllReport(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage +1;
		ArrayList<Report> list = new ReportDao().selectAllReport(conn,start,end);
		int totalCount = new ReportDao().selectTotalReportCount(conn);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize +1;
		String pageNavi = "<ul class ='pagination pagination-lg'>";
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/reportListMore?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>"; 
		}
		
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class ='page-item active'>";
				pageNavi += "<a class='page-link' href='/reportListMore?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class ='page-item'>";
				pageNavi += "<a class='page-link' href='/reportListMore?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li class ='page-item'>";
			pageNavi += "<a class='page-link' href='/reportListMore?reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
			
		}
		pageNavi +="</ul>";
		ReportPageData rpd = new ReportPageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		
		return rpd;
	}

	public ArrayList<Report> selectCntList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Report> list = new ReportDao().selectCntList(conn);
		JDBCTemplate.close(conn);
		return list;
	}


	public boolean chkChangeResult(String num) {
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(num,"/");
		ReportDao dao = new ReportDao();
		boolean result = true;
		while(sT1.hasMoreTokens()) { 
			int reportNo =Integer.parseInt(sT1.nextToken());
			int result1 = dao.chkChangeResult(conn,reportNo);
			if(result1 == 0) {
				result=false; 
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}


	public ArrayList<AllWrite> selectFreeNotice(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<AllWrite> list = new ReportDao().selectFreeNotice(conn,memberId);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<AllWrite> selectTradeNotice(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<AllWrite> list = new ReportDao().selectTradeNotice(conn,memberId);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<AllWrite> selectCommentList(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<AllWrite> list = new ReportDao().selectCommentList(conn,memberId);
		JDBCTemplate.close(conn);
		return list;
	}

	public boolean chkDeleteReport(String num) {
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(num,"/");
		ReportDao dao = new ReportDao();
		boolean result = true; 
		while(sT1.hasMoreTokens()) {
			int reportNo =Integer.parseInt(sT1.nextToken());
			int result1 = dao.chkDeleteReport(conn,reportNo);
			if(result1 == 0) { 
				result=false; 
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public MemberPageData selectAllMember2(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage +1;
		ArrayList<AdminMember> list = new ReportDao().selectAllMember2(conn,start,end);
		int totalCount = new ReportDao().selectTotalCount(conn);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize +1;
		String pageNavi = "<ul class ='pagination pagination-lg'>";
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/adminLevelPage?reqPage="+(pageNo-1)+"&keyword=등급순'>";
			pageNavi += "&lt;</a></li>"; 
		}
		
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class ='page-item active'>";
				pageNavi += "<a class='page-link' href='/adminLevelPage?reqPage="+pageNo+"&keyword=등급순'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class ='page-item'>";
				pageNavi += "<a class='page-link' href='/adminLevelPage?reqPage="+pageNo+"&keyword=등급순'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		
		if(pageNo <= totalPage) {
			pageNavi += "<li class ='page-item'>";
			pageNavi += "<a class='page-link' href='/adminLevelPage?reqPage="+pageNo+"&keyword=등급순'>";
			pageNavi += "&gt;</a></li>";
			
		}
		pageNavi +="</ul>";
		MemberPageData mpd = new MemberPageData(list,pageNavi,start);
		JDBCTemplate.close(conn);
		
		return mpd;
		
	}

	public ArrayList<AllWrite> selectBoardTop() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<AllWrite> list = new ReportDao().selectBoardTop(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<AllWrite> selectFreeTop() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<AllWrite> list = new ReportDao().selectFreeTop(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<AllWrite> selectQna() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<AllWrite> list = new ReportDao().selectQna(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	
	public ArrayList<ChartData> chartData() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ChartData> list = new ReportDao().chartData(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public AdminCount adminCount() {
		Connection conn = JDBCTemplate.getConnection();
		int alljoin =new ReportDao().allJoin(conn);
		int newjoin =new ReportDao().newJoin(conn);
		int alltrade =new ReportDao().allTrade(conn);
		int newtrade =new ReportDao().newTrade(conn);
		int allfree =new ReportDao().allFree(conn);
		int newfree =new ReportDao().newFree(conn);
		AdminCount ac = new AdminCount();
		ac.setAlljoin(alljoin);
		ac.setNewjoin(newjoin);
		ac.setAlltrade(alltrade);
		ac.setNewtrade(newtrade);
		ac.setAllfree(allfree);
		ac.setNewfree(newfree);
		JDBCTemplate.close(conn);
		return ac;
	}

	public boolean chkDeleteMember(String num) {
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(num,"/");
		ReportDao dao = new ReportDao();
		boolean result = true; 
		while(sT1.hasMoreTokens()) {
			int memberNo =Integer.parseInt(sT1.nextToken());
			int result1 = dao.chkDeleteMember(conn,memberNo);
			if(result1 == 0) { 
				result=false; 
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<ChartData> chartData(String time1, String time2) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ChartData> list = new ReportDao().chartData(conn,time1,time2);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<AllWrite> selectReviewList(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<AllWrite> list = new ReportDao().selectReviewList(conn,memberId);
		JDBCTemplate.close(conn);
		return list;
	}


}
