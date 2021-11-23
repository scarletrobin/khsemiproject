package freeBoard.model.vo;

public class Comment1 {
	private int commentNo; 			// 댓글번호
	private String memberId;		// 회원아이디
	private String commentContent; 	// 댓글내용
	private String regDate; 		// 작성일
	private int boardNo; 			// 해당게시판의 게시물번호
	private int boardType; 			// 게시판 구분(1.자유 2.거래 3.QnA)
	public Comment1() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment1(int commentNo, String memberId, String commentContent, String regDate, int boardNo,
			int boardType, int like_it) {
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
	public String getCommentContentBr() {
		return commentContent.replace("\r\n", "<br>");
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
