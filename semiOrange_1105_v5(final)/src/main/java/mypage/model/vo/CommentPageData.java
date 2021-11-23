package mypage.model.vo;

import java.util.ArrayList;

import freeBoard.model.vo.Comment1;

public class CommentPageData {
	private ArrayList<Comment1> list;
	private String pageNavi;
	private int start;
	public CommentPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentPageData(ArrayList<Comment1> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<Comment1> getList() {
		return list;
	}
	public void setList(ArrayList<Comment1> list) {
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
