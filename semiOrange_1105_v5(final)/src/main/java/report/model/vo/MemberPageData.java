package report.model.vo;

import java.util.ArrayList;

public class MemberPageData {
	private ArrayList<AdminMember> list;
	private String pageNavi;
	private int start;
	public MemberPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberPageData(ArrayList<AdminMember> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<AdminMember> getList() {
		return list;
	}
	public void setList(ArrayList<AdminMember> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

	
}