package mypage.model.vo;

public class Review {
	private int reviewNo;
	private int transactionNo;
	private int reviewLike;
	private String reviewDate;
	private String reviewContent;
	private String reviewWriter;
	private String reviewee;
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Review(int reviewNo, int transactionNo, int reviewLike, String reviewDate, String reviewContent,
			String reviewWriter, String reviewee) {
		super();
		this.reviewNo = reviewNo;
		this.transactionNo = transactionNo;
		this.reviewLike = reviewLike;
		this.reviewDate = reviewDate;
		this.reviewContent = reviewContent;
		this.reviewWriter = reviewWriter;
		this.reviewee = reviewee;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(int transactionNo) {
		this.transactionNo = transactionNo;
	}
	public int getReviewLike() {
		return reviewLike;
	}
	public void setReviewLike(int reviewLike) {
		this.reviewLike = reviewLike;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewWriter() {
		return reviewWriter;
	}
	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}
	public String getReviewee() {
		return reviewee;
	}
	public void setReviewee(String reviewee) {
		this.reviewee = reviewee;
	}
	
	
	
	
	
}
