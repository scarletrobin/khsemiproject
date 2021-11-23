package mypage.model.vo;

public class Sigungu {
	private int sigunguNo;
	private String sigunguDo;
	private String sigunguName;
	public Sigungu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sigungu(int sigunguNo, String sigunguDo, String sigunguName) {
		super();
		this.sigunguNo = sigunguNo;
		this.sigunguDo = sigunguDo;
		this.sigunguName = sigunguName;
	}
	public int getSigunguNo() {
		return sigunguNo;
	}
	public void setSigunguNo(int sigunguNo) {
		this.sigunguNo = sigunguNo;
	}
	public String getSigunguDo() {
		return sigunguDo;
	}
	public void setSigunguDo(String sigunguDo) {
		this.sigunguDo = sigunguDo;
	}
	public String getSigunguName() {
		return sigunguName;
	}
	public void setSigunguName(String sigunguName) {
		this.sigunguName = sigunguName;
	}

}
