package report.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import report.model.vo.AllWrite;
import report.model.vo.ChartData;
import report.model.vo.AdminMember;
import report.model.vo.Report;

public class ReportDao {

	public int insertMember(Connection conn, Report r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into report values(report_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),default)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getReportWriter());
			pstmt.setString(2, r.getReportedMember());
			pstmt.setString(3, r.getReportContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<AdminMember> selectAllMember(Connection conn,int start,int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AdminMember> list = new ArrayList<AdminMember>();
		String query = "select*from (select rownum as rnum, n.*from (select*from member order by member_no desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AdminMember m = new AdminMember();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setPhone(rset.getString("phone"));
				m.setPostCode(rset.getString("postcode"));
				m.setAddress1(rset.getString("address1"));
				m.setAddress2(rset.getString("address2"));
				m.setEmail(rset.getString("email"));
				m.setMemberLevel(rset.getInt("member_level"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setManner(rset.getInt("manner"));
				m.setFilepath(rset.getString("filepath"));
				m.setSms(rset.getInt("sms"));
				m.setLocal1(rset.getString("local1"));
				m.setLocal2(rset.getString("local2"));
				m.setLocal3(rset.getString("local3"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Report> selectAllReport(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Report> list = new ArrayList<Report>();
		String query = "select*from (select rownum as rnum, n.*from (select*from report order by report_no desc)n) where rnum between 1 and 5";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Report r = new Report();
				r.setReportNo(rset.getInt("report_no"));
				r.setReportWriter(rset.getString("report_writer"));
				r.setReportedMember(rset.getString("reported_member"));
				r.setReportContent(rset.getString("report_content"));
				r.setReportDate(rset.getString("report_date"));
				r.setReportResult(rset.getString("report_result"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public AdminMember selectOneMember(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select*from member where member_no=?";
		AdminMember m = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				m = new AdminMember();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setPhone(rset.getString("phone"));
				m.setPostCode(rset.getString("postcode"));
				m.setAddress1(rset.getString("address1"));
				m.setAddress2(rset.getString("address2"));
				m.setEmail(rset.getString("email"));
				m.setMemberLevel(rset.getInt("member_level"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setManner(rset.getInt("manner"));
				m.setFilepath(rset.getString("filepath"));
				m.setSms(rset.getInt("sms"));
				m.setLocal1(rset.getString("local1"));
				m.setLocal2(rset.getString("local2"));
				m.setLocal3(rset.getString("local3"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	public int selectTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from member";
		try {
			pstmt= conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int changeLevel(Connection conn, int memberNo, int memberLevel) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_level=? where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberLevel);
			pstmt.setInt(2, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Report> selectAllReport(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Report> list = new ArrayList<Report>();
		String query = "select*from (select rownum as rnum, n.*from (select*from report order by report_no desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Report r = new Report();
				r.setReportNo(rset.getInt("report_no"));
				r.setReportWriter(rset.getString("report_writer"));
				r.setReportedMember(rset.getString("reported_member"));
				r.setReportContent(rset.getString("report_content"));
				r.setReportDate(rset.getString("report_date"));
				r.setReportResult(rset.getString("report_result"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Report> selectCntList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Report> list = new ArrayList<Report>();
		String query = "SELECT N.*,MEMBER_LEVEL,MEMBER_NO FROM (SELECT REPORTED_MEMBER, COUNT(*) AS cnt FROM (SELECT *FROM REPORT WHERE report_result='Y') GROUP BY REPORTED_MEMBER)N JOIN MEMBER ON(MEMBER_ID=REPORTED_MEMBER) ORDER BY CNT DESC";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Report r = new Report();
				r.setReportedMember(rset.getString("reported_member"));
				r.setrCount(rset.getInt("cnt"));
				r.setmLevel(rset.getInt("member_level"));
				r.setmNo(rset.getInt("member_no"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int chkChangeResult(Connection conn, int reportNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update report set report_result='Y' where report_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reportNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<AllWrite> selectFreeNotice(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AllWrite> list = new ArrayList<AllWrite>();
		String query = "SELECT *FROM FREEBOARD WHERE FREE_WRITER= ? ORDER BY FREE_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AllWrite a = new AllWrite();
				a.setFreeNo(rset.getInt("free_no"));
				a.setFreeTitle(rset.getString("free_title"));
				a.setFreeRegDate(rset.getString("reg_date"));
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<AllWrite> selectTradeNotice(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AllWrite> list = new ArrayList<AllWrite>();
		String query = "SELECT *FROM TRADE WHERE TRADE_WRITER= ? ORDER BY TRADE_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AllWrite a = new AllWrite();
				a.setTradeNo(rset.getInt("trade_no"));
				a.setTradeTitle(rset.getString("trade_title"));
				a.setTradeRegDate(rset.getString("reg_date"));
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<AllWrite> selectCommentList(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AllWrite> list = new ArrayList<AllWrite>();
		String query = "SELECT *FROM COMMENT1 WHERE MEMBER_ID= ? ORDER BY COMMENT_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AllWrite a = new AllWrite();
				a.setCommentNo(rset.getInt("comment_no"));
				a.setCommentContent(rset.getString("comment_content"));
				a.setCmtDate(rset.getString("reg_date"));
				a.setTotalNo(rset.getInt("board_no"));
				a.setBoardType(rset.getInt("board_type"));
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int chkDeleteReport(Connection conn, int reportNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM REPORT WHERE REPORT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reportNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<AdminMember> selectAllMember2(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AdminMember> list = new ArrayList<AdminMember>();
		String query = "select*from (select rownum as rnum, n.*from (select*from member order by member_level desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AdminMember m = new AdminMember();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setPhone(rset.getString("phone"));
				m.setPostCode(rset.getString("postcode"));
				m.setAddress1(rset.getString("address1"));
				m.setAddress2(rset.getString("address2"));
				m.setEmail(rset.getString("email"));
				m.setMemberLevel(rset.getInt("member_level"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setManner(rset.getInt("manner"));
				m.setFilepath(rset.getString("filepath"));
				m.setSms(rset.getInt("sms"));
				m.setLocal1(rset.getString("local1"));
				m.setLocal2(rset.getString("local2"));
				m.setLocal3(rset.getString("local3"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<AllWrite> selectBoardTop(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AllWrite> list = new ArrayList<AllWrite>();
		String query = "SELECT*FROM (SELECT TRADE_NO,TRADE_WRITER,TRADE_TITLE,REG_DATE,READ_COUNT FROM TRADE WHERE REG_DATE = (SELECT  TO_CHAR(SYSDATE,'YYYY-MM-DD') AS 오늘날짜 FROM DUAL)) ORDER BY READ_COUNT DESC";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AllWrite a = new AllWrite();
				a.setTradeNo(rset.getInt("trade_no"));
				a.setMemberId(rset.getString("trade_writer"));
				a.setTradeTitle(rset.getString("trade_title"));
				a.setTradeRegDate(rset.getString("reg_date"));
				a.setTradeReadCount(rset.getInt("read_count"));
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<AllWrite> selectFreeTop(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AllWrite> list = new ArrayList<AllWrite>();
		String query = "SELECT*FROM (SELECT FREE_NO,FREE_WRITER,FREE_TITLE,REG_DATE,READ_COUNT  FROM FREEBOARD WHERE REG_DATE LIKE (SELECT  TO_CHAR(SYSDATE,'YYYY-MM-DD') AS 오늘날짜 FROM DUAL)||'%') ORDER BY READ_COUNT DESC";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AllWrite a = new AllWrite();
				a.setFreeNo(rset.getInt("free_no"));
				a.setMemberId(rset.getString("free_writer"));
				a.setFreeTitle(rset.getString("free_title"));
				a.setFreeRegDate(rset.getString("reg_date"));
				a.setFreeReadCount(rset.getInt("read_count"));
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<AllWrite> selectQna(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AllWrite> list = new ArrayList<AllWrite>();
		String query = "select*from (select rownum as rnum,n.*from (select*from qna order by qna_no desc)n) where rnum between 1 and 5";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AllWrite a = new AllWrite();
				a.setQnaNo(rset.getInt("qna_no"));
				a.setMemberId(rset.getString("member_id"));
				a.setQnaTitle(rset.getString("qna_title"));
				a.setQnaRegDate(rset.getString("reg_date"));
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public ArrayList<ChartData> chartData(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ChartData> list = new ArrayList<ChartData>();
		String query = "SELECT*FROM\r\n" + 
				"(SELECT a.dt ENROLL_DATE\r\n" + 
				"     , NVL(SUM(b.tot), 0) CNT\r\n" + 
				"  FROM (SELECT TO_CHAR(TO_DATE('20211001', 'yyyymmdd') + LEVEL - 1, 'yyyymmdd') dt\r\n" + 
				"          FROM dual\r\n" + 
				"         CONNECT BY LEVEL <= TO_DATE('20211031', 'yyyymmdd')\r\n" + 
				"                           - TO_DATE('20211001', 'yyyymmdd') + 1\r\n" + 
				"        ) a\r\n" + 
				"     , ( SELECT TO_DATE(ENROLL_DATE, 'yyyy-mm-dd') dt\r\n" + 
				"     , COUNT(*) tot\r\n" + 
				"  FROM MEMBER\r\n" + 
				" WHERE ENROLL_DATE >= TO_DATE('20211001', 'yyyymmdd')\r\n" + 
				"   AND ENROLL_DATE <  TO_DATE('20211031', 'yyyymmdd')+1 \r\n" + 
				" GROUP BY ENROLL_DATE\r\n" + 
				"        ) b\r\n" + 
				" WHERE a.dt = b.dt(+)\r\n" + 
				" GROUP BY (a.dt))\r\n" + 
				" ORDER BY ENROLL_DATE\r\n";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ChartData cd = new ChartData();
				cd.setDate(rset.getString("enroll_date"));
				cd.setJoincnt(rset.getInt("cnt"));
				list.add(cd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int allJoin(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int alljoin = 0;
		String query = "SELECT COUNT(*) AS ALLJOIN FROM MEMBER";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				alljoin = rset.getInt("alljoin");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return alljoin;
	}

	public int newJoin(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int newjoin = 0;
		String query = "SELECT COUNT(*) AS NEWJOIN FROM (SELECT MEMBER_ID FROM MEMBER WHERE ENROLL_DATE = (SELECT  TO_CHAR(SYSDATE,'YYYY-MM-DD') AS 오늘날짜 FROM DUAL))";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				newjoin = rset.getInt("newjoin");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return newjoin;
	}

	public int allTrade(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int alltrade = 0;
		String query = "SELECT COUNT(*) AS ALLTRADE FROM TRADE";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				alltrade = rset.getInt("alltrade");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return alltrade;
	}

	public int newTrade(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int newtrade = 0;
		String query = "SELECT COUNT(*) AS NEWTRADE FROM (SELECT TRADE_NO FROM TRADE WHERE REG_DATE = (SELECT  TO_CHAR(SYSDATE,'YYYY-MM-DD') AS 오늘날짜 FROM DUAL))";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				newtrade = rset.getInt("newtrade");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return newtrade;
	}

	public int allFree(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int allfree = 0;
		String query = "SELECT COUNT(*) AS ALLFREE FROM FREEBOARD";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				allfree = rset.getInt("allfree");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return allfree;
	}

	public int newFree(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int newfree = 0;
		String query = "SELECT COUNT(*) AS NEWFREE FROM (SELECT FREE_NO  FROM FREEBOARD WHERE REG_DATE LIKE (SELECT  TO_CHAR(SYSDATE,'YYYY-MM-DD') AS 오늘날짜 FROM DUAL)||'%')";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				newfree = rset.getInt("newfree");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return newfree;
	}

	public int chkDeleteMember(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM MEMBER WHERE MEMBER_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectTotalReportCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from report";
		try {
			pstmt= conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<ChartData> chartData(Connection conn, String time1, String time2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ChartData> list = new ArrayList<ChartData>();
		String query = "SELECT*FROM\r\n" + 
				"(SELECT a.dt ENROLL_DATE\r\n" + 
				"     , NVL(SUM(b.tot), 0) CNT\r\n" + 
				"  FROM (SELECT TO_CHAR(TO_DATE(?, 'yyyymmdd') + LEVEL - 1, 'yyyymmdd') dt\r\n" + 
				"          FROM dual\r\n" + 
				"         CONNECT BY LEVEL <= TO_DATE(?, 'yyyymmdd')\r\n" + 
				"                           - TO_DATE(?, 'yyyymmdd') + 1\r\n" + 
				"        ) a\r\n" + 
				"     , ( SELECT TO_DATE(ENROLL_DATE, 'yyyy-mm-dd') dt\r\n" + 
				"     , COUNT(*) tot\r\n" + 
				"  FROM MEMBER\r\n" + 
				" WHERE ENROLL_DATE >= TO_DATE(?, 'yyyymmdd')\r\n" + 
				"   AND ENROLL_DATE <  TO_DATE(?, 'yyyymmdd')+1 \r\n" + 
				" GROUP BY ENROLL_DATE\r\n" + 
				"        ) b\r\n" + 
				" WHERE a.dt = b.dt(+)\r\n" + 
				" GROUP BY (a.dt))\r\n" + 
				" ORDER BY ENROLL_DATE\r\n";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, time2);
			pstmt.setString(2, time1);
			pstmt.setString(3, time2);
			pstmt.setString(4, time2);
			pstmt.setString(5, time1);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ChartData cd = new ChartData();
				cd.setDate(rset.getString("enroll_date"));
				cd.setJoincnt(rset.getInt("cnt"));
				list.add(cd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<AllWrite> selectReviewList(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AllWrite> list = new ArrayList<AllWrite>();
		String query = "select*from review where review_writer = ? order by review_no";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AllWrite a = new AllWrite();
				a.setReviewNo(rset.getInt("review_no"));
				a.setReviewDate(rset.getString("review_date"));
				a.setTransactionNo(rset.getShort("transaction_no"));
				a.setReviewLike(rset.getInt("review_like"));
				a.setReviewContent(rset.getString("review_content"));
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}



}
