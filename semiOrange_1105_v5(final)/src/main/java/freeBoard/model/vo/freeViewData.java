package freeBoard.model.vo;

import java.util.ArrayList;

public class freeViewData {
	private ArrayList<Comment1> list;
	private FreeBoard f;
	public freeViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public freeViewData(ArrayList<Comment1> list, FreeBoard f) {
		super();
		this.list = list;
		this.f = f;
	}
	public ArrayList<Comment1> getList() {
		return list;
	}
	public void setList(ArrayList<Comment1> list) {
		this.list = list;
	}
	public FreeBoard getF() {
		return f;
	}
	public void setF(FreeBoard f) {
		this.f = f;
	}
	
}
