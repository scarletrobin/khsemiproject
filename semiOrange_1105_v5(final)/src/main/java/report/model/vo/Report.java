package report.model.vo;

public class Report {

	private int reportNo;
	private String reportWriter;
	private String reportedMember;
	private String reportContent;
	private String reportDate;
	private String reportResult;
	private int rCount;
	private int mLevel;
	private int mNo;
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Report(int reportNo, String reportWriter, String reportedMember, String reportContent, String reportDate,
			String reportResult,int rCount,int mLevel,int mNo) {
		super();
		this.reportNo = reportNo;
		this.reportWriter = reportWriter;
		this.reportedMember = reportedMember;
		this.reportContent = reportContent;
		this.reportDate = reportDate;
		this.reportResult = reportResult;
		this.rCount=rCount;
		this.mLevel=mLevel;
		this.mNo=mNo;
	}
	
	public int getmNo() {
		return mNo;
	}
	public void setmNo(int mNo) {
		this.mNo = mNo;
	}
	public int getmLevel() {
		return mLevel;
	}
	public void setmLevel(int mLevel) {
		this.mLevel = mLevel;
	}
	public int getrCount() {
		return rCount;
	}
	public void setrCount(int rCount) {
		this.rCount = rCount;
	}
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public String getReportWriter() {
		return reportWriter;
	}
	public void setReportWriter(String reportWriter) {
		this.reportWriter = reportWriter;
	}
	public String getReportedMember() {
		return reportedMember;
	}
	public void setReportedMember(String reportedMember) {
		this.reportedMember = reportedMember;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportResult() {
		return reportResult;
	}
	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}
	
	
}
