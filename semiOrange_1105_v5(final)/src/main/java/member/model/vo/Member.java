package member.model.vo;

public class Member {
	private int memberNo; // 회원번호
	private String memberId; // 아이디
	private String memberPw; // 비밀번호
	private String memberName; // 이름
	private String phone; // 휴대폰 번호
	private String postcode; // 우편번호
	private String address1; // 지번 or 도로명 주소
	private String address2; // 상세주소
	private String email; // 이메일
	private int memberLevel; // 회원레벨 9가 관리자 1이 회원
	private String enrollDate; // 가입일

	// 10월 20일에 피드백 후 추가된것
	private int manner; // 매너 온도
	private String filePath; // 프로필 사진 경로
	private int sms; // 문자 수신여부 동의

	// 관심지역 설정
	private int local1; // 관심지역1 (필수)
	private int local2; // 관심지역2 (선택)
	private int local3; // 관심지역3 (선택)

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(int memberNo, String memberId, String memberPw, String memberName, String phone, String postcode,
			String address1, String address2, String email, int memberLevel, String enrollDate, int manner,
			String filePath, int sms, int local1, int local2, int local3) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.phone = phone;
		this.postcode = postcode;
		this.address1 = address1;
		this.address2 = address2;
		this.email = email;
		this.memberLevel = memberLevel;
		this.enrollDate = enrollDate;
		this.manner = manner;
		this.filePath = filePath;
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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getSms() {
		return sms;
	}

	public void setSms(int sms) {
		this.sms = sms;
	}

	public int getLocal1() {
		return local1;
	}

	public void setLocal1(int local1) {
		this.local1 = local1;
	}

	public int getLocal2() {
		return local2;
	}

	public void setLocal2(int local2) {
		this.local2 = local2;
	}

	public int getLocal3() {
		return local3;
	}

	public void setLocal3(int local3) {
		this.local3 = local3;
	}

}
