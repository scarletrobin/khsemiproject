<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오렌지마켓</title>
<style>
.main-m {
	display: flex;
	
}
.text-m {
	margin: auto;
}
img {
  max-width: 100%;
}
.content>ul {
    list-style-type: none;
    padding: 0;
    overflow: hidden;
}
.product {
    float: left;
    width: calc(100%/4);
    padding: 20px;
    box-sizing: border-box; 
}
.product>a {
	padding-top: 15px;
    text-decoration: none;
    display: block;
    width: 250px;
    height: 320px;
}
.img-box {
    width: 200px;
    height: 200px;
    margin: 0 auto;
}
.img-box>img {
    width: 100%;
    height: 100%;
}
.info {
    width: 70%;
    margin: 0 auto;
}
.f-price-area>span {
	font-size:18px; 
	font-weight:bolder;
    color: #f95420;
}
.flavor{
	margin-top: 10px;
	color: #424242;
}
.f-addr{
	font-size: 14px;
	color: #616161;
}
.product>a:hover {
    box-shadow: 0 2px 10px 0 rgb(0,0,0,0.2);
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<div class="main-m">
			<div>
				<img src="/img/cmain.jpg" style="width: 600px; height: 400px;">
			</div>
			<div class="text-m">
				<h1>
					우리 동네 <br> 중고 직거래 마켓
				</h1>
				<p style="color: #616161; font-size: 18px;">
					구매자 뿐만 아니라, 판매자들도 만족하고 이용할 수 있는 <br> 오렌지마켓 서비스
				</p>
			</div>
		</div>

		<div class="main">
			<div class="title" style="text-align: center;">
				<br>
				<br>
				<br>
				<h2>중고거래 인기매물</h2>
			</div>
			<br>
			<div class="content">
				<ul>
				<c:forEach items="${list }" var="t" varStatus="i">
					<li class="product">
					<a href="/tradeView?tradeNo=${t.getTradeNo() }">
						<div class="img-box">
							<img src="/upload/trade/${t.getFilepath() }">
						</div>
						<div class="info">
							<div class="flavor overflow-text">${t.getTradeTitle() }</div>
							<div class="f-price-area">
								<span class="f-price"><fmt:formatNumber value="${t.getPrice() }" pattern="#,###" />원</span>
							</div>
							<c:choose>
								<c:when test="${t.getSigunguDo() eq t.getSigunguName() }">
									<div class="f-addr">${t.getSigunguDo() }</div>
								</c:when>
								<c:otherwise>
									<div class="f-addr">${t.getSigunguDo() } ${t.getSigunguName() }</div>
								</c:otherwise>
							</c:choose>
						</div>
					</a>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>