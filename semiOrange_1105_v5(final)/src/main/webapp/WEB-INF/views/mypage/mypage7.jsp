<%@page import="com.sun.mail.handlers.message_rfc822"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="mypage container">
		<div class="welcome-info row">
			<div><a href='/mypage?reqPage=1&memberId=${sessionScope.m.memberId }' class="mypagelogo">MY PAGE</a></div>
			<div><table class="welcome-content">
				<tbody>
					<tr>
						<th rowspan="2">
								<c:choose>
								<c:when test="${sessionScope.m.filePath eq null }">
								<img src="/img/orange.png" style="height: 100px;">
								</c:when>
								<c:otherwise>
								<div class="profilePic"><img src='/upload/member/${sessionScope.m.filePath }' id="profilePic" style="height: 100px;  width: 100px;"></div>
								</c:otherwise>
								</c:choose>
						</th>
						<td><h4 style="color: rgb(230, 126, 34); font-size: 22px;">오늘도 즐거운 오렌지생활 되세요 :)</h4></td>
					</tr>
					<tr>
						<td><h2 style="color: rgb(211, 84, 0); font-size: 30px;">[${sessionScope.m.memberId }]님!</h2></td>
					</tr>
					<tr>
						<td></td>
						<td><h4 style="font-size: 22px;">회원등급 : 
						<c:choose>
							<c:when test="${sessionScope.m.memberLevel eq 1 }">
							일반회원
							</c:when><c:when test="${sessionScope.m.memberLevel eq 2 }">
							블랙회원
							</c:when>
							<c:when test="${sessionScope.m.memberLevel eq 9 }">
							관리자
							</c:when>
						</c:choose>
						</h4>
						</td>
						<th><h5 style="color:rgb(127, 127, 127);">가입일 : ${sessionScope.m.enrollDate }</h5>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
	</div>
	<div class="mypagemain container row">
		<!-- 사이드메뉴 -->
		<div class="mypagesidenav side column">
			<ul id="mypagemenu">
				<li><h1>나의 오렌지 활동</h1></li>
				<li><a href="/mypage?reqPage=1&memberId=${sessionScope.m.memberId }">거래 내역 조회</a></li>
				<li><a href="/myPosts?reqPage=1&memberId=${sessionScope.m.memberId }">내가 올린 판매글</a></li>
				<c:choose>
					<c:when test="${not empty sessionScope.m && postCnt > 99}">
						<li class="notification">
							<a href="/postList?reqPage=1" class="posttest">쪽지함</a>
							<span class="notification-cnt">99+</span>
						</li>
					</c:when>
					<c:when test="${not empty sessionScope.m && postCnt gt 0 && postCnt lt 100}">
						<li class="notification">
							<a href="/postList?reqPage=1" class="posttest">쪽지함</a>
							<span class="notification-cnt">${postCnt }</span>
						</li>
					</c:when>
					<c:otherwise>
						<li>
						<a href="/postList?reqPage=1">쪽지함</a>
						</li>
					</c:otherwise>
				</c:choose>
				<li><a href="/myQna?reqPage=1&memberId=${sessionScope.m.memberId }">QnA 문의</a></li>
				<%-- <li><a href="/myWishlist?reqPage=1&memberId=${sessionScope.m.memberId }">관심물건</a></li> --%>
				<li><a href="/myCommunity?reqPage=1&memberId=${sessionScope.m.memberId }">커뮤니티</a></li>
				<li><a href="/myUserInfo?local1=${sessionScope.m.local1 }&local2=${sessionScope.m.local2}&local3=${sessionScope.m.local3}" style="color: rgb(230, 126, 34);">나의 정보</a></li>
			</ul>
		</div>
		<!-- 내 회원정보 -->
		<div class="mypage-content content column">
			<h1 class="mypage-content-title">나의 회원정보</h1><br>
			<!-- 회원정보목록 -->
			<div class="mypage-info">
				<form class="userinfo" action="/updateMyInfo" method="post" enctype="multipart/form-data">
					<div class="form-group row">
							<label for="profile" class="col-sm-2 col-form-label"><b>프로필</b></label>
							<div class="col-sm-5">
								<c:choose>
								<c:when test="${empty sessionScope.m.filePath }">
								<img src="/img/orange.png" id="pic" name="pic" class="pic" style="width:200px;">
								<small id="profileHelp" class="form-text text-muted">프로필 사진을 설정해보세요.</small>
								</c:when>
								<c:otherwise>
								<img src='/upload/member/${sessionScope.m.filePath }' id="pic" name="pic" class="pic" style="width:150px; height: 150px;">
								</c:otherwise>
								</c:choose>
							</div>
							<div class="col-sm-5"> 
								<input type="hidden" class="oldFilePath" name="oldFilePath" id="oldFilePath" value='${sessionScope.m.filePath }'>
								<input type="file" name="filepath" id="filepath" accept=".gif, .jpg, .jpeg, .png" style="display:none;"><br><br>
								<label for="filepath" class="changeprofile btn btn-outline-dark">사진 변경</label>
							</div>
					</div>
					<div class="form-group row">
							<label for="memberId" class="col-sm-2 col-form-label"><b>아이디</b></label>
							<div class="col-sm-5">
								<input type="text" class="form-control-plaintext" id="memberId" name="memberId" 
								readonly="readonly" value='${sessionScope.m.memberId }'>
								<input type="hidden" class="memberNo" id="memberNo" name="memberNo" value='${sessionScope.m.memberNo }'>
							</div>
							<div class="col-sm-5">
								<small id="enrollDate" class="form-text text-muted">가입일: ${sessionScope.m.enrollDate }</small>
							</div>
					</div>
					<div class="form-group row">
							<label for="memberName" class="col-sm-2 col-form-label"><b>이름</b></label>
							<div class="col-sm-5">
								<input type="text" class="form-control-plaintext" id="memberName" name="memberName" 
								readonly="readonly" value='${sessionScope.m.memberName }'>
							</div>
							<div class="col-sm-5">
							</div>
					</div>
					<div class="form-group row">
							<label for="memberLevel" class="col-sm-2 col-form-label"><b>회원등급</b></label>
							<div class="col-sm-5">
							<c:choose>
							<c:when test="${sessionScope.m.memberLevel eq 1}">
							<input type="text" class="form-control-plaintext" id="memberLevel" name="memberLevel"
							readonly="readonly" value="[일반회원]">
							</c:when>
							<c:when test="${sessionScope.m.memberLevel eq 2}">
							<input type="text" class="form-control-plaintext" id="memberLevel" name="memberLevel"
							readonly="readonly" value="[블랙회원]">
							</c:when>
							<c:when test="${sessionScope.m.memberLevel eq 9}">
							<input type="text" class="form-control-plaintext" id="memberLevel" name="memberLevel"
							readonly="readonly" value="[관리자]">
							</c:when>
							</c:choose>
							</div>
							<div class="col-sm-5">
							</div>
					</div>
					<div class="form-group row">
							<label for="manner" class="col-sm-2 col-form-label"><b>매너날씨</b></label>
							<div class="col-sm-5">
							<input type="hidden" id="manner" name="manner" value='${sessionScope.m.manner }'>
							<c:choose>
								<c:when test="${sessionScope.m.manner >= 80 }">
									<span class="mannerface" style="font-size: 20px; color:#78c46a;">${sessionScope.m.manner } 맑음 <img src="/img/happyface.png" style="width: 25px;"></span>
								</c:when>
								<c:when test="${sessionScope.m.manner >= 50 }">
									<span class="mannerface" style="font-size: 20px; color: #ffc600;">${sessionScope.m.manner } 보통 <img src="/img/normalface.png" style="width: 25px;"></span>
								</c:when>
								<c:otherwise>
									<span class="mannerface" style="font-size: 20px; color: #ff5200;">${sessionScope.m.manner } 흐림 <img src="/img/sadface.png" style="width: 25px;"></span>
								</c:otherwise>
							</c:choose>
							</div>
							<div class="col-sm-5">
							</div>
					</div>
					<div class="form-group row">
							<label for="memberPw" class="col-sm-2 col-form-label"><b>비밀번호</b></label>
							<div class="col-sm-5"><input type="password" class="form-control" id="memberPw" name="memberPw"
							value='${sessionScope.m.memberPw }'></div>
							<input type="hidden" id="originalPw" value='${sessionScope.m.memberPw }'>
							<div class="col-sm-5"><h6 class="pwResult"></h6>
							</div>
					</div>
					<div class="form-group row">
							<label for="memberPwRe" class="col-sm-2 col-form-label"><b>비밀번호 재확인</b></label>
							<div class="col-sm-5"><input type="password" class="form-control" id="memberPwRe" name="memberPwRe"
							value=""></div>
							<div class="col-sm-5">
							<h6 class="pwReResult"></h6>
							</div>
					</div>
					<div class="form-group row">
							<label for="email" class="col-sm-2 col-form-label"><b>이메일</b></label>
							<div class="col-sm-5"><input type="text" class="form-control-plaintext" id="email" name="email"
							readonly="readonly" value='${sessionScope.m.email }'></div>
							<div class="col-sm-5">
							</div>
					</div>
					<div class="form-group row">
							<label for="phone" class="col-sm-2 col-form-label"><b>전화번호</b></label>
							<div class="col-sm-5"><input type="text" class="form-control" id="phone" name="phone"
							value='${sessionScope.m.phone }'></div>
							<input type="hidden" id="originalPhone" value='${sessionScope.m.phone }'>
							<div class="col-sm-5">
							<h6 class="phoneResult"></h6>
							</div>
					</div>
					<div class="form-group row">
							<label for="local" class="col-sm-2 col-form-label"><b>관심지역</b></label>
							<div class="localbadges col-sm-5">
								<c:choose>
								<c:when test="${sessionScope.m.local2 eq 0 && sessionScope.m.local3 eq 0}">
								<span class="local1badge badge bg-primary"><span>${sList[0].sigunguDo }</span> <span>${sList[0].sigunguName }</span></span>
								<span class="local2badge badge bg-info"><span></span> <span></span></span>
								<span class="local3badge badge bg-info"><span></span> <span></span></span>
								</c:when>
								<c:when test="${sessionScope.m.local2 > 0 && sessionScope.m.local3 eq 0}">
								<span class="local1badge badge bg-primary"><span>${sList[0].sigunguDo }</span> <span>${sList[0].sigunguName }</span></span>
								<span class="local2badge badge bg-info"><span>${sList[1].sigunguDo }</span> <span>${sList[1].sigunguName }</span></span>
								<span class="local3badge badge bg-info"><span></span> <span></span></span>
								</c:when>
								<c:when test="${sessionScope.m.local2 > 0 && sessionScope.m.local3 > 0}">
								<span class="local1badge badge bg-primary"><span>${sList[0].sigunguDo }</span> <span>${sList[0].sigunguName }</span></span>
								<span class="local2badge badge bg-info"><span>${sList[1].sigunguDo }</span> <span>${sList[1].sigunguName }</span></span>
								<span class="local3badge badge bg-info"><span>${sList[2].sigunguDo }</span> <span>${sList[2].sigunguName }</span></span>
								</c:when>
								</c:choose>
								<input type="hidden" name="local1" id="local1" value='${sessionScope.m.local1 }'>
								<input type="hidden" name="local2" id="local2" value='${sessionScope.m.local2 }'>
								<input type="hidden" name="local3" id="local3" value='${sessionScope.m.local3 }'>
							</div>
							<div class="col-sm-5">
							<input type="button" class="changeLocal btn btn-outline-dark" data-toggle="modal" data-target="#changeLocal" value="지역 변경">
							</div>
					</div>
						<div class="form-group row">
							<label for="sms" class="col-sm-2 col-form-label"><b>수신설정</b></label>
							<div class="col-sm-5">
							<h5>댓글/쪽지 알림 설정(마케팅 정보수신 동의)</h5>
								<input type="hidden" id="sms" name="sms" value="${sessionScope.m.sms }">
								<c:choose>
								<c:when test="${sessionScope.m.sms eq 1 }"> <!-- 수신동의 yes인 경우 -->
								<p><input type="checkbox" id="smsChk" name="smsChk" checked> SMS</p>
								</c:when>
								<c:otherwise>
								<p><input type="checkbox" id="smsChk" name="smsChk"> SMS</p>
								</c:otherwise>
								</c:choose>
							</div>
							<div class="col-sm-5">
							</div>
					</div><br>
					<div class="myInfoButtons form-group">
						<fieldset style="margin-left: 35%;">
							<button type="submit" class="updateinfo btn btn-outline-primary">정보수정</button>
								<a href="#" class="signout btn btn-outline-secondary" style="border: none;">회원탈퇴</a>
						</fieldset>
					</div>
				</form>
				<!-- 지역변경 -->
					<div class="changeLocal modal fade" id="changeLocal" tabindex="-1" role="dialog" aria-hidden="true">
					  <div class="modal-dialog modal-lg" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" style="color:#e95420;">관심지역 변경</h5>
					        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true"></span>
					        </button>
					      </div>
					      <div class="local-info modal-body">
					        <div class="row">
					        	<div class="col-sm-3">관심지역2</div>
					        	<div class="localchange2 col-sm-3">
					        	<c:choose>
					        	<c:when test="${sessionScope.m.local2 > 0}">
					        	<span>${sList[1].sigunguDo }</span> <span>${sList[1].sigunguName }</span>
					        	</c:when>
					        	<c:otherwise>
					        	<span></span><span></span>
					        	</c:otherwise>
					        	</c:choose>
					        	</div>
					        	<div class="col-sm-3">
					        	<input type="button" class="changeLocal2 btn btn-outline-primary" value="지역2 추가/변경">
					        	</div>
					        </div>
					         <div class="row">
					        	<div class="col-sm-3">관심지역3</div>
					        	<div class="localchange3 col-sm-3">
					        	<c:choose>
					        	<c:when test="${sessionScope.m.local3 > 0 }">
					        	<span>${sList[2].sigunguDo }</span> <span>${sList[2].sigunguName }</span>
					        	</c:when>
					        	<c:otherwise>
					        	<span></span>
					        	</c:otherwise>
					        	</c:choose>
					        	</div>
					        	<div class="col-sm-3">
					        	<input type="button" class="changeLocal3 btn btn-outline-primary" value="지역3 추가/변경">
					        	</div>
					        </div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="localsubmit btn btn-primary">등록 완료</button>
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					      </div>
					    </div>
					  </div>
					</div>
			</div>
		</div>
		
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
<script>
		//사진업로드 확장자 제한 걸어둔곳
		//var oldFilePath = "${sessionScope.m.filePath}";
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
					return;
				}
			}else if(fileVal == ""){
				$("#filepath").val("");
				return;
			}else{
				alert("프로필 사진은 이미지 파일만 가능합니다.");
				$("#filepath").val("");
				return;
			}
			loadImg(this);
		});
		//사진 업로드
		function loadImg(obj){
			var files = obj.files;	//input type="file"에서 선택한 파일을 배열로 가져옴
			console.log(files);
			if(files.length != 0){	//첨부파일 있는 경우
				var reader = new FileReader(); //파일에 대한 정보를 읽어오는 객체
				reader.readAsDataURL(files[0]);	//파일경로를 읽어옴
				//경로를 다 읽어오면 실행할 함수 설정
				reader.onload = function(e){ //e 에 읽어온 파일 정보가 들어있음
					$("#pic").attr("src", e.target.result); //읽어온 경로를 img태그의 src속성에 설정
				}
				$("#profileHelp").html("멋지네요!");
				$("#profileHelp").css("margin-left","30px");
			} 
		}
	$(function(){
		//회원탈퇴
		$(".signout").click(function(){
			var signout_ans = confirm("회원탈퇴시 되돌릴 수 없으며, 회원 정보 역시 사라집니다. 정말로 탈퇴하시겠습니까?");
			var memberNo = $("#memberNo").val();
			if(signout_ans == true){
				location.href="/deleteMember?memberNo="+memberNo;
			}
			else{
				alert("회원탈퇴를 취소합니다.");
			}
		});

		//유효성검사하기
		var pwChk = true;
		var pwReChk = true;
		var phoneChk = true;
		var originalPwVal = $("#originalPw").val();
		var rv = true;
		//비밀번호 변경
		$("#memberPw").on("keyup", function(){
			var pwVal = $("#memberPw").val();
			var pwReg = /^[a-zA-Z0-9]{3,11}$/;
			if(pwReg.test(pwVal)){
				$(".pwResult").html("사용 가능한 비밀번호 입니다.");
				$(".pwResult").css("color", "blue");
				pwChk=true;
			}else{
				$(".pwResult").html("대소문자, 숫자로 입력해주세요.");
				$(".pwResult").css("color", "red");
				pwChk=false;
				rv= false;
			}
		});

		//새로운 비밀번호 확인
		$("#memberPwRe").on("keyup", function(){
			var pwVal = $("#memberPw").val();
			var pwreVal = $("#memberPwRe").val();
			if(originalPwVal != pwVal && pwVal == pwreVal){
				$(".pwReResult").html("비밀번호가 일치합니다.");
				$(".pwReResult").css("color", "blue");
				pwReChk=true;
				rv=true;
			}else if (originalPwVal != pwVal && pwVal != pwreVal){
				$(".pwReResult").html("비밀번호가 일치하지 않습니다.");
				$(".pwReResult").css("color", "red");
				pwReChk=false;
				rv= false;
			}else if (originalPwVal == pwVal && pwreVal == ""){
				rv= true;
			}
		});
		//전화번호 변경
		$("#phone").on("keyup", function(){
			var phoneVal = $("#phone").val();
			var phoneReg = /^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$/;
			if(phoneReg.test(phoneVal)){
				$(".phoneResult").html("사용가능한 번호입니다.");
				$(".phoneResult").css("color", "blue");
				phoneChk=true;
				rv=true;
			}else{
				$(".phoneResult").html("올바른 형식으로 입력해주세요 (01X-XXX-XXXX)");
				$(".phoneResult").css("color", "red");
				phoneChk=false;
				rv=false;
			}
		});
		//sms체크
		$("#smsChk").on("change",function(){
			if($(this).prop("checked")){
				$("#sms").val(1);
			}else {
				$("#sms").val(0);
			}
		});		
		$(".userinfo").submit(function(){
			if(rv==false){
				alert("입력정보를 다시 확인해주세요!");
				return rv= false;
			}
			return rv;
		})
		//지역변경
		$(".changeLocal2").click(function(){
			window.open('/searchLocal2', 'window', 'width=900px, height=300px');
		});
		$(".changeLocal3").click(function(){
			var local2stat = $(".localchange2").children().html();
			if(local2stat == "없음"){
				alert("지역2를 먼저 추가해주세요!!!");	
				self.close();
			} else {
				window.open('/searchLocal3', 'window', 'width=900px, height=300px');
			}
			
		});
		$(".localsubmit").click(function(){
			var province2 = $(".localchange2").children("span").first().html();
			var province3 = $(".localchange3").children("span").first().html();
			var sigungu2 = $(".localchange2").children("span").last().html();
			var sigungu3 = $(".localchange3").children("span").last().html();
			var sigunguNo2 = $(".localchange2").children("input").val();
			var sigunguNo3 = $(".localchange3").children("input").val();
			$(".local2badge").empty();
			$(".local2badge").html("<span>"+province2+"</span> <span>"+sigungu2+"</span>");
			$(".local3badge").empty();
			$(".local3badge").html("<span>"+province3+"</span> <span>"+sigungu3+"</span>");
			$(".changeLocal").modal('hide');
			if(typeof sigunguNo2 != "undefined"){
				$("#local2").val(sigunguNo2);
			}
			if(typeof sigunguNo3 != "undefined"){
				$("#local3").val(sigunguNo3);
			}
			console.log($("#local2").val());
			
		});
		$("#smsChk").on("change",function(){
			if($(this).prop("checked")){
				$(this).val(1);
			} else {
				$(this).val(0);
			}
		})
		
    });
</script>
<style>
	.content{
		width: 83%;
		height: 100%;	
	}
	.mypage{
		margin: 0;
		padding: 0;
		width: 100%;
		height: 200px;
		max-width: none;
	}
	.mypagemain>div{
		float: left;
	}
	.welcome-info{
		background-color: rgb(255, 251, 233);
		height: 210px;
		padding-top: 10px;
	}
	.welcome-info>div{
		padding-left: 30px;
	}
	.welcome-content{
		margin: 0 70px;
		font-weight: bolder;
	}
	.welcome-content td,th{
		padding-right: 10px;
	}
	.welcome-content h2,h4{
		font-weight: bolder;
	}
	.mypagelogo{
		text-decoration: none;
		color: rgb(230, 126, 34);
		font-size: 26px;
		margin-left: 50px;
		font-weight: bolder;
	}
	.mypagesidenav{
		height: 100%;
		width: 17%;
		background-color: transparent;
	}
	#mypagemenu h1{
		padding-top: 30px;
		font-size: 26px;
		color: #e95420;
		font-weight: bolder;
	}
	#mypagemenu{
		list-style-type: none;
		padding: 0;
		margin: 0;
		padding-left: 30px;
		overflow: hidden;
		display: flex;
		flex-direction: column;
		padding-bottom: 150px;
		background-color: transparent;
	}
	#mypagemenu>li{
		float: left;
		padding: 12px 0;
	}	

	#mypagemenu a{
		color: rgb(127, 127, 127);
		font-weight: bolder;
		text-decoration: none;
		font-size: 24px;
	}
	#mypagemenu a:hover{
		color: rgb(230, 126, 34);
	}
	.mypage-content-title{
		margin-top: 40px;
		font-size: 32px;
		font-weight: bolder;
		color: rgb(230, 126, 34);
	}
	.mypage-content>h4{
		color: rgb(158, 158, 158);
	}
	.mypage-content>h4>span:hover{
		cursor: pointer;
		color: black;
	}
	.mypagemain{
		margin: 0;
		max-width: none;
	}
	.userinfo>div{
		border-bottom: 1px solid rgb(192, 192, 192);
		padding: 20px 0;
		font-size: 20px;
	}
	.userinfo>div:first-child {
		border-top: 5px solid rgb(230, 126, 34);
	}
	.userinfo>div:last-child {
		border-bottom: none;
	}
	.userinfo h6, .text-muted{
		font-weight: bolder;
	}
	.col-sm-5{
		color: #000000;
	}
	.badge{
		font-size: 18px;
	}
	.myInfoButtons button, .myInfoButtons a{
		font-size: 20px;
		font-weight: bolder;
	}
	.myInfoButtons{
		margin-bottom: 60px;
	}
	.update{
		margin-right: 20px;
	}
	.form-group row{
		display: inline-flex;
		align-items: center;
	}
	.mannerface{
		font-weight: bolder;
	}
	.local-info{
		margin-left: 20px;
	}
	.profilePic{
		border-radius: 30%;
		overflow: hidden;
	}
	#profilePic{
		width: 100%;
		height: 100%;
		object-fit: cover;
	}
	.notification-cnt {
	    position: absolute;
		left: 57px;
		top: -1px;
	    z-index: -1;
	    height: 30px;
	    width: 30px;
	    line-height: 30px;
	    text-align: center;
	    background-color: #E95420;
	    border-radius: 25px;
	    display: inline-block;
	    font-size:15px;
	    color:white;
	}
	.notification {
    position: relative;
	}
</style>
</html>