<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<link rel="stylesheet" href="/css/member/login.css">
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div class="login-wrapper">
			<div class="login-logo">
			<img src="./img/orange.png" style="width:50px; height:50px;"><span>로그인</span>				
			</div>
			<form action="/login" method="post" class="loginform">
				<div class="login-id">
				<label for="memberId">　아이디</label>
				<input type="text" name="memberId" id="memberId" placeholder="아이디를 입력해주세요">
				</div>	
				<div class="login-pw">
				<label for="memberPw">비밀번호</label>
				<input type="password" name="memberPw" id="memberPw" placeholder="비밀번호를 입력해주세요">
				</div>
				<div>
				<a href="/findIdFrm">아이디 찾기</a> / <a href="/findPwFrm">비밀번호 찾기</a>
				</div>
				<div>
				<input type="submit" value="로그인" class="login-button">
				</div>
				<div class="joindiv">
					<a href="/joinFrm">회원가입</a>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>