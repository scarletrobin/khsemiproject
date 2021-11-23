package mypage.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypage.model.dao.SigunguDao;
import mypage.model.vo.Sigungu;

public class SigunguService {

	public ArrayList<Sigungu> searchProvince(String province) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Sigungu> list = new SigunguDao().searchProvince(conn, province);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Sigungu> selectSigungu(ArrayList<Sigungu> list) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Sigungu> sList = new SigunguDao().selectSigungu(conn, list);
		JDBCTemplate.close(conn);
		return sList;
	}

	public Sigungu searchSigungu(int localNo) {
		Connection conn = JDBCTemplate.getConnection();
		Sigungu s = new SigunguDao().searchSigungu(conn, localNo);
		JDBCTemplate.close(conn);
		return s;
	}

}
