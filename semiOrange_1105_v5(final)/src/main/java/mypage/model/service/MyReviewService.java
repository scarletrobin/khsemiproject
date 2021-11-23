package mypage.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import mypage.model.dao.MyReviewDao;
import mypage.model.vo.Review;

public class MyReviewService {

	public int insertReview(Review r) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MyReviewDao().insertReview(conn, r);
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
			JDBCTemplate.close(conn);
		return result;
	}

}
