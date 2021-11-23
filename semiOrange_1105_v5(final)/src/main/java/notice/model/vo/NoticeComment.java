package notice.model.vo;

public class NoticeComment {
	private int commentNo;
	private String memberId;
	private String commentContent;
	private String regDate;
	private int boardNo;
	private int boardType;
	public NoticeComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeComment(int commentNo, String memberId, String commentContent, String regDate, int boardNo,
			int boardType) {
		super();
		this.commentNo = commentNo;
		this.memberId = memberId;
		this.commentContent = commentContent;
		this.regDate = regDate;
		this.boardNo = boardNo;
		this.boardType = boardType;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getBoardType() {
		return boardType;
	}
	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	
}
