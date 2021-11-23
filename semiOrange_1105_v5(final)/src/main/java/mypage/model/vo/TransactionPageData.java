package mypage.model.vo;

import java.util.ArrayList;


public class TransactionPageData {
	
	ArrayList<Transaction> list;
	private String pageNavi;
	private int start;
	public TransactionPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransactionPageData(ArrayList<Transaction> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<Transaction> getList() {
		return list;
	}
	public void setList(ArrayList<Transaction> list) {
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
