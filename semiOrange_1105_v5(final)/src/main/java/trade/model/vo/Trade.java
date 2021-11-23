package trade.model.vo;

public class Trade {
	private int tradeNo;
	private String tradeWriter;
	private String tradeTitle;
	private String tradeContent;
	private String category;
	private int price;
	private int readCount;
	private int tradeStatus;
	private String regDate;
	private String filename;
	private String filepath;
	private String tradeDate;
	private int tradeLocal;
	private String sigunguDo;
	private String sigunguName;

	public Trade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trade(int tradeNo, String tradeWriter, String tradeTitle, String tradeContent, String category, int price,
			int readCount, int tradeStatus, String regDate, String filename, String filepath, String tradeDate,
			int tradeLocal, String sigunguDo, String sigunguName) {
		super();
		this.tradeNo = tradeNo;
		this.tradeWriter = tradeWriter;
		this.tradeTitle = tradeTitle;
		this.tradeContent = tradeContent;
		this.category = category;
		this.price = price;
		this.readCount = readCount;
		this.tradeStatus = tradeStatus;
		this.regDate = regDate;
		this.filename = filename;
		this.filepath = filepath;
		this.tradeDate = tradeDate;
		this.tradeLocal = tradeLocal;
		this.sigunguDo = sigunguDo;
		this.sigunguName = sigunguName;
	}

	public int getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(int tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeWriter() {
		return tradeWriter;
	}

	public void setTradeWriter(String tradeWriter) {
		this.tradeWriter = tradeWriter;
	}

	public String getTradeTitle() {
		return tradeTitle;
	}

	public void setTradeTitle(String tradeTitle) {
		this.tradeTitle = tradeTitle;
	}

	public String getTradeContent() {
		return tradeContent;
	}
	
	public String getTradeContentBr() {
		return tradeContent.replace("\r\n", "<br>");
	}

	public void setTradeContent(String tradeContent) {
		this.tradeContent = tradeContent;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(int tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public int getTradeLocal() {
		return tradeLocal;
	}

	public void setTradeLocal(int tradeLocal) {
		this.tradeLocal = tradeLocal;
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
