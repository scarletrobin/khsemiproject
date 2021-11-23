<%@page import="report.model.vo.AdminCount"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.top{
	background-color :#FFFDC6;
	margin : 0px;
	height:220px;
	}
	.profile{
	width:120px;
	heigh:120px;
	border-radius:50px;
	margin-left: 180px;
	background: white;
	}
	.leftmenu>a{
		height:70px;
		font-size: 20px;
		font-weight: 600;
		color: gray;
	}
	#leftnavi>li:last-child>a{
		color: #f57c00;
		font-size: 22px;
	}
	.mypage{
	color: #f57c00;
	font-size: 30px;
	font-weight:600;
	padding-top:10px;
	margin-left: 40px;
	}
	.head>div{
	display: inline-block;
	vertical-align: middle;
	}
    .content{
       flex-grow: 1;
    }
    .top>.container{
    	margin-top: 0px;
    	
    }
    #row{
    	margin: 30px auto 100px;
    }
    #leftnavi{
    	text-align:left;
    	margin: 0;
    }
    .headtext{
    	margin-left: 20px;
    }
    .headtext>div:first-child{
    	color: #f57c00;
    	font-weight: 600;
    }
    .headtext>div:nth-child(2){
    	color: #e95420;
    	font-weight: 600;
    	font-size: 37px;
    }
    .headtext>span{
    	font-weight: 600;
    	line-height: 25px;
    }
    .headtext>span a{
    	text-decoration: none;
    	color: green;
    }
    p{
    	color: #f57c00;
    	font-weight: 600;
    	font-size: 30px
    }
    #contentright{
    	margin-top: 0px;
    }
    #table{
		border-top: 3px solid #f57c00;
	}
    #textright{
    margin-left: 30px;
    line-height: 27px;
    color: #f57c00;
    font-weight: 600;
    }
    #mygo{
		text-decoration: none;
	}
	/*태경css  */
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
</style>
</head>
<body>
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<div class="content" >
				<div class="top" style="back">
					<div class="container">
						<div class="mypage">MY PAGE</div>
						<div class="head">
							<div>
							<c:choose>
							<c:when test="${empty sessionScope.m.filePath }">
							<img class="profile" src="/img/orange.png" >
							</c:when>
							<c:otherwise>
							<img class="profile" src="/upload/profile/${sessionScope.m.filePath }" >
							</c:otherwise>
							</c:choose>
							</div>
							<div class="headtext">
								<div>오늘도 즐거운 오렌지 생활되세요:)</div>
								<div>관리자  <a href="/adminMy" id="mygo">[${sessionScope.m.memberName }]</a>님!</div>
								<span><a href="/chart?num=1">총 가입자수</a>:${sessionScope.ac.alljoin } </span>
								<span>오늘 가입자수 : ${sessionScope.ac.newjoin}</span>
								<br>
								<span>총 글수 : ${sessionScope.ac.alltrade+ac.allfree }</span>
								<span>오늘 새 글수 : ${sessionScope.ac.newfree+ac.newtrade }</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row container" id="row">
					<div class="col-lg-2">
					  <ul class="nav flex-column" id="leftnavi">
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/adminPage?reqPage=1">전체회원관리</a>
					    </li>
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/boardAdmin">게시판/문의관리</a>
					    </li>
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/reportListMore?reqPage=1">신고리스트관리</a>
					    </li>
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/adminMy" id="adminMy">나의정보</a>
					    </li>
					  </ul>
					</div>
					<div class="col-lg-9">
						<div class="mypage-content content column">
			<p id="p"class="mypage-content-title">나의 회원정보</p><br>
			<!-- 회원정보목록 -->
			<div class="mypage-info">
				<form class="userinfo" action="/updateMember" method="post" enctype="multipart/form-data">
					<div class="form-group row">
							<label for="profile" class="col-sm-2 col-form-label"><b>프로필</b></label>
							<div class="col-sm-5">
								<img src="${sessionScope.m.filePath }" id="pic" name="pic" class="pic" style="width:150px;">
								<small id="profileHelp" class="form-text text-muted">프로필 사진을 설정해보세요.</small>
							</div>
							<div class="col-sm-5">
								<input type="file" class="profile" name="profile" id="profile" style="display:none;">
								<label for="profile" class="changeprofile btn btn-outline-dark">사진 변경</label>
							</div>
					</div>
					<div class="form-group row">
							<label for="memberId" class="col-sm-2 col-form-label"><b>아이디</b></label>
							<div class="col-sm-5">
								<input type="text" class="form-control-plaintext" id="memberId" name="memberId" 
								readonly="readonly" value="${sessionScope.m.memberId }">
								<input type="hidden" class="memberNo" id="memberNo" name="memberNo" value="${sessionScope.m.memberNo }">
							</div>
							<div class="col-sm-5">
								<small id="enrollDate" class="form-text text-muted">가입일: ${sessionScope.m.enrollDate }</small>
							</div>
					</div>
					<div class="form-group row">
							<label for="memberLevel" class="col-sm-2 col-form-label"><b>회원등급</b></label>
							<div class="col-sm-5"><input type="text" class="form-control-plaintext" id="memberLevel" name="memberLevel"
							readonly="readonly" 
							value="<c:choose><c:when test="${sessionScope.m.memberLevel eq 1 }">[일반회원]</c:when><c:when test="${sessionScope.m.memberLevel eq 9 }">[관리자]</c:when></c:choose>"></div>
							<div class="col-sm-5">
							</div>
					</div>
					<div class="form-group row">
							<label for="manner" class="col-sm-2 col-form-label"><b>매너날씨</b></label>
							<div class="col-sm-5">
							<input type="hidden" id="manner" name="manner" value="${sessionScope.m.manner }">
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
							value="${sessionScope.m.memberPw }"></div>
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
							readonly="readonly" value="${sessionScope.m.email }"></div>
							<div class="col-sm-5">
							</div>
					</div>
					<div class="form-group row">
							<label for="phone" class="col-sm-2 col-form-label"><b>전화번호</b></label>
							<div class="col-sm-5"><input type="text" class="form-control" id="phone" name="phone"
							value="${sessionScope.m.phone }"></div>
							<div class="col-sm-5">
							<h6 class="phoneResult"></h6>
							</div>
					</div>
					<div class="form-group row">
							<label for="local" class="col-sm-2 col-form-label"><b>관심지역</b></label>
							<div class="col-sm-5">
								<span class="badge bg-primary">${sessionScope.m.sigungu }</span>
								<c:choose>
									<c:when test="${not empty sessionScope.m.local2 && empty sessionScope.m.local3 }">
									<span class="badge bg-info">${sessionScope.m.local2 }</span>
									</c:when>
									<c:when test="${not empty sessionScope.m.local2 && not empty sessionScope.m.local3 }">
									<span class="badge bg-info">${sessionScope.m.local2 }</span>
									<span class="badge bg-info">${sessionScope.m.local3 }</span>
									</c:when>
								</c:choose>
								<input type="hidden" name="local1" id="local1">
								<input type="hidden" name="local2" id="local2">
								<input type="hidden" name="local3" id="local3">
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
									<c:when test="${sessionScope.m.sms eq 1 }">
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
							<button type="submit" class="update btn btn-outline-primary">정보수정</button>
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
					        	<div class="col-sm-3">
					        	<c:choose>
					        		<c:when test="${not empty sessionScope.m.local2 }">
					        		<span>${sessionScope.m.local2 }</span>
					        		</c:when>
					        		<c:otherwise>
					        		<span>없음</span>
					        		</c:otherwise>
					        	</c:choose>
					        	</div>
					        	<div class="col-sm-3">
					        	<input type="button" class="changeLocal2 btn btn-outline-primary" data-toggle="modal-popover" data-target="changeLocal2" value="지역2 추가/변경">
					        	</div>
					        </div>
					         <div class="row">
					        	<div class="col-sm-3">관심지역3</div>
					        	<div class="col-sm-3">
					        	<c:choose>
					        	    <c:when test="${not empty sessionScope.m.local3 }">
					        		<span>${sessionScope.m.local3 }</span>
					        		</c:when>
					        		<c:otherwise>
					        		<span>없음</span>
					        		</c:otherwise>
					        	</c:choose>
					        	</div>
					        	<div class="col-sm-3">
					        	<input type="button" class="changeLocal2 btn btn-outline-primary" value="지역3 추가/변경">
					        	</div>
					        </div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-primary">등록 완료</button>
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					      </div>
					    </div>
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
		//비밀번호 변경
		$("#memberPw").on("keyup", function(){
			var pwReg = /^[a-zA-Z0-9]{3,11}$/;
			var pwVal = $("#memberPw").val();
			if(pwReg.test(pwVal)){
				$(".pwResult").html("사용 가능한 비밀번호 입니다.");
				$(".pwResult").css("color", "blue");
				pwChk=true;
				return true;
			}else{
				$(".pwResult").html("대소문자, 숫자로 입력해주세요.");
				$(".pwResult").css("color", "red");
				return false;
			}
		});

		//새로운 비밀번호 확인
		$("#memberPwRe").on("keyup", function(){
			var pwVal = $("#memberPw").val();
			var pwreVal = $("#memberPwRe").val();
			if(pwVal == pwreVal){
				$(".pwReResult").html("비밀번호가 일치합니다.");
				$(".pwReResult").css("color", "blue");
				pwReChk=true;
				return true;
			}else{
				$(".pwReResult").html("비밀번호가 일치하지 않습니다.");
				$(".pwReResult").css("color", "red");
				return false;
			}
		});
		//전화번호 변경
		$("#phone").on("keyup", function(){
			var phoneReg = /^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$/;
			var phoneVal = $("#phone").val();
			if(phoneReg.test(phoneVal)){
				$(".phoneResult").html("사용가능한 번호입니다.");
				$(".phoneResult").css("color", "blue");
				phoneChk=true;
				return true;
			}else{
				$(".phoneResult").html("올바른 형식으로 입력해주세요 (01X-XXX-XXXX)");
				$(".phoneResult").css("color", "red");
				return false;
			}
		});
		$(".changeLocal2").click(function(){
			window.open('/searchLocal', 'window', 'width=900px, height=300px');
		});
    });
</script>
</html>