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
						<th><h5 style="color: rgb(127, 127, 127);">가입일 : ${sessionScope.m.enrollDate }</h5>
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
				<li><a href="/myCommunity?reqPage=1&memberId=${sessionScope.m.memberId }" style="color: rgb(230, 126, 34);">커뮤니티</a></li>
				<li><a href="/myUserInfo?local1=${sessionScope.m.local1 }&local2=${sessionScope.m.local2}&local3=${sessionScope.m.local3}">나의 정보</a></li>
			</ul>
		</div>
		<!-- 내 커뮤니티 -->
		<div class="mypage-content content column">
			<h1 class="mypage-content-title">커뮤니티</h1><br>
			<h4><span class="transactionop">게시물</span> / <span class="transactionop">댓글</span></h4>
			<!-- 게시물 -->
			<div class="transactiontbl">
				<h5>총 ${fn:length(list) }개</h5>
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">제목</th>
							<th scope="col">작성일</th>
							<th scope="col">조회수</th>
							<th scope="col">댓글수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="f" varStatus="i">
						<c:if test="${not empty list}">
						<tr>
							<td>
								<h5><a href='/freeView?freeNo=${f.freeNo }'>
								${f.freeTitle }
								</a></h5>
							</td>
							<td>
								<h5>${f.regDate }</h5>
							</td>
							<td>
							<h5>${f.readCount }</h5>
							</td>
							<td>
								<h5 class="commentcount">${f.ncCount }</h5>
							</td>
						</tr>
						</c:if>
						</c:forEach>
					</tbody>
				</table>
				<!-- pagination -->
				<div class="page-navi">
					${pageNavi }
				</div>
			</div>
			<!-- 댓글 -->
			<div class="transactiontbl" style="display:none;">
				<input type="hidden" class="memberId" value='${sessionScope.m.memberId }'>
				<h5>총 <span class="totalCommentCount"></span>개</h5>
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">내용</th>
							<th scope="col">게시판 종류</th>
							<th scope="col">게시물 번호</th>
							<th scope="col">댓글 작성일</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<!-- pagination -->
				<div class="page-navi"></div>
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
		
		 //댓글수 가져오기
         $(".transactionop").last().click(function(){
  			var memberId = $(".memberId").val();
  			var reqPage = 1;
  			var commentTable = $(".transactiontbl").last().find("tbody");
  			$.ajax({
  				url : "/myCommunityComment",
  				type: "GET",
  				data : {reqPage:reqPage, memberId:memberId},
  				success : function(data){
  						var list = data.list;
  						console.log(list);
  						var pageNavi = data.pageNavi;
  						commentTable.empty();
  						if(typeof list != "undefined"){
  							for(var i=0; i<list.length; i++){
  								var type="";
  								if(list[i].boardType == 1){
  	 								type= "자유";
  	 							}else if (list[i].boardType == 2){
  	 							 	type= "거래";
  	 							} else if (list[i].boardType == 3){
  	 								type="QnA";
  	 							} else { 
  	 								type="공지";
  	 							}
  	 							var html="<tr><td><h5>"+list[i].commentContent+"</h5></td><td><h5>"+type+"</h5></td><td><h5>"+list[i].boardNo+"</h5></td><td><h5>"+list[i].regDate+"</h5></td></tr>";
  	 							commentTable.append(html);
  	 						}
  	 						$(".page-navi").last().empty();
  	 						$(".page-navi").last().html(pageNavi);
  	 						$(".totalCommentCount").html(list.length);
  						} else {
  							$(".totalCommentCount").html(0);
  						}
  				}
  				});
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
		text-align: center;
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
	.page-navi{
		margin: 50px 0;
		margin-left: 40%;
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