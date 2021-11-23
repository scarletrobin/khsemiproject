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
	<%@include file="/WEB-INF/views/common/header.jsp" %>
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
							</c:when>
							<c:when test="${sessionScope.m.memberLevel eq 2 }">
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
				<%-- <li><a href="/myWishlist?reqPage=1&memberId=${sessionScope.m.memberId }" style="color: rgb(230, 126, 34);">관심물건</a></li> --%>
				<li><a href="/myCommunity?reqPage=1&memberId=${sessionScope.m.memberId }">커뮤니티</a></li>
				<li><a href="/myUserInfo?local1=${sessionScope.m.local1 }&local2=${sessionScope.m.local2}&local3=${sessionScope.m.local3}">나의 정보</a></li>
			</ul>
		</div>
		<!-- 내 관심물건 리스트 -->
		<div class="mypage-content content column">
			<h1 class="mypage-content-title">내 관심물건</h1><br>
			<!-- 답변대기중 -->
			<div class="transactiontbl">
				<h5>총 2개</h5>
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col"></th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">거래가/지역</th>
							<th scope="col">상태</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="checkbox" class="chk" style="width:50px;"></td>
							<td>
								<h5><a href="#">
								<b>상품 제목</b><br>
								상품에 대한 설명내용
								</a></h5>
							</td>
							<td>
								<h5>판매자 ID</h5>
							</td>
							<td>
							<h5>20,000원</h5>
							<h5>강남구</h5>
							</td>
							<td>
								<h5 class="itemStat">판매중</h5>
							</td>
						</tr>
						<!-- 판매완료 상품인 경우 -->
						<tr class="table-secondary">
							<td><input type="checkbox" class="chk" style="width:50px;"></td>
							<td>
								<h5><a href="#">
								<b>상품 제목</b><br>
								상품에 대한 설명내용
								</a></h5>
							</td>
							<td>
								<h5>판매자 ID</h5>
							</td>
							<td>
							<h5>12,000원</h5>
							<h5>분당구</h5>
							</td>
							<td>
								<h5 class="itemStat">판매완료</h5>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- pagination -->
				<div class="pagination"></div>
				<div class="delButton">
					<span>선택한 물건을 관심목록에서 </span><button class="delete btn btn-dark btn-sm" id="delete" name="delete">삭제하기</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
<script>
	$(function(){
		 $(".transactionop").first().addClass("activeTab");
		 $(".transactiontbl").first().show();
		 /* 탭 메뉴 전환 */
         $(".transactionop").click(function(){
        	$(".transactionop").removeClass("activeTab");
        	$(".transactiontbl").hide();
        	$(this).addClass("activeTab");
        	if($(this).index() == 0){
        		$(".transactiontbl").first().show();
        	} else {
        		$(".transactiontbl").eq(1).show();
        	}
         });
		
         
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
	.transactiontbl>table{
		margin-top: 30px;
		border-top: 5px solid rgb(230, 126, 34);
		background-color: #ffffff;
	}
	.transactiontbl>table th{
		font-size: 22px;
	}
	.transactiontbl>table>tbody>*{
		vertical-align: middle;
	}
	.transactiontbl h6,b{
		font-size: 20px;
	}
	.activeTab{
		color: #000000;
	}
	.trans-total span{
		color: #e95420;
	}
	.trans-total{
		position: absolute;
		text-align: right;
		left: 68%;
	}
	label[for="delete"]:hover{
		cursor: pointer;
	}
	.delButton{
		color: rgb(52, 52, 52);
		font-size: 20px;
		display: inline-flex;
		align-items:center;
		margin-left: 76%;
	}
	.delete{
		margin: 0px 10px;
		font-size: 18px;
	}
	.chk{
		width: 50px;
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