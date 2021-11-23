package notice.model.vo;

public class Notice {
	private int noticeNo;
	private String memberId;
	private String noticeTitle;
	private String noticeContent;
	private int readCount;
	private String regDate;
	private String filename;
	private String filepath;
	private int ncCount;
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notice(int noticeNo, String memberId, String noticeTitle, String noticeContent, int readCount,
			String regDate, String filename, String filepath, int ncCount) {
		super();
		this.noticeNo = noticeNo;
		this.memberId = memberId;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.readCount = readCount;
		this.regDate = regDate;
		this.filename = filename;
		this.filepath = filepath;
		this.ncCount = ncCount;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContentBr() {
		return noticeContent.replaceAll("\r\n", "<br>");
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
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
	public int getNcCount() {
		return ncCount;
	}
	public void setNcCount(int ncCount) {
		this.ncCount = ncCount;
	}
	
}
