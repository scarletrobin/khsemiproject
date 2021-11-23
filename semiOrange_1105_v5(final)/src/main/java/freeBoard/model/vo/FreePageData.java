package freeBoard.model.vo;

import java.util.ArrayList;

public class FreePageData {
	private ArrayList<FreeBoard> list;
	private String pageNavi;
	private int start;
	private int totalCount;
	private int fixPage;
	public FreePageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FreePageData(ArrayList<FreeBoard> list,String pageNavi, int start, int totalCount, int fixPage) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
		this.totalCount = totalCount;
		this.fixPage = fixPage;
		//this.fixPage = fixPage;
	}
	public ArrayList<FreeBoard> getList() {
		return list;
	}
	public void setList(ArrayList<FreeBoard> list) {
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getFixPage() {
		return fixPage;
	}
	public void setFixPage(int fixPage) {
		this.fixPage = fixPage;
	}

	
	}
	