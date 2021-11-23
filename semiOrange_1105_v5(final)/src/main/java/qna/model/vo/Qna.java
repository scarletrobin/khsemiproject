package qna.model.vo;

public class Qna {
	private int qnaNo;
	private String memberId;
	private String qnaTitle;
	private String qnaContent;
	private String regDate;
	private String fileName;
	private String filePath;
	private int qnaCount;
	private int memberLevel;
	public Qna() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Qna(int qnaNo, String memberId, String qnaTitle, String qnaContent, String regDate, String fileName,
			String filePath, int qnaCount, int memberLevel) {
		super();
		this.qnaNo = qnaNo;
		this.memberId = memberId;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.regDate = regDate;
		this.fileName = fileName;
		this.filePath = filePath;
		this.qnaCount = qnaCount;
		this.memberLevel = memberLevel;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaContentBr() {
		return qnaContent.replaceAll("\r\n", "<br>");
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getQnaCount() {
		return qnaCount;
	}
	public void setQnaCount(int qnaCount) {
		this.qnaCount = qnaCount;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
}
