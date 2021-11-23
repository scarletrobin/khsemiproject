package post.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import post.model.vo.Post;

public class PostDao {

	//쪽지 보내기
	public int insertPost(Connection conn, Post p) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into post values(post_seq.nextval, ?, ?, ?, ?, to_char(sysdate, 'yyyy-mm-dd hh:mm:ss'), 'N', ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getPostFrom());
			pstmt.setString(2, p.getPostTo());
			pstmt.setString(3, p.getPostTitle());
			pstmt.setString(4, p.getPostContent());
			pstmt.setInt(5, p.getTradeNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//쪽지 목록 확인
	public ArrayList<Post> selectPostList(Connection conn, int start, int end, String memberId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Post> list = new ArrayList<Post>();
		
		//String query = "select * from (select rownum as rnum, n.* from (select p.* from post p join member on post_from = member_id where member_id='test6' order by post_no desc ) n ) where rnum BETWEEN ? and ?";
		String query = "select * from (select rownum as rnum, n.* from (select p.* from post p where post_to = ? order by post_no desc ) n ) where rnum BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Post p = new Post();
				p.setPostNo(rset.getInt("post_no"));
				p.setPostFrom(rset.getString("post_from"));
				p.setPostTo(rset.getString("post_to"));
				p.setPostTitle(rset.getString("post_title"));
				p.setPostContent(rset.getString("post_content"));
				p.setPostDate(rset.getString("post_date"));
				p.setIsRead(rset.getString("isread").charAt(0));
				p.setTradeNo(rset.getInt("trade_no"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	//내가 보낸 쪽지 리스트 표시
	public ArrayList<Post> selectMySendList(Connection conn, int start, int end, String memberId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Post> myList = new ArrayList<Post>();
		
		//String query = "select * from (select rownum as rnum, n.* from (select p.* from post p join member on post_from = member_id where member_id='test6' order by post_no desc ) n ) where rnum BETWEEN ? and ?";
		String query = "select * from (select rownum as rnum, n.* from (select p.* from post p where post_from = ? order by post_no desc ) n ) where rnum BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Post p = new Post();
				p.setPostNo(rset.getInt("post_no"));
				p.setPostFrom(rset.getString("post_from"));
				p.setPostTo(rset.getString("post_to"));
				p.setPostTitle(rset.getString("post_title"));
				p.setPostContent(rset.getString("post_content"));
				p.setPostDate(rset.getString("post_date"));
				p.setIsRead(rset.getString("isread").charAt(0));
				p.setTradeNo(rset.getInt("trade_no"));
				myList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return myList;
	}
	
	//쪽지 페이지
	public int selectTotalCount(Connection conn, String memberId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from post where post_to=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//보낸 쪽지 페이지
	public int selectMyTotalCount(Connection conn, String memberId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from post where post_from=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//읽음 여부 확인
	public int updateIsRead(Connection conn, int postNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update post set isread='Y' where post_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	//쪽지 내용
	public Post selectOnePost(Connection conn, int postNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Post p = null;
		String query = "select * from post where post_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				p = new Post();
				p.setPostNo(rset.getInt("post_no"));
				p.setPostTitle(rset.getString("post_title"));
				p.setPostContent(rset.getString("post_content"));
				p.setPostFrom(rset.getString("post_from"));
				p.setPostTo(rset.getString("post_to"));
				p.setIsRead(rset.getString("isread").charAt(0));
				p.setPostDate(rset.getString("post_date"));
				p.setTradeNo(rset.getInt("trade_no"));
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
		return p;
		
	}
	
	

	//쪽지 삭제
	public int deletePost(Connection conn, int postNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from post where post_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//안읽은 쪽지 개수
	public int selectNotReadPost(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from post where post_to=? and isread='N'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}





}
