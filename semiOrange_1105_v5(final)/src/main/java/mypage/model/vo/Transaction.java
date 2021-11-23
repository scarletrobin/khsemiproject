package mypage.model.vo;

public class Transaction {
	private int transactionNo;
	private String buyer;
	private int tradeNo;
	private String seller;
	private String transactionDate;
	private int transactionPrice;
	private String tradeTitle;
	private String category;
	private String filePath;
	private int reviewCount;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int transactionNo, String buyer, int tradeNo, String seller, String transactionDate,
			int transactionPrice, String tradeTitle, String category, String filePath, int reviewCount) {
		super();
		this.transactionNo = transactionNo;
		this.buyer = buyer;
		this.tradeNo = tradeNo;
		this.seller = seller;
		this.transactionDate = transactionDate;
		this.transactionPrice = transactionPrice;
		this.tradeTitle = tradeTitle;
		this.category = category;
		this.filePath = filePath;
		this.reviewCount = reviewCount;
	}
	public int getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(int transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public int getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(int tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public int getTransactionPrice() {
		return transactionPrice;
	}
	public void setTransactionPrice(int transactionPrice) {
		this.transactionPrice = transactionPrice;
	}
	public String getTradeTitle() {
		return tradeTitle;
	}
	public void setTradeTitle(String tradeTitle) {
		this.tradeTitle = tradeTitle;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	
	
}