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
								<div class="profilePic"><img src='/upload/member/${sessionScope.m.filePath }' id="profilePic" style="height: 100px; width: 100px;"></div>
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
				<li><a href="/mypage?reqPage=1&memberId=${sessionScope.m.memberId }" style="color: rgb(230, 126, 34);">거래 내역 조회</a></li>
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
				<li><a href="/myCommunity?reqPage=1&memberId=${sessionScope.m.memberId }">커뮤니티</a></li>
				<li><a href="/myUserInfo?local1=${sessionScope.m.local1 }&local2=${sessionScope.m.local2}&local3=${sessionScope.m.local3}">나의 정보</a></li>
			</ul>
		</div>
		<!-- 거래내역 -->
		<div class="mypage-content content column">
			<h1 class="mypage-content-title">거래내역 조회</h1><br>
			<h4><span class="transactionop">판매내역</span> / <span class="transactionop">구매내역</span></h4>
			<!-- 판매내역 -->
			<div class="transactiontbl">
				<c:choose>
					<c:when test="${empty list }">
					<h5>총 0개</h5>
					</c:when>
					<c:when test="${not empty list }">
					<h5>총 <span>${fn:length(list) }</span>개</h5>
					</c:when>
				</c:choose>
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col"></th>
							<th scope="col">상품 정보</th>
							<th scope="col">작성일</th>
							<th scope="col">거래가</th>
							<th scope="col">거래완료일</th>
							<th scope="col">상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="t" varStatus="i">
							<c:if test="${not empty list}">
								<tr>
									<td scope="row"><a href='/tradeView?tradeNo=${t.tradeNo }'><img src="/upload/trade/${t.filepath }" style="width: 150px; height: 150px;"></a></td>
									<td>
										<a href='/tradeView?tradeNo=${t.tradeNo }'></a><b>${t.tradeTitle }</b><br></a>
										<h6>${t.category }</h6>
									</td>
									<td><h5>${t.regDate }</h5></td>
									<td><h5>${t.price }</h5></td>
									<td>
										<h5>${t.tradeDate }</h5>
									</td>
									<td><h5>판매완료</h5></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				<div class="trans-total">
					<h4>총 판매금액 : 
						<c:choose>
							<c:when test="${not empty list }">
								<span>${totalPrice }</span>원</h4>
							</c:when>
							<c:otherwise>
								<span>0원</span></h4>
								<h5>아직 판매완료한 물건이 없어요.</h5>						
							</c:otherwise>
						</c:choose>
				</div>
				<!-- pagination -->
				<div class="page-navi">
					${pageNavi }
				</div>
			</div>
			<!-- 구매내역 -->
			<div class="transactiontbl" style="display:none;">
				<input type="hidden" class="memberId" value='${sessionScope.m.memberId }'>
				<h5 class="totalBuyCount">총 <span></span>개</h5>
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col"></th>
							<th scope="col">상품 정보</th>
							<th scope="col">카테고리</th>
							<th scope="col">거래가격</th>
							<th scope="col">거래완료일</th>
							<th scope="col">판매자ID</th>
							<th scope="col">리뷰</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="trans-total">
					<h4>총 구매금액 : <span></span>원</h4>
				</div>
				<!-- pagination -->
				<div class="page-navi">
					${pageNavi }
				</div>
			</div>
				<!-- 리뷰작성 -->
					<c:forEach items="${reviewDone }" var="r" varStatus="i">
						<c:if test="${not empty list}">
						<span class="reviewDone" style="display: none;" value='${r.transactionNo }'>
						</span>
						</c:if>
					</c:forEach>
					<div class="review modal fade" id="reviewModal" tabindex="-1" role="dialog" aria-hidden="true">
					  <div class="modal-dialog modal-lg" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" style="color:#e95420;">리뷰 작성하기</h5>
					        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true"></span>
					        </button>
					      </div>
					      <div class="modal-body">
					 		<form class="review" action="/writeReview" method="post">
								<fieldset>
									<table class="table" style="width:100%;">
										<tr class="table-warning">
											<th scope="col">작성자</th>
											<td>
												<input type="hidden" class="transactionNo" name="transactionNo">
												<input type="hidden" class="buyer" name="buyer" value="${sessionScope.m.memberId }">
												${sessionScope.m.memberId}
											</td>
										</tr>
										<tr class="table-warning">
											<th>상품 제목</th>
											<td colspan="3">
												<input type="text" class="tradeTitle" name="tradeTitle" readonly> 
											</td>
										</tr>
										<tr class="table-warning">
											<th>판매자</th>
											<td>
												<input type="text" class="seller" name="seller" readonly>
											</td>
										</tr>
										<tr class="recommend table-warning">
											<th>추천 / 비추천</th>
											<td>
												<input type="radio" class="choochun" name="choo" id="choo" style="display:none;" value="1">
												<label for="choo" data-toggle="tooltip" title="해당 판매자 추천!"><img src="/img/thumbs-up.png" style="width: 30px;"></label>
												<input type="radio" class="choochun" name="bichoo" id="bichoo" value="0" style="display:none;">
												<label for="bichoo" data-toggle="tooltip" title="해당 판매자를 비추합니다.."><img src="/img/thumbs-down.png" style="width: 30px;"></label>
												<input type="hidden" class="reviewLike" name="reviewLike">
											</td>
										</tr>
										<tr class="table-warning">
											<th>내용</th>
											<td colspan="3">
											<textarea id="reviewComment" name="reviewComment" class="form-control"></textarea>
											</td>
										</tr>
									</table>
					      </div>
					      <div class="modal-footer">
					        <input type="submit" class="reviewSubmit btn btn-primary" value="리뷰 등록" onclick="return checkValue();">
					        	</fieldset>
					        </form>
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					      </div>
					    </div>
					  </div>
					</div>
			</div>
		</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
<script>
	function checkValue(){
		var textarea = $("#reviewComment").val();
		var inputsu = $("input[type='radio']:checked").length;
		if(inputsu == 0){
			alert("추천/비추천을 선택해주세요!!!");
			return false;
		} 
		if(textarea==""){
			alert("내용을 작성해주세요.");
			return false;
		}
	}
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
		 
		 //추천비추천
		 $("input[type='radio']").click(function(){
			 $("input[type='radio']").next().children("img").removeClass("recSelected");
			 $(this).next().children("img").addClass("recSelected");
			 if($(this).index()== 0){ //추천 선택한 경우
				 $(".reviewLike").val(1);
			 }else {
				 $(".reviewLike").val(0); //비추
			 }
		 });
		 
		 
		 //구매내역 가져오기
		   $(".transactionop").last().click(function(){
	 			var memberId = $(".memberId").val();
	 			var reqPage = 1;
	 			var buyTable = $(".transactiontbl").last().find("tbody");
	 			$.ajax({
	 				url : "/myTradeBuy",
	 				type: "GET",
	 				data : {reqPage:reqPage, memberId:memberId},
	 				success : function(data){
	 						var list = data.list;
	 						var pageNavi = data.pageNavi;
	 						buyTable.empty();
	 						var total = 0;
	 						if(list.length > 0){
	 							for(var i=0; i<list.length; i++){
	 								if(list[i].reviewCount==0){
	 									var html="<tr><td><img src='/upload/trade/"+list[i].filePath+"' style='width:150px; height:150px;'></td><td><h5><a href='/tradeView?tradeNo="+list[i].tradeNo+"'>"+list[i].tradeTitle+"</a></h5></td><td><h5>"+list[i].category+"</h5></td><td><h5>"+list[i].transactionPrice+"원</h5></td><td><h5>"+list[i].transactionDate+"</h5></td><td><input type='hidden' value='"+list[i].tradeNo+"'><h5>"+list[i].seller+"</h5></td><td><button class='writeReview btn btn-outline-dark'()' data-toggle='modal' data-target='#reviewModal'>리뷰작성하기</button></td></tr>";
	 								} else {
	 									var html="<tr><td><img src='/upload/trade/"+list[i].filePath+"' style='width:150px; height:150px;'></td><td><h5><a href='/tradeView?tradeNo="+list[i].tradeNo+"'>"+list[i].tradeTitle+"</a></h5></td><td><h5>"+list[i].category+"</h5></td><td><h5>"+list[i].transactionPrice+"원</h5></td><td><h5>"+list[i].transactionDate+"</h5></td><td><input type='hidden' value='"+list[i].tradeNo+"'><h5>"+list[i].seller+"</h5></td><td><h5>작성완료</h5></td></tr>";
	 								}	
	 	 							buyTable.append(html);
	 	 							total= total + list[i].transactionPrice;
	 	 						}
	 	 						$(".page-navi").last().empty();
	 	 						$(".page-navi").last().html(pageNavi);
	 	 						$(".trans-total").last().find("span").html(total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	 						} else {
	 							$(".trans-total").last().find("span").html(total);	
	 						}
	 						$(".totalBuyCount").children("span").html(list.length);
	 				}
	 				});
	 		 });
		 
		   //리뷰작성 -> 중요!!!! ajax인 경우
		   $(document).on("click",".writeReview",function(){
			   	var tradeTitle = $(this).parent().prev().prev().prev().prev().prev().find("a").html();
				var seller = $(this).parent().prev().children("h5").html();
				var tradeNo = $(this).parent().prev().children("input").val();
				console.log(tradeTitle);
				console.log(seller);
				console.log(tradeNo);
				$(".tradeTitle").val(tradeTitle);
				$(".seller").val(seller);
				$(".transactionNo").val(tradeNo);
				
				
		   });
		   $(document).on("ready",".writeReview",function(){
				var tradeNo = $(this).parent().prev().children("input").val();
				var reviewDone = $("reviewDone").val();
				if(tradeNo == reviewDone){
					$(this).html("작성완료");
					$(this).prop('disabled','true');
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
	.transactiontbl>table th, td{
		padding-right: 100px;
	}
	.transactiontbl>table>tbody>tr:first-child {
		border-bottom: 1px solid rgb(158, 158, 158);
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
		left: 80%;
		margin-top: 40px;
	}
	.recommend label:first-of-type{
		margin-right: 50px;
	}
	.recommend label:hover{
		cursor: pointer;
	}
	.recSelected{
		filter: brightness(1.5);
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
	.tradeTitle, .seller{
		border: none;
		background-color: transparent;
		text-align: center;
	}
</style>
</html>