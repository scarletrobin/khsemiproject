package trade.model.vo;

import java.util.ArrayList;

public class TradePageData {

	ArrayList<Trade> list;
	private String pageNavi;
	private int start;

	public TradePageData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TradePageData(ArrayList<Trade> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}

	public ArrayList<Trade> getList() {
		return list;
	}

	public void setList(ArrayList<Trade> list) {
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
