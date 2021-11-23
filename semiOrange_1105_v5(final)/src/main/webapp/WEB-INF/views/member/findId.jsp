<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="./img/favicon.ico"/>
<link rel="stylesheet" href="/css/member/findId.css">
<title>아이디 찾기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div class="findid-wrap">
			<div class="findid-tab">
				<a href="../findIdFrm">아이디 찾기</a>
			</div>
			<div class="findid-tab">
				<a href="../findPwFrm">비밀번호 찾기</a>
			</div>
			<form action="/findId" method="post">
			    <table class="findid-table">
			        <tr>
			            <th colspan="2" class="findid-logo">
							<img src="./img/orange.png" style="width:50px; height:50px;">아이디 찾기
						</th>
			        </tr>
			        <tr>
						<td>
							<h4>이름</h4>
			            	<input type="text" name="memberName" id="memberName" placeholder="이름을 입력해주세요" class="findid-input">
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
			            	<input type="text" id="email" name="email" placeholder="이메일을 입력해주세요" class="findid-input">
			        		<p class="expResult">　</p>
			            </td>
			        </tr>
			        <tr>
			        	<td colspan="2">
			        	</td>
			        </tr>
			        <tr>
			            <th colspan="2" class="findid-button">
							<input type="submit" value="아이디 찾기" id="findid" ><!-- onclick="return checkFindId();" -->
						</th>
			        </tr>
			    </table>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="/js/member/findId.js"></script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>