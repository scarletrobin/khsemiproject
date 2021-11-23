<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
	<jsp:include page ="/WEB-INF/views/common/header.jsp"/>
	<div class="mypage container">
		<div class="welcome-info">
			<div><a href="/mypage?reqPage=1&memberId=${sessionScope.m.memberId }" class="mypagelogo">MY PAGE</a></div>
			<table class="welcome-content">
				<tbody>
					<tr>
						<th rowspan="2"><img src="/img/orange.png" style="height: 100px;"></th>
						<td><h4 style="color: rgb(230, 126, 34);">오늘도 즐거운 오렌지생활 되세요 :)</h4></td>
					</tr>
					<tr>
						<td><h2 style="color: rgb(211, 84, 0);">[user01]님!</h2></td>
					</tr>
					<tr>
						<td></td>
						<td><h4>회원등급 : [일반회원]</h4></td>
						<th><h5 style="color:rgb(127, 127, 127);">가입일 : 2021.09.01</h5>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="mypagemain container">
		<!-- 사이드메뉴 -->
		<div class="mypagesidenav side">
			<ul id="mypagemenu">
				<li><h1>나의 오렌지 활동</h1></li>
				<li><a href="#">거래 내역 조회</a></li>
				<li><a href="#">내가 올린 판매글</a></li>
				<li><a href="#">마이 쪽지함</a></li>
				<li><a href="#">QnA 문의</a></li>
				<li><a href="#">관심물건</a></li>
				<li><a href="#">커뮤니티</a></li>
				<li><a href="#">나의 정보</a></li>
			</ul>
		</div>
		<!-- 거래내역 -->
		<div class="mypage-content content">
			<h1 class="mypage-content-title">거래내역 조회</h1><br>
			<h5>총 2개</h5>
			<h4><span>판매내역</span> / <span>구매내역</span></h4>
			<div>
				<table id="transactiontbl">
					<tbody>
						<tr>
							<th></th>
							<th>상품 정보</th>
							<th>작성일</th>
							<th>거래번호</th>
							<th>거래가/지역</th>
							<th>거래완료일</th>
						</tr>
						<tr>
							<td><img src="/img/orange.png" style="width: 150px;"></td>
							<td>
								<b>상품명</b>
							</td>
							<td><h5>2021.10.01</h5></td>
							<td><h5>202100100021</h5></td>
							<td>
								<h6>거래가</h6>
								<h6>거래지역</h6>
							</td>
							<td><h5>2021.10.11</h5></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
<style>
	.content{
		width: 80%;
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
		height: 200px;
		padding-top: 10px;
	}
	.welcome-content{
		margin: 0 70px;
		font-weight: bolder;
	}
	.welcome-content h2,h4{
		font-weight: bolder;
	}
	.mypagelogo{
		text-decoration: none;
		color: rgb(230, 126, 34);
		font-size: 26px;
		margin-left: 40px;
		font-weight: bolder;
	}
	.mypagesidenav{
		height: 100%;
		width: 20%;
		background-color: #ffffff;
	}
	#mypagemenu h1{
		padding-top: 30px;
		font-size: 28px;
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
		background-color: #ffffff;
	}
	#mypagemenu>li{
		float: left;
		font-size: 24px;
		padding: 12px 0;
	}
	#mypagemenu a{
		color: rgb(127, 127, 127);
		font-weight: bolder;
		text-decoration: none;
	}
	#mypagemenu a:hover{
		color: rgb(230, 126, 34);
	}
	.mypage-content-title{
		margin-top: 42px;
		font-size: 30px;
		font-weight: bolder;
		color: rgb(230, 126, 34);
	}
	.mypage-content{
		padding-left: 60px;
	}
	.mypage-content>h4{
		color: rgb(158, 158, 158);
	}
	.mypagemain{
		margin: 0;
	}
</style>
</html>