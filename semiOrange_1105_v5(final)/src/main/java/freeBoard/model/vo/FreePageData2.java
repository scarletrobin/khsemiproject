package freeBoard.model.vo;

import java.util.ArrayList;

public class FreePageData2 {
	private ArrayList<FreeBoard> list;
	private String pageNavi;
	private int start;
	public FreePageData2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FreePageData2(ArrayList<FreeBoard> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
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
	
	}
	