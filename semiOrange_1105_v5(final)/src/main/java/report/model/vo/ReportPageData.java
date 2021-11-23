package report.model.vo;

import java.util.ArrayList;

public class ReportPageData {
	private ArrayList<Report> list;
	private String pageNavi;
	private int start;
	public ReportPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportPageData(ArrayList<Report> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<Report> getList() {
		return list;
	}
	public void setList(ArrayList<Report> list) {
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
