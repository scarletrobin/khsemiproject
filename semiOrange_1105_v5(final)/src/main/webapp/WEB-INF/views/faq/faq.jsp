<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=2yu9yglnhu&submodules=geocoder"></script>
<meta charset="UTF-8">
<title>자주 하는 질문</title>
</head>
<style>
	#choice th{
   		border-bottom: 5px solid transparent;
   		transition : 0.5s;
	}
	
	#choice th:hover{
		border-bottom: 5px solid #e95420;
	}
	#question{
		text-align: center;
	    width: 900px;
	    margin: 80px auto;
	    height: 200px;
	    line-height: 100px;
	}
	#question th{
		border-top : 5px solid transparent;
	}
	.cs{
		width: 1000px;
		margin: 50px auto;
	}
	.cs h3{
		font-weight: bold;
		color: #e95420;
	}
	.cs p{
		margin: 20px auto;
	}
	.cs img,.rules img{
		width: 35px;
		height: 35px;
	}
	.rules{
		width: 900px;
		margin: 30px auto;
		background-color: #eceff1;
		height: 1000px;
		border-radius: 10px;
	}
	.rules>div{
		overflow: hidden;
	}
	.rules>div>div{
		float: left;
		width: 50%;
		padding: 40px;
	}
	#ruleslist{
		font-size: 20px;
	}
	#ruleslist>p{
		margin-bottom: 50px;
	}
	#blist{
		font-size: 20px;
		padding: 0;
		padding-top: 80px;
		padding-right: 20px;
	}
	#blist td{
		text-align: center;
	}
	.accord{
		width: 1000px;
		margin: 50px auto;
	}
</style>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<table class="table" id="choice" style="text-align: center; width: 800px; margin:0 auto; margin-bottom: 16px;">
			<tr class="table-active">
				<th class="btn-light chm">고객센터</th>
				<th class="btn-light chm">운영정책</th>
				<th class="btn-light chm" style="width:270px; border-bottom: 5px solid #e95420; ">자주묻는질문</th>
			</tr>
		</table>
		<table class="table" id="question">
			<tr class="table-secondary">
				<th class="q">회원/계정</th>
				<th class="q">거래사기</th>
				<th class="q">거래매너</th>
			</tr>
			<tr class="table-secondary">
				<th class="q">거래품목</th>
				<th class="q">기타</th>
				<th class="q">이용방법</th>
			</tr>
		</table>
		<div class="accord">
		<jsp:include page="/WEB-INF/views/faq/accordian.jsp"/>
		</div>
		<div class="rules" style="display: none;">
			<div style="border-bottom: 1px solid black;">
				<div>
					<h4 style="font-weight: bold;">오렌지마켓운영정책 <img src="/img/orange_logo5-removebg-preview.png"></h4>
					<p class="text-muted">이용제재 / 블랙리스트 제도</p>
				</div>
				<div id="ruleslist">
					<p><i class="bi bi-x-circle-fill"></i> 금지품목 거래시 제재를 받을 수 있어요</p>
					<p><i class="bi bi-tags-fill"></i> 적절하지 않은 상품명,이미지, 가격등록시에 제재를 받을 수 있어요.</p>
					<p><i class="bi bi-chat-dots-fill"></i> 외부 채널로의 연락유도, 타 사이트로의 결제 유도시 제재받을 수 있어요</p>
					<p><i class="bi bi-hand-thumbs-down-fill"></i> 욕설,성희롱등 비매너 행위를 상대를 불쾌하게 해요.</p>
				</div>
			</div>
			<div>
				<div>
					<h4 style="font-weight: bold;">블랙리스트제도<img src="/img/orange_logo5-removebg-preview.png"></h4>
				</div>
				<div id="blist">
					<table class="table" style="height: 300px;">
						<tr class="table-danger">
							<th colspan="2">위의 사항을 위배하거나, 차단&신고 횟수가 누적되면 이용을 제한합니다.</th>
						</tr>
						<tr><td>3회이상 정책위반</td><td>회원탈퇴</td></tr>
						<tr><td>신고횟수 5회누적</td><td>블랙리스트 등록</td></tr>
						<tr><td>신고횟수 10회누적</td><td>회원차단</td></tr>
					</table>
					<br>
					<p class="text-muted" style="font-size: 16px;">* 자세한 문의사항은 qna를 이용해주세요</p>
				</div>
			</div>
		</div>
		<div class="cs" style="display:none;">
			<h3>고객센터 위치 <img src="/img/orange_logo5-removebg-preview.png"></h3>
			<br>
			<div id="map" style="width: 100%; height: 500px;"></div>
			<p class="text-muted">* 내방시, 상담가능시간은 평일 오전 10시부터 오후 6시까지입니다. 점심시간 12시부터 오후 1시까지는 제외이며, 주말과 공휴일은 고객센터 운영이 되지 않고 있사오니 이점 참고 부탁 드립니다.</p>
			<p class="text-info">주차공간이 협소하오니, 대중교통이용을 권장드립니다.</p>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		$("#question th").hover(
			function(){
				$(this).addClass('btn-primary');
			},
			function(){
				$(this).removeClass('btn-primary');
			}
		);		
	});		
	
	var map = new naver.maps.Map("map",{
		center : new naver.maps.LatLng(37.533837,126.896836),
		zoom : 17,
		zoomControl : true,
		zoomControlOptions : {
			position : naver.maps.Position.TOP_RIGHT,
			style : naver.maps.ZoomControlStyle.SMALL
		}
	});
	var marker = new naver.maps.Marker({
		position : new naver.maps.LatLng(37.533837,126.896836),
		//매개변수 없이 , 위에서 선언한 새로운 객체이름 으로 설정
		map : map
	});
	
	var contentString = [
		"<div class='iw_inner'>",
		" 	<h3>KH오렌지센터</h3>",
		"	<p>서울시 영등포구 선유동2로 57 이레빌딩 19F,20F</p>",
		"</div>"
	].join("");

	var infoWindow = new naver.maps.InfoWindow();
	
	naver.maps.Event.addListener(marker,"click",function(e){
			if(infoWindow.getMap()){//지도에 켜져있으면?존재하면?
				infoWindow.close();
			}else{
				infoWindow = new naver.maps.InfoWindow({
					content : contentString
				});
				//map이라는 위치 marker위에
				infoWindow.open(map,marker);
			}
	});	

</script>
<script type="text/javascript">
	$(".chm").click(function(){
		var index = $(".chm").index(this);
		$("#choice th").css('border-bottom','5px solid transparent');
		$("#question").css('display','none');
		$(".accord").css('display','none');
		$(".rules").css('display','none');
		$(".cs").css('display','none');
		if(index == 0){
			$(this).css('border-bottom','5px solid #e95420');
			$(".cs").removeAttr("style");
			map.setSize(new naver.maps.Size($("#map").css("width"),$("#map").css("height")));
		}else if(index == 1){
			$(this).css('border-bottom','5px solid #e95420');
			$(".rules").removeAttr("style");
		}else if(index == 2){
			$(this).css('border-bottom','5px solid #e95420');
			$("#question").removeAttr("style");
			$(".accord").removeAttr("style");
		}
	})
	$(".q").click(function(){
		var index = $(".q").index(this);
		$(".accordion").css("display","none");
		$("#question th").css("border-top","5px solid transparent");
		$(".accordion").eq(index).css("border-top","5px solid #e95420");
		$(".accordion").eq(index).removeAttr("style");
	});
</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>