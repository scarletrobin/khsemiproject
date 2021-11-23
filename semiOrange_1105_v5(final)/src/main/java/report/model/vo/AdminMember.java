package report.model.vo;

public class AdminMember {
 private int memberNo;
 private String memberId;
 private String memberPw;
 private String memberName;
 private String phone;
 private String postCode;
 private String address1;
 private String address2;
 private String email;
 private int memberLevel;
 private String enrollDate;
 private int manner;
 private String filepath;
 private int sms;
 private String local1;
 private String local2;
 private String local3;
public AdminMember() {
	super();
	// TODO Auto-generated constructor stub
}
public AdminMember(int memberNo, String memberId, String memberPw, String memberName, String phone, String postCode,
		String address1, String address2, String email, int memberLevel, String enrollDate, int manner,
		String filepath, int sms, String local1, String local2, String local3) {
	super();
	this.memberNo = memberNo;
	this.memberId = memberId;
	this.memberPw = memberPw;
	this.memberName = memberName;
	this.phone = phone;
	this.postCode = postCode;
	this.address1 = address1;
	this.address2 = address2;
	this.email = email;
	this.memberLevel = memberLevel;
	this.enrollDate = enrollDate;
	this.manner = manner;
	this.filepath = filepath;
	this.sms = sms;
	this.local1 = local1;
	this.local2 = local2;
	this.local3 = local3;
}
public int getMemberNo() {
	return memberNo;
}
public void setMemberNo(int memberNo) {
	this.memberNo = memberNo;
}
public String getMemberId() {
	return memberId;
}
public void setMemberId(String memberId) {
	this.memberId = memberId;
}
public String getMemberPw() {
	return memberPw;
}
public void setMemberPw(String memberPw) {
	this.memberPw = memberPw;
}
public String getMemberName() {
	return memberName;
}
public void setMemberName(String memberName) {
	this.memberName = memberName;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getPostCode() {
	return postCode;
}
public void setPostCode(String postCode) {
	this.postCode = postCode;
}
public String getAddress1() {
	return address1;
}
public void setAddress1(String address1) {
	this.address1 = address1;
}
public String getAddress2() {
	return address2;
}
public void setAddress2(String address2) {
	this.address2 = address2;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getMemberLevel() {
	return memberLevel;
}
public void setMemberLevel(int memberLevel) {
	this.memberLevel = memberLevel;
}
public String getEnrollDate() {
	return enrollDate;
}
public void setEnrollDate(String enrollDate) {
	this.enrollDate = enrollDate;
}
public int getManner() {
	return manner;
}
public void setManner(int manner) {
	this.manner = manner;
}
public String getFilepath() {
	return filepath;
}
public void setFilepath(String filepath) {
	this.filepath = filepath;
}
public int getSms() {
	return sms;
}
public void setSms(int sms) {
	this.sms = sms;
}
public String getLocal1() {
	return local1;
}
public void setLocal1(String local1) {
	this.local1 = local1;
}
public String getLocal2() {
	return local2;
}
public void setLocal2(String local2) {
	this.local2 = local2;
}
public String getLocal3() {
	return local3;
}
public void setLocal3(String local3) {
	this.local3 = local3;
}

 
}
