package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;

public class NoticeDao {
	public ArrayList<Notice> selectNoticeList(Connection conn,int start,int end){
		PreparedStatement ptst = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		String query = "select nn.*,(select count(*) from comment1 where board_no=nn.notice_no and board_type = 4) as \"nc_count\" from (select rownum as rnum,n.* from (select * from notice order by notice_no desc)n)nn where rnum between ? and ?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, start);
			ptst.setInt(2, end);
			rset = ptst.executeQuery();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setMemberId(rset.getString("member_id"));
				n.setReadCount(rset.getInt("readcount"));
				n.setRegDate(rset.getString("regdate"));
				n.setFilename(rset.getString("filename"));
				n.setFilepath(rset.getString("filepath"));
				n.setNcCount(rset.getInt("nc_count"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(ptst);
		}
		return list;
	}

	public int selectTotalCount(Connection conn) {
		PreparedStatement ptst = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from notice";
		try {
			ptst = conn.prepareStatement(query);
			rset = ptst.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(ptst);
		}
		return result;
	}

	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement ptst = null;
		int result = 0;
		String query = "insert into notice values(notice_seq.nextval,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, n.getMemberId());
			ptst.setString(2, n.getNoticeTitle());
			ptst.setString(3, n.getNoticeContent());
			ptst.setString(4, n.getFilename());
			ptst.setString(5, n.getFilepath());
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ptst);
		}
		return result;
	}

	public int updateReadCount(Connection conn, int noticeNo) {
		PreparedStatement ptst = null;
		int result = 0;
		String query = "update notice set readcount = readcount+1 where notice_no=?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, noticeNo);
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ptst);
		}
		return result;
	}

	public Notice selectOneNotice(Connection conn, int noticeNo) {
		PreparedStatement ptst = null;
		ResultSet rset = null;
		Notice n = null;
		String query = "select * from notice where notice_no=?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, noticeNo);
			rset = ptst.executeQuery();
			if(rset.next()) {
				n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setMemberId(rset.getString("member_id"));
				n.setReadCount(rset.getInt("readcount"));
				n.setRegDate(rset.getString("regdate"));
				n.setFilename(rset.getString("filename"));
				n.setFilepath(rset.getString("filepath"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(ptst);
		}
		return n;
	}

	public ArrayList<NoticeComment> selectCommentList(Connection conn, int noticeNo) {
		PreparedStatement ptst = null;
		ResultSet rset = null;
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		String query = "select * from comment1 where board_no=? and board_type=4 order by 1";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, noticeNo);
			rset = ptst.executeQuery();
			while(rset.next()) {
				NoticeComment nc = new NoticeComment();
				nc.setCommentNo(rset.getInt("comment_no"));
				nc.setMemberId(rset.getString("member_id"));
				nc.setCommentContent(rset.getString("comment_content"));
				nc.setRegDate(rset.getString("reg_date"));
				nc.setBoardNo(rset.getInt("board_no"));
				nc.setBoardType(4);
				list.add(nc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(ptst);
		}
		return list;
	}

	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement ptst = null;
		int result = 0;
		String query = "delete from notice where notice_no=?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, noticeNo);
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ptst);
		}
		return result;
	}

	public int updateNotice(Connection conn, Notice n) {
		PreparedStatement ptst = null;
		int result = 0;
		String query = "update notice set notice_title=?,notice_content=?,filename=?,filepath=? where notice_no=?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, n.getNoticeTitle());
			ptst.setString(2, n.getNoticeContent());
			ptst.setString(3, n.getFilename());
			ptst.setString(4, n.getFilepath());
			ptst.setInt(5, n.getNoticeNo());
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ptst);
		}
		return result;
	}
	public int insertComment(Connection conn, NoticeComment nc) {
		PreparedStatement ptst = null;
		int result = 0;
		String query = "insert into comment1 values(comment_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?,4)";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, nc.getMemberId());
			ptst.setString(2, nc.getCommentContent());
			ptst.setInt(3, nc.getBoardNo());
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ptst);
		}
		return result;
		
	}

	public int deleteComment(Connection conn, int ncNo) {
		PreparedStatement ptst = null;
		int result = 0;
		String query ="delete from comment1 where comment_no=?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, ncNo);
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public int updateComment(Connection conn, int commentNo, String commentContent) {
		PreparedStatement ptst = null;
		int result = 0;
		String query = "update comment1 set comment_content=? where comment_no=?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, commentContent);
			ptst.setInt(2, commentNo);
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ptst);
		}
		return result;
	}
}
