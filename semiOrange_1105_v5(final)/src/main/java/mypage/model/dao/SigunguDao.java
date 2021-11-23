package mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypage.model.vo.Sigungu;

public class SigunguDao {

	public ArrayList<Sigungu> searchProvince(Connection conn, String province) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Sigungu> list = new ArrayList<Sigungu>();
		String query = "SELECT * FROM SIGUNGU WHERE SIGUNGU_DO LIKE ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, province+"%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Sigungu s = new Sigungu();
				s.setSigunguNo(rset.getInt("SIGUNGU_NO"));
				s.setSigunguDo(rset.getString("SIGUNGU_DO"));
				s.setSigunguName(rset.getString("SIGUNGU_NAME"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public ArrayList<Sigungu> selectSigungu(Connection conn, ArrayList<Sigungu> list) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Sigungu> sList = new ArrayList<Sigungu>();
		String query = "SELECT * FROM SIGUNGU WHERE SIGUNGU_NO IN (?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, list.get(0).getSigunguNo());
			pstmt.setInt(2, list.get(1).getSigunguNo());
			pstmt.setInt(3, list.get(2).getSigunguNo());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Sigungu s = new Sigungu();
				s.setSigunguNo(rset.getInt("SIGUNGU_NO"));
				s.setSigunguDo(rset.getString("SIGUNGU_DO"));
				s.setSigunguName(rset.getString("SIGUNGU_NAME"));
				sList.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return sList;
	}

	public Sigungu searchSigungu(Connection conn, int localNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Sigungu s = null;
		String query = "SELECT * FROM SIGUNGU WHERE SIGUNGU_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, localNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				s = new Sigungu();
				s.setSigunguNo(rset.getInt("SIGUNGU_NO"));
				s.setSigunguDo(rset.getString("SIGUNGU_DO"));
				s.setSigunguName(rset.getString("SIGUNGU_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return s;
	}

}
