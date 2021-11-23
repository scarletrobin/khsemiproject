<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<link rel="stylesheet" href="/css/member/join.css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div class="container-wrapper">
			<div class="join-wrap">
				<div class="join-logo">
					<img src="./img/orange.png" width=60px; height=60px;>회원가입
				</div>
				<form action="/join" method="post" enctype="multipart/form-data">
					<h5>아이디</h5>
					<div class="middle">
						<input type="text" name="memberId" id="memberId" class="form-control" placeholder="아이디 입력(4~12글자)" maxlength="12">
						<button type="button" id="memberIdChk" class="btn btn-primary">중복 확인</button>
					</div>
					<div>
						<span class="expResult">　</span><br>
					</div>
					<h5>비밀번호</h5>
					<input type="password" name="memberPw" id="memberPw" class="form-control" placeholder="비밀번호 입력(숫자, 대소문자 조합 4~12글자)" maxlength="12">
					<span class="expResult">　</span><br>
					<h5>비밀번호 확인</h5>
					<input type="password" name="memberPwre" id="memberPwre" class="form-control"placeholder="비밀번호 확인" maxlength="12">
					<span class="expResult">　</span><br>
					<h5>이름</h5>
					<input type="text" name="memberName" id="memberName" class="form-control"placeholder="이름(2~6글자)" maxlength="6">
					<div>
						<span class="expResult">　</span><br>
					</div>
					<h5>전화번호</h5>
					<input type="text" name="phone" id="phone" class="form-control" placeholder="전화번호 입력 -포함" maxlength="13">
					<span class="expResult">　</span><br>
					<h2>주소</h2>
					<div class="middle">
						<input type="text" name="postcode" id="postcode" class="form-control" readonly placeholder="우편번호">
						<button type="button" class="btn btn-primary" id="findpostcode">주소 찾기</button><!-- onclick="findAddr();"있었음 -->
					</div>
					
					<br>
					<input type="text" name="address1" id="address1" class="form-control" readonly placeholder="주소"><br>
					
					<input type="text" name="address2" id="address2" class="form-control" placeholder="상세주소" maxlength="33">
					<span class="expResult">　</span>
					<br><br>
					<h5>이메일</h5>
					<div class="middle">
						<input type="text" name="email" id="email" class="form-control" placeholder="이메일" maxlength="33"><button type="button" id="emailbutton" class="btn btn-primary">인증번호 받기</button><!-- onclick="sendMail();"있었음 -->
					</div>
					<span class="expResult">　</span><br>
					<br>
					<div id="auth">
						<input type="text" id="authCode" class="form-control" placeholder="인증코드 입력" maxlength="6">
						<button type="button" class="btn btn-primary" id="authBtn">인증하기</button>
						<span id="timeZone"></span>
						<span id="authMsg"></span>
					</div>	
					
					<br><br>
					<h5>프로필 사진</h5>
					<input type="file" name="filepath" id="filepath" accept=".gif, .jpg, .jpeg, .png"><br><br>
					<!-- 관심지역 1, 2, 3 1만 필수 -->
					<h5>관심지역1<span class="text-muted" style="font-size: 16px;">* 관심지역1은 반드시 선택하셔야 합니다</span></h5>
					
					<!-- 관심지역1 -->
		        	<select id="local1sel" class="localsel" >
                    <option value="" selected disabled>시, 도</option>
                    <option value="seoul">서울특별시</option>
                    <option value="busan">부산광역시</option>
                    <option value="daegu">대구광역시</option>
                    <option value="incheon">인천광역시</option>
                    <option value="kwangju">광주광역시</option>
                    <option value="deajeon">대전광역시</option>
                    <option value="ulsan">울산광역시</option>
                    <option value="sejong">세종특별자치시</option>
                    <option value="kyeonggi">경기도</option>
                    <option value="kangwon">강원도</option>
                    <option value="chungbuk">충청북도</option>
                    <option value="chungnam">충청남도</option>
                    <option value="jeonbuk">전라북도</option>
                    <option value="jeonnam">전라남도</option>
                    <option value="kyeongbuk">경상북도</option>
                    <option value="kyeongnam">경상남도</option>
                    <option value="jeju">제주특별자치도</option>
	        		</select>
			        <select id="local1" name="local1">
			        	<option value="0">시, 군, 구</option>
			        </select><br>
		        	<span class="expResult">　</span>
			        <!-- 관심지역1 끝 -->
			        <br>
			        <!-- 관심지역2 -->
			        <h5>관심지역2</h5>
		        	<select id="local2sel" class="localsel">
                <option value="" selected disabled>시, 도</option>
                <option value="seoul">서울특별시</option>
                <option value="busan">부산광역시</option>
                <option value="daegu">대구광역시</option>
                <option value="incheon">인천광역시</option>
                <option value="kwangju">광주광역시</option>
                <option value="deajeon">대전광역시</option>
                <option value="ulsan">울산광역시</option>
                <option value="sejong">세종특별자치시</option>
                <option value="kyeonggi">경기도</option>
                <option value="kangwon">강원도</option>
                <option value="chungbuk">충청북도</option>
                <option value="chungnam">충청남도</option>
                <option value="jeonbuk">전라북도</option>
                <option value="jeonnam">전라남도</option>
                <option value="kyeongbuk">경상북도</option>
                <option value="kyeongnam">경상남도</option>
                <option value="jeju">제주특별자치도</option>
	        		</select>
			        <select id="local2" name="local2">
			        	<option value="0">시, 군, 구</option>
			        </select><br>
			        <!-- 관심지역2 끝 -->
			        <br>
			        <!-- 관심지역3 -->
			        <h5>관심지역3</h5>
		        	<select id="local3sel" class="localsel">
                <option value="" selected disabled>시, 도</option>
                <option value="seoul">서울특별시</option>
                <option value="busan">부산광역시</option>
                <option value="daegu">대구광역시</option>
                <option value="incheon">인천광역시</option>
                <option value="kwangju">광주광역시</option>
                <option value="deajeon">대전광역시</option>
                <option value="ulsan">울산광역시</option>
                <option value="sejong">세종특별자치시</option>
                <option value="kyeonggi">경기도</option>
                <option value="kangwon">강원도</option>
                <option value="chungbuk">충청북도</option>
                <option value="chungnam">충청남도</option>
                <option value="jeonbuk">전라북도</option>
                <option value="jeonnam">전라남도</option>
                <option value="kyeongbuk">경상북도</option>
                <option value="kyeongnam">경상남도</option>
                <option value="jeju">제주특별자치도</option>
	        		</select>
			        <select id="local3" name="local3">
			        	<option value="0">시, 군, 구</option>
			        </select>
			        <!-- 관심지역3 끝 -->
			        <br>
					<br><br>
					<input type="hidden" name="sms" id="agree" value="9">

					<input type="checkbox" id="chkAllBox">약관 전체 동의합니다.<br> 			
					<input type="checkbox" class="priBox">개인정보 수집 이용 동의(필수) 
					<a data-toggle="modal" href="#modal1">약관 보기</a>
					<br>
					<input type="checkbox" class="priBox">오렌지마켓 이용 약관(필수) 
					<a data-toggle="modal" href="#modal2">약관 보기</a>
					<br>
					<input type="checkbox" class="priBox">SMS 문자 수신 동의(선택) 
					<a data-toggle="modal" href="#modal3">약관 보기</a>
					<br>
					<span class="expResult"></span><br>
					
					<div>
						<input type="submit" class="btn btn-primary joinbtn" id="joinsubmit" value="회원가입">
					</div>
				</form>
			
			</div>
			<!--  -->
	<div class="modal" id="modal1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">약관</h5>
				</div>
					<div class="modal-body">
						<p class="agreement">
							(주)오렌지마켓은 아래의 목적으로 개인정보를 수집 및 이용하며, 회원의 개인정보를 안전하게 취급하는데 최선을 다하고 있습니다.<br>
							<b>1. 수집목적</b><br>
							- 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산<br>
							- 민원사무 처리<br>
							- 재화 또는 서비스 제공<br>
							- 휴대폰 번호를 활용한 문자 수신<br><br>
							
							<b>2. 수집항목</b><br>
							회원가입 시<br>
							(필수) 아이디, 비밀번호, 이메일, 이름, 성별, 생년월일, 휴대폰번호, 이메일 인증 결과<br><br>
							본인인증 시<br>
							(필수) 이름, 성별, 생년월일, 휴대폰번호, 이메일 인증결과<br><br>

							<b>3. 보유기간</b><br>
							수집된 정보는 회원탈퇴 요청 5일 후 지체없이 파기됩니다. <br>
							다만 내부 방침에 의해 서비스 부정이용기록은 부정 가입 및 이용 방지를 위하여 회원 탈퇴 시점으로부터 최대 1년간 보관 후 파기하며, 관계법령에 의해 보관해야 하는 정보는 법령이 정한 기간 동안 보관한 후 파기합니다. <br>
							서비스 제공을 위해 필요한 최소한의 개인정보이므로 동의를 해 주셔야 서비스 이용이 가능합니다.<br>
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary cls" data-dismiss="modal">닫기</button>
					</div>
			</div>
		</div>
	</div>		
		<div class="modal" id="modal2">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">약관</h5>
					</div>
						<div class="modal-body">
							<p class="agreement">
								제1조(목적)<br>
								이 약관은 주식회사 오렌지마켓이 운영하는 사이버 마켓 “오렌지마켓”(이하 “마켓”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 마켓과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.<br><br>
								제2조(정의)<br>
								① “마켓”이란 주식회사 오렌지마켓이 재화 또는 용역(이하 “재화 등”이라 함)을 이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화 등을 거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 사이버마켓을 운영하는 사업자의 의미로도 사용합니다.<br>
								② “이용자”란 “마켓”에 접속하여 이 약관에 따라 “몰”이 제공하는 서비스를 받는 회원 및 비회원을 말합니다.<br>
								③ ‘회원’이라 함은 “마켓”에 회원등록을 한 자로서, 계속적으로 “몰”이 제공하는 서비스를 이용할 수 있는 자를 말합니다.<br>
								④ ‘비회원’이라 함은 회원에 가입하지 않고 “마켓”이 제공하는 서비스를 이용하는 자를 말합니다.<br>
							</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary cls" data-dismiss="modal">닫기</button>
						</div>
				</div>
			</div>
		</div>		
			<div class="modal" id="modal3">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">약관</h5>
						</div>
						<div class="modal-body">
							<p class="agreement">
								귀하는 개인(신용)정보의 선택적인 수집∙이용, 제공에 대한 동의를 거부할 수 있습니다. 다만, 동의하지 않을 경우 관련 편의제공(이벤트 안내, 공지사항, 할인행사)안내 등 이용 목적에 따른 혜택에 제한이 있을 수 있습니다.
								그 밖에 계약과 관련된 불이익은 없습니다. 동의한 경우에도 귀하는 동의를 철회하거나 마케팅 목적으로 귀하에게 연락하는 것을 중지하도록 요청할 수 있습니다.<br><br>
								1. 수집 및 이용목적<br>
								고객에 대한 편의제공, 귀사 및 제휴업체의 상품·서비스 안내 및 이용권유, 사은·판촉행사 등의 마케팅 활동, 시장조사 및 상품·서비스 개발연구 등을 목적으로 수집·이용<br><br>
								2. 수집 및 이용항목<br>
								- 개인식별정보: 성명, 성별, 나이,휴대전화번호, e-mail 등<br>
							</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary cls" data-dismiss="modal">닫기</button>
						</div>
					</div>
				</div>
			</div>			
		</div>
	</div>
	<script type="text/javascript" src="/js/member/join.js"></script>
	<script type="text/javascript">
	//프로필 사진 업로드 용량제한, 첨부파일 제한
		$("#filepath").change(function(){
			var fileVal = $(this).val();
			var pathPoint = fileVal.lastIndexOf('.');
			var filePoint = fileVal.substring(pathPoint+1, this.length);
			var fileType = filePoint.toLowerCase();
			var fileSize = 10 * 1024 * 1024; //10메가
			if(fileType == 'jpg' || fileType == 'png' || fileType == 'jpeg' || fileType == 'gif'){
			var uploadFileSize = this.files[0].size;
				if(uploadFileSize > fileSize){
					alert("이미지 업로드 용량은 10MB 이하로 가능합니다.");
					$("#filepath").val("");
				}
			}else if(fileVal == ""){
				
			}else{
				alert("프로필 사진은 이미지 파일만 가능합니다.");
				$("#filepath").val("");
			}
		});
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>