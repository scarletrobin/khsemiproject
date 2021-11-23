package freeBoard.model.vo;

public class LikeIt {
	private String memberId; // 회원아이디
	private int freeNo;		 // 게시판번호
	private int likeIt;		 // 좋아요

	public LikeIt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeIt(int freeNo, int likeIt, String memberId) {
		super();
		this.freeNo = freeNo;
		this.likeIt = likeIt;
		this.memberId = memberId;
	}
	public int getFreeNo() {
		return freeNo;
	}
	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
	}
	public int getLikeIt() {
		return likeIt;
	}
	public void setLikeIt(int likeIt) {
		this.likeIt = likeIt;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
