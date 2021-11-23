package freeBoard.model.vo;

public class FreeBoard {
	private int freeNo;			// 글번호	
	private String freeTitle;	// 제목
	private String freeContent; // 내용
	private String freeWriter;	// 작성자
	private int readCount;		// 조회수
	private String regDate;		// 작성일
	private int priority;		// 관리자 고정 공지사항 (1:고정, 0:고정x)
	private String filename;	// 첨부파일이름
	private String filepath;	// 첨부파일경로
	private int ncCount;		// 댓글수 (자유게시판 앞에 표시)
	private int likeCount;		// 좋아요
	public FreeBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FreeBoard(int freeNo, String freeTitle, String freeContent, String freeWriter, int readCount, String regDate,
			int priority, String filename, String filepath, int ncCount, int likeCount) {
		super();
		this.freeNo = freeNo;
		this.freeTitle = freeTitle;
		this.freeContent = freeContent;
		this.freeWriter = freeWriter;
		this.readCount = readCount;
		this.regDate = regDate;
		this.priority = priority;
		this.filename = filename;
		this.filepath = filepath;
		this.ncCount = ncCount;
		this.likeCount = likeCount;
	}
	public int getFreeNo() {
		return freeNo;
	}
	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
	}
	public String getFreeTitle() {
		return freeTitle;
	}
	public void setFreeTitle(String freeTitle) {
		this.freeTitle = freeTitle;
	}
	public String getFreeContent() {
		return freeContent;
	}
	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
	}
	public String getFreeWriter() {
		return freeWriter;
	}
	public void setFreeWriter(String freeWriter) {
		this.freeWriter = freeWriter;
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
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
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

}
