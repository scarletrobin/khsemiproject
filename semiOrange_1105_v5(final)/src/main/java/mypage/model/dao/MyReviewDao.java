package mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.JDBCTemplate;
import mypage.model.vo.Review;


public class MyReviewDao {

	public int insertReview(Connection conn, Review r) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO REVIEW VALUES (REVIEW_SEQ.NEXTVAL, ?, TO_CHAR(SYSDATE,'YYYY-MM-DD'), ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, r.getReviewLike());
			pstmt.setString(2, r.getReviewContent());
			pstmt.setString(3, r.getReviewWriter());
			pstmt.setString(4, r.getReviewee());
			pstmt.setInt(5, r.getTransactionNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
