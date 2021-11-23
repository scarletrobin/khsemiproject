package post.model.vo;

public class Post {
	private int postNo; // 쪽지 고유번호
	private String postFrom; // 쪽지 수신자
	private String postTo; // 쪽지 발신자
	private String postTitle; // 쪽지 제목
	private String postContent; // 쪽지 제목
	private String postDate; // 쪽지 보낸 날짜, 시간
	private char isRead; // 쪽지 읽었는지 여부
	private int tradeNo; // 거래 게시판 번호 외래키로 참조

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int postNo, String postFrom, String postTo, String postTitle, String postContent, String postDate,
			char isRead, int tradeNo) {
		super();
		this.postNo = postNo;
		this.postFrom = postFrom;
		this.postTo = postTo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postDate = postDate;
		this.isRead = isRead;
		this.tradeNo = tradeNo;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getPostFrom() {
		return postFrom;
	}

	public void setPostFrom(String postFrom) {
		this.postFrom = postFrom;
	}

	public String getPostTo() {
		return postTo;
	}

	public void setPostTo(String postTo) {
		this.postTo = postTo;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	
	public String getPostContentBr() {
		return postContent.replaceAll("\r\n", "<br>");
	}
	
	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public char getIsRead() {
		return isRead;
	}

	public void setIsRead(char isRead) {
		this.isRead = isRead;
	}

	public int getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(int tradeNo) {
		this.tradeNo = tradeNo;
	}

}
