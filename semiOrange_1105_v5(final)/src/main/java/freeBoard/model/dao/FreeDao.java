package freeBoard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import freeBoard.model.vo.FreeBoard;
import freeBoard.model.vo.LikeIt;
import freeBoard.model.vo.Comment1;

public class FreeDao {

	// 자유게시판 등록
	public int insertFree(Connection conn, FreeBoard f) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO FREEBOARD VALUES(FREE_SEQ.NEXTVAL,?,?,?,0,TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'),?,?,?,0)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, f.getFreeTitle());
			pstmt.setString(2, f.getFreeContent());
			pstmt.setString(3, f.getFreeWriter());
			pstmt.setInt(4, f.getPriority());
			pstmt.setString(5, f.getFilename());
			pstmt.setString(6, f.getFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 페이징 + 게시판[댓글수표시]
	public ArrayList<FreeBoard> selectFreeList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FreeBoard> list = new ArrayList<FreeBoard>();
		String query = "SELECT FF.*,(select count(*) from comment1 where board_no=free_no and board_type=1) AS \"NC_COUNT\" FROM (SELECT ROWNUM AS RNUM, f.* FROM(SELECT * FROM freeboard where priority = 0 ORDER BY REG_DATE DESC)f)FF WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FreeBoard f = new FreeBoard();
				f.setFreeNo(rset.getInt("free_no"));
				f.setFreeTitle(rset.getString("free_title"));
				f.setFreeContent(rset.getString("free_content"));
				f.setFreeWriter(rset.getString("free_writer"));
				f.setReadCount(rset.getInt("read_count"));
				f.setRegDate(rset.getString("reg_date"));
				f.setPriority(rset.getInt("priority"));
				f.setFilename(rset.getString("filename"));
				f.setFilepath(rset.getString("filepath"));
				f.setNcCount(rset.getInt("nc_count"));
				list.add(f);
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

	// 전체 페이지수 계산
	public int selectTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*)as cnt from freeBoard ";
		try {
			pstmt = conn.prepareStatement(query);
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
	

	public int fixPageCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*)as cnt from freeboard where priority=1";
		try {
			pstmt = conn.prepareStatement(query);
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
	
	// 조회수
	public int updateReadCount(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update freeboard set read_count= read_count+1 where free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 파일 다운로드 , 게시판 상세보기
	public FreeBoard selectOneFree(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FreeBoard f = null;
		String query = "select * from freeBoard where free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				f = new FreeBoard();
				f.setFreeNo(rset.getInt("free_no"));
				f.setFreeTitle(rset.getString("free_title"));
				f.setFreeContent(rset.getString("free_content"));
				f.setFreeWriter(rset.getString("free_writer"));
				f.setReadCount(rset.getInt("read_count"));
				f.setRegDate(rset.getString("reg_date"));
				f.setPriority(rset.getInt("priority"));
				f.setFilename(rset.getString("filename"));
				f.setFilepath(rset.getString("filepath"));
				f.setNcCount(rset.getInt("nccount"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return f;
	}

	// 자유게시판 수정
	public int updateFree(Connection conn, FreeBoard f) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = " update freeBoard set free_title=?, free_content=?, filename=?, filepath=?, priority=? where free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, f.getFreeTitle());
			pstmt.setString(2, f.getFreeContent());
			pstmt.setString(3, f.getFilename());
			pstmt.setString(4, f.getFilepath());
			pstmt.setInt(5, f.getPriority());
			pstmt.setInt(6, f.getFreeNo());	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 자유게시판 삭제
	public int freeDelte(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from freeboard where free_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 라디오박스 검색 
	public ArrayList<FreeBoard> selectSearchFree(Connection conn, int start, int end, String optionsRadios, String freeSearch) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FreeBoard> list = new ArrayList<FreeBoard>();
		String query ="";
		if(optionsRadios.equals("option1")) {
			query = "select * from (select rownum as rnum, f.* from"
					+ "(select * from freeboard where free_content like ? order by free_no desc)f) "
					+ "where rnum between ? and ?";
		}else {
			query = "select * from (select rownum as rnum, f.* from"
					+ "(select * from freeboard where free_writer like ? order by free_no desc)f) "
					+ "where rnum between ? and ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+freeSearch+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FreeBoard f = new FreeBoard();
				f.setFreeNo(rset.getInt("free_no"));
				f.setFreeTitle(rset.getString("free_title"));
				f.setFreeContent(rset.getString("free_content"));
				f.setFreeWriter(rset.getString("free_writer"));
				f.setReadCount(rset.getInt("read_Count"));
				f.setRegDate(rset.getString("reg_date"));
				f.setFilename(rset.getString("filename"));
				f.setFilepath(rset.getString("filePath"));
				list.add(f);
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

	// 라디오박스 검색 
	public int selectTotalCount(Connection conn, String optionsRadios, String freeSearch) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query ="";
		if(optionsRadios.equals("option1")) {
			query = "select count(*) as cnt from freeboard where free_content like ?"; 
		}else {
			query = "select count(*) as cnt from freeboard where free_writer like ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+freeSearch+"%");
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

	// 댓글
	public int insertComment(Connection conn, Comment1 fc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO COMMENT1 VALUES(COMMENT_SEQ.NEXTVAL,?,?,TO_CHAR(SYSDATE, 'YYYY-MM-DD'),?,1)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fc.getMemberId());
			pstmt.setString(2, fc.getCommentContent());
			pstmt.setInt(3, fc.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 댓글
	public ArrayList<Comment1> selectCommentList(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Comment1> list = new ArrayList<Comment1>();
		String query = "select * from comment1 where board_no=? and board_type=1 order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Comment1 fc = new Comment1();
				fc.setCommentNo(rset.getInt("comment_no"));
				fc.setMemberId(rset.getString("member_id"));
				fc.setCommentContent(rset.getString("comment_content"));
				fc.setRegDate(rset.getString("reg_date"));
				fc.setBoardNo(rset.getInt("board_no"));
				fc.setBoardType(rset.getInt("board_type"));
				list.add(fc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	// 댓글수정
	public int updateComment(Connection conn, int commentNo, String commentContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update comment1 set comment_content=? where comment_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, commentContent);
			pstmt.setInt(2, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 댓글삭제
	public int deleteComment(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from comment1 where comment_no=?";
		try {			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 고정페이지(관리자)
	public ArrayList<FreeBoard> priority(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FreeBoard> fixlist = new ArrayList<FreeBoard>();
		String query = "select * from freeBoard where priority=1 order by 1 desc";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FreeBoard f = new FreeBoard();
				f.setFreeNo(rset.getInt("free_no"));
				f.setFreeTitle(rset.getString("free_title"));
				f.setFreeWriter(rset.getString("free_writer"));
				f.setRegDate(rset.getString("reg_date"));
				f.setReadCount(rset.getInt("read_count"));
				f.setPriority(rset.getInt("priority"));
				fixlist.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		return fixlist;
	}

	//좋아요 업데이트
	/*public int update_Like(Connection conn, Comment1 fc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update comment1 set like_it=like_it+1 where comment_no=?"; 
		try {			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fc.getCommentNo());
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//좋아요 개수 찾기
	public int select_Like(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select like_it from comment1 where comment_no=?";			
		try {			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("like_it");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return result;
	} 

	public int update_Like(Connection conn, int freeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update freeboard set like_it=like_it+1 where free_no=?"; 
		try {			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}  */

	// 게시판 좋아요
	/*public int updateLike(Connection conn, FreeBoard fb) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select * from freeBoard where free_no=? free_Writer=?";			
		try {			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fb.getFreeNo());
			pstmt.setString(2, fb.getFreeWriter());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("like_Count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return result;
	}

	public int selectLike(Connection conn, FreeBoard fb) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update freeboard set like_Count=like_Count+1 where free_no=?"; 
		try {			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fb.getFreeNo());
			result = pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}*/
	
}