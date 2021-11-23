<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<link rel="stylesheet" href="/css/member/findPw.css">
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div class="findpw-wrap">
			<div class="findpw-tab">
				<a href="../findIdFrm">아이디 찾기</a>
			</div>
			<div class="findpw-tab">
				<a href="../findPwFrm">비밀번호 찾기</a>
			</div>
			<form action="/findPw" method="post">
			    <table class="findpw-table">
			        <tr>
			            <th colspan="2" class="findpw-logo">
							<img src="./img/orange.png" style="width:50px; height:50px;">비밀번호 찾기
						</th>
			        </tr>

			        <tr>
						<td>
							<h4>아이디</h4>
			            	<input type="text" name="memberId" id="memberId" placeholder="아이디를 입력해주세요" class="findpw-input">
			        		<p class="expResult">　</p>
			            </td>
			        </tr>
			        <tr>
			        	<td colspan="2">
			        	</td>
			        </tr>
			        <tr>
						<td>
							<h4>이메일 주소</h4>
			            	<input type="text" id="email" name="email" placeholder="이메일을 입력해주세요" class="findpw-input">
			        		<p class="expResult">　</p>
			            </td>
			        </tr>
			        <tr>
			        	<td colspan="2">
			        	</td>
			        </tr>
			        <tr>
			            <th colspan="2" class="findpw-button">
							<input type="submit" value="비밀번호 찾기" id="findpw" onclick="return checkFindPw();" >
						</th>
			        </tr>
			    </table>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="/js/member/findPw.js"></script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>