<%@page import="java.text.NumberFormat"%>
<%@page import="trade.model.vo.Trade"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container container2">
		<br>
		<h4 class="text-center">중고거래 지역매물</h4>
		<br>
		<hr>
		<div class="row row-cols-1 row-cols-md-4 g-4">
		<c:choose>
			<c:when test="${empty list }">
				<div class="text-center empty-list">
					매물이 없습니다.
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="t" varStatus="i">
					<div class="col cursor-img" onclick="location.href='/tradeView?tradeNo=${t.getTradeNo() }';">
					<div class="card h-100 card-img">
					<img src="/upload/trade/${t.getFilepath() }" class="card-img-top img-thumbnail img-fluid">
						<div class="card-body">
							<h6 class="card-title overflow-text">${t.getTradeTitle() }</h6>
							<p class="card-text" style="color: #f95420"><fmt:formatNumber value="${t.getPrice() }" pattern="#,###" />원</p>
							<c:choose>
								<c:when test="${t.getSigunguDo() eq t.getSigunguName() }">
									<p class="card-text trade-add"><span>거래지역: </span>${t.getSigunguDo() }</p>
								</c:when>
								<c:otherwise>
									<p class="card-text trade-add"><span>거래지역: </span>${t.getSigunguDo() } ${t.getSigunguName() }</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				</c:forEach>
			</c:otherwise>	
		</c:choose>
		</div>
		<hr>
		<br>
		<div id="pageNavi">${pageNavi }</div><br><br>
	</div>	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>