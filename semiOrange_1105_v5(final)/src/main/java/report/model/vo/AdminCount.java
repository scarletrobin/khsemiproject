package report.model.vo;

public class AdminCount {
	private int alljoin; //총가입
	private int newjoin; //오늘가입
	private int alltrade; //총거래글수
	private int newtrade; //오늘거래글수
	private int allfree; //총자유글수
	private int newfree; //오늘자유글수
	public AdminCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminCount(int alljoin, int newjoin, int alltrade, int newtrade, int allfree, int newfree) {
		super();
		this.alljoin = alljoin;
		this.newjoin = newjoin;
		this.alltrade = alltrade;
		this.newtrade = newtrade;
		this.allfree = allfree;
		this.newfree = newfree;
	}
	public int getAlljoin() {
		return alljoin;
	}
	public void setAlljoin(int alljoin) {
		this.alljoin = alljoin;
	}
	public int getNewjoin() {
		return newjoin;
	}
	public void setNewjoin(int newjoin) {
		this.newjoin = newjoin;
	}
	public int getAlltrade() {
		return alltrade;
	}
	public void setAlltrade(int alltrade) {
		this.alltrade = alltrade;
	}
	public int getNewtrade() {
		return newtrade;
	}
	public void setNewtrade(int newtrade) {
		this.newtrade = newtrade;
	}
	public int getAllfree() {
		return allfree;
	}
	public void setAllfree(int allfree) {
		this.allfree = allfree;
	}
	public int getNewfree() {
		return newfree;
	}
	public void setNewfree(int newfree) {
		this.newfree = newfree;
	}
	

}
