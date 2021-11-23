package post.model.vo;

import java.util.ArrayList;

public class PostPageData {
	private ArrayList<Post> list;
	private String pageNavi;
	private int start;
	private int totalPostCount;

	public PostPageData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostPageData(ArrayList<Post> list, String pageNavi, int start, int totalPostCount) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
		this.totalPostCount = totalPostCount;
	}

	public ArrayList<Post> getList() {
		return list;
	}

	public void setList(ArrayList<Post> list) {
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
	
	//11월 01일 전체 쪽지 갯수 
	public int getTotalPostCount() {
		return totalPostCount;
	}

	public void setTotalPostCount(int totalPostCount) {
		this.totalPostCount = totalPostCount;
	}

}
