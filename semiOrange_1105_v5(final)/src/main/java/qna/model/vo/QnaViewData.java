package qna.model.vo;

import java.util.ArrayList;

public class QnaViewData {
	private ArrayList<QnaComment> list;
	private Qna q;
	public QnaViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaViewData(ArrayList<QnaComment> list, Qna q) {
		super();
		this.list = list;
		this.q = q;
	}
	public ArrayList<QnaComment> getList() {
		return list;
	}
	public void setList(ArrayList<QnaComment> list) {
		this.list = list;
	}
	public Qna getQ() {
		return q;
	}
	public void setQ(Qna q) {
		this.q = q;
	}
	
}
