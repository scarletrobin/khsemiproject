package freeBoard.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import freeBoard.model.dao.FreeDao;
import freeBoard.model.vo.FreeBoard;
import freeBoard.model.vo.Comment1;
import freeBoard.model.vo.FreePageData;
import freeBoard.model.vo.FreePageData2;
import freeBoard.model.vo.LikeIt;
import freeBoard.model.vo.freeViewData;

public class FreeService {
	// 자유게시판 등록
	public int insertFree(FreeBoard f) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().insertFree(conn, f);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	// 페이지 네비
	public FreePageData selectFreeList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();

		// 관리자_고정게시물
		ArrayList<FreeBoard> fixlist = new FreeDao().priority(conn);				
		
		// 한 페이지당 게시물 수
		int numPerPage = 10;	// 1-10 / 11-20 / 21-30 
		int end = reqPage * numPerPage;            	
		int start = end - numPerPage + 1;		   									
		
		FreeDao dao = new FreeDao();
		ArrayList<FreeBoard> list = dao.selectFreeList(conn, start, end);

		// 전체 게시물 수 조회 -> 전체 페이지 수 계산
		int totalCount = dao.selectTotalCount(conn);  
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}

		// 페이지 네비 길이(1~5,6~10...)
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1; // 네비 시작번호
		String pageNavi = "<ul class='pagination pagination-lg'>";
		
		// 이전버튼
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/freeBoard?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "&lt;</a></li>";
		}

		// 페이지 숫자
		for (int i = 0; i < pageNaviSize; i++) {  // 0,1,2,3,4
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/freeBoard?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/freeBoard?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {  // 최종 페이지보다 네비게이션 시작번호가 더 클 경우, break
				break;				   // ex.totalPage는 5
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/freeBoard?reqPage=" + pageNo + "'>";  // 페이지가 이미 1이 증가된 상태로 들어오기때문에 pageNo로 작성
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		// 관리자_고정게시물 count
		int fixPage = new FreeDao().fixPageCount(conn);
		
		fixlist.addAll(list);  // 고정페이지와 일반페이지 합침
		
		FreePageData fpd = new FreePageData(fixlist, pageNavi, start, totalCount, fixPage);
		JDBCTemplate.close(conn);
		return fpd;
	}

	// 조회수, 파일 다운로드 , 게시판 상세보기, 댓글
	public freeViewData selectOneFree(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		FreeDao dao = new FreeDao();
		int result = dao.updateReadCount(conn,freeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}	
		FreeBoard f = dao.selectOneFree(conn,freeNo);  
		ArrayList<Comment1> list = dao.selectCommentList(conn,freeNo);
		freeViewData fvd = new freeViewData(list, f);  
		JDBCTemplate.close(conn);
		return fvd;
	}

	// 자유게시판 수정
	public int updateFree(FreeBoard f) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().updateFree(conn,f);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	// 파일 다운로드
	public FreeBoard getFree(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		FreeBoard f = new FreeDao().selectOneFree(conn, freeNo);
		JDBCTemplate.close(conn);
		return f;
	}

	// 파일 삭제
	public int freeDelte(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().freeDelte(conn, freeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	// 라디오박스 검색
	public FreePageData2 searchFree(int reqPage, String optionsRadios, String freeSearch) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		FreeDao dao = new FreeDao();
		ArrayList<FreeBoard> list = dao.selectSearchFree(conn, start, end, optionsRadios, freeSearch);
		
		int totalCount = dao.selectTotalCount(conn, optionsRadios, freeSearch);  // 라디오박스 1.
		
		// 전체 페이지수 계산
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
		
		// 페이지 네비의 길이(네비게이션 숫자 최대 개수)
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination pagination-lg'>";
		
		// 이전버튼
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/searchFree?reqPage=" + (pageNo - 1) + "&optionsRadios=" + optionsRadios + "&freeSearch="+freeSearch+"'>";
			pageNavi += "&lt;</a></li>";
		}
		// 페이지숫자
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/searchFree?reqPage=" + pageNo + "&optionsRadios=" + optionsRadios + "&freeSearch="+freeSearch+"'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/searchFree?reqPage=" + pageNo + "&optionsRadios=" + optionsRadios + "&freeSearch="+freeSearch+ "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/searchFree?reqPage=" + pageNo + "&optionsRadios=" + optionsRadios + "&freeSearch="+freeSearch+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";

		// 게시물목록(ArrayList), 페이지네비(String), start(번호 표시용)
		FreePageData2 fpd = new FreePageData2(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return fpd;
	}

	// 댓글
	public int insertComment(Comment1 fc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().insertComment(conn, fc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	// 댓글수정
	public int updateComment(String commentContent, int commentNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().updateComment(conn,commentNo,commentContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	// 댓글삭제
	public int deleteComment(int commentNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().deleteComment(conn,commentNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	/*public int updateLike(FreeBoard fb) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().updateLike(conn,fb);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int selectLike(FreeBoard fb) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().selectLike(conn,fb);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}*/

	//좋아요 업데이트
	/*public int update_Like(Comment1 fc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().update_Like(conn,fc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//좋아요 개수 찾기
	public int select_Like(Comment1 fc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().select_Like(conn,fc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public int select_LikeFree(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().select_Like(conn,freeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public void update_Like(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FreeDao().update_Like(conn,freeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	} */


}














