package mypage.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;
import mypage.model.dao.UpdateMyInfoDao;

public class UpdateMyInfoService {

	//내 정보 수정
	public int updateMember(Member member) {
			// TODO Auto-generated method stub
			Connection conn = JDBCTemplate.getConnection();
			int result = new UpdateMyInfoDao().updateMember(conn, member);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}

	public int upgradeManner(int reviewLike, String reviewee) {
		Connection conn = JDBCTemplate.getConnection();
		//100 넘는지 확인
		boolean over = new UpdateMyInfoDao().selectUpManner(conn, reviewee);
		int result = 0;
		if(over == true) { //100 넘는 경우
			result = new UpdateMyInfoDao().setMaxManner(conn, reviewee);
		} else {
			result = new UpdateMyInfoDao().upgradeMember(conn, reviewLike, reviewee);
		}
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}


	public int degradeManner(int reviewLike, String reviewee) {
		Connection conn = JDBCTemplate.getConnection();
		//마이너스 되는지 확인
		boolean under = new UpdateMyInfoDao().selectDownManner(conn, reviewee);
		int result = 0;
		if(under == true) { //0 미만이 되는 경우
			result = new UpdateMyInfoDao().setMinManner(conn, reviewee);
		} else {
			result = new UpdateMyInfoDao().degradeMember(conn, reviewLike, reviewee);
		}
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
