package qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.net.httpserver.Authenticator.Result;

import common.JDBCTemplate;
import qna.model.vo.Qna;
import qna.model.vo.QnaComment;

public class QnaDao {
	public ArrayList<Qna> selectQnaList(Connection conn, int start, int end){
		PreparedStatement ptst = null;
		ResultSet rset = null;
		ArrayList<Qna> list = new ArrayList<Qna>();
		//String query = "SELECT QC.*,(SELECT COUNT(*) FROM COMMENT1 WHERE BOARD_NO=QC.QNA_NO AND BOARD_TYPE=3) AS \"qc_count\" FROM (SELECT ROWNUM AS RNUM,Q.* FROM (SELECT * FROM QNA ORDER BY QNA_NO DESC)Q)QC WHERE RNUM BETWEEN ? AND ?";
		String query = "SELECT QC.*,(SELECT COUNT(*) FROM COMMENT1 WHERE BOARD_NO=QC.QNA_NO AND BOARD_TYPE=3) AS \"QC_COUNT\" FROM (SELECT ROWNUM AS RNUM,Q.* FROM (SELECT QNA.*,MEMBER.member_level FROM QNA JOIN MEMBER ON QNA.MEMBER_ID=MEMBER.MEMBER_ID ORDER BY QNA_NO DESC)Q)QC WHERE RNUM BETWEEN ? AND ?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, start);
			ptst.setInt(2, end);
			rset = ptst.executeQuery();
			while(rset.next()) {
				Qna q = new Qna();
				q.setQnaNo(rset.getInt("qna_no"));
				q.setQnaTitle(rset.getString("qna_title"));
				q.setQnaContent(rset.getString("qna_content"));
				q.setMemberId(rset.getString("member_id"));
				q.setRegDate(rset.getString("reg_date"));
				q.setFileName(rset.getString("filename"));
				q.setFilePath(rset.getString("filepath"));
				q.setQnaCount(rset.getInt("qc_count"));
				q.setMemberLevel(rset.getInt("member_level"));
				list.add(q);
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
		String query = "select count(*) as cnt from qna";
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
	public ArrayList<Qna> selectSearchQna(Connection conn, String type, String keyword, int start, int end) {
		PreparedStatement ptst = null;
		ResultSet rset = null;
		ArrayList<Qna> list = new ArrayList<Qna>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM,Q.* FROM(SELECT * FROM QNA WHERE MEMBER_ID LIKE ? ORDER BY QNA_NO DESC)Q) WHERE rnum BETWEEN ? AND ?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, "%"+keyword+"%");
			ptst.setInt(2, start);
			ptst.setInt(3, end);
			rset = ptst.executeQuery();
			while(rset.next()) {
				Qna q = new Qna();
				q.setQnaNo(rset.getInt("qna_no"));
				q.setQnaTitle(rset.getString("qna_title"));
				q.setQnaContent(rset.getString("qna_content"));
				q.setMemberId(rset.getString("member_id"));
				q.setRegDate(rset.getString("reg_date"));
				q.setFileName(rset.getString("filename"));
				q.setFilePath(rset.getString("filepath"));
				list.add(q);
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
	public int selectTotalCount(Connection conn, String type, String keyword) {
		PreparedStatement ptst = null;
		ResultSet rset = null;
		int result = 0;
		String query ="select count(*) as cnt from qna where member_id like ?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, "%"+keyword+"%");
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
	public int insertQna(Connection conn, Qna q) {
		PreparedStatement ptst = null;
		int result = 0;
		String query = "insert into qna values(qna_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, q.getMemberId());
			ptst.setString(2, q.getQnaTitle());
			ptst.setString(3, q.getQnaContent());
			ptst.setString(4, q.getFileName());
			ptst.setString(5, q.getFilePath());
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ptst);
		}
		return result;
	}
	public Qna selectOneQna(Connection conn, int qnaNo) {
		PreparedStatement ptst = null;
		ResultSet rset = null;
		Qna q = null;
		String query = "select * from qna where qna_no=?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, qnaNo);
			rset = ptst.executeQuery();
			if(rset.next()) {
				q = new Qna();
				q.setQnaNo(rset.getInt("qna_no"));
				q.setQnaTitle(rset.getString("qna_title"));
				q.setQnaContent(rset.getString("qna_content"));
				q.setMemberId(rset.getString("member_id"));
				q.setRegDate(rset.getString("reg_date"));
				q.setFileName(rset.getString("filename"));
				q.setFilePath(rset.getString("filepath"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(ptst);
		}
		return q;
	}
	public ArrayList<QnaComment> selectCommentList(Connection conn, int qnaNo) {
		PreparedStatement ptst = null;
		ResultSet rset = null;
		ArrayList<QnaComment> list = new ArrayList<QnaComment>();
		String query = "select * from comment1 where board_no=? and board_type=3 order by 1";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, qnaNo);
			rset = ptst.executeQuery();
			while(rset.next()) {
				QnaComment qc = new QnaComment();
				qc.setCommentNo(rset.getInt("comment_no"));
				qc.setMemberId(rset.getString("member_id"));
				qc.setCommentContent(rset.getString("comment_content"));
				qc.setRegDate(rset.getString("reg_date"));
				qc.setBoardNo(rset.getInt("board_no"));
				qc.setBoardType(rset.getInt("board_type"));
				list.add(qc);
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
	public int deleteQna(Connection conn, int qnaNo) {
		PreparedStatement ptst = null;
		int result = 0;
		String query = "delete from qna where qna_no=?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, qnaNo);
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ptst);
		}
		return result;
	}
	public int updateQna(Connection conn,Qna q) {
		PreparedStatement ptst = null;
		int result = 0;
		String query = "update qna set qna_title=?,qna_content=?, filename=?,filepath=? where qna_no=?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, q.getQnaTitle());
			ptst.setString(2, q.getQnaContent());
			ptst.setString(3, q.getFileName());
			ptst.setString(4, q.getFilePath());
			ptst.setInt(5, q.getQnaNo());
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ptst);
		}
		return result;
	}
	public int insertComment(Connection conn, QnaComment qc) {
		PreparedStatement ptst = null;
		int result = 0;
		String query = "insert into comment1 values(comment_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?,3)";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setString(1, qc.getMemberId());
			ptst.setString(2, qc.getCommentContent());
			ptst.setInt(3, qc.getBoardNo());
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ptst);
		}
		return result;
	}
	public int deleteComment(Connection conn, int qcNo) {
		PreparedStatement ptst = null;
		int result = 0;
		String query = "delete from comment1 where comment_no=?";
		try {
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, qcNo);
			result = ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ptst);
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
