<%@page import="java.text.NumberFormat"%>
<%@page import="trade.model.vo.Trade"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container container2">
		<br>
		<h4 class="text-center">판매내역</h4>
		<br> <br>
		<table class="table text-center">
			<thead>
				<tr>
					<th class="col-md-2">사진</th>
					<th class="col-md-2">글 제목</th>
					<th class="col-md-2">가격</th>
					<th class="col-md-2">작성일</th>
					<th class="col-md-2">판매상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="t" varStatus="i">
					<tr class="align-middle">
						<td class="trade-history-img img-thumbnail"><a
							href="/tradeView?tradeNo=${t.getTradeNo() }"><img
								src="/upload/trade/${t.getFilepath() }"></a></td>
						<td class="overflow-text">${t.getTradeTitle() }</td>
						<td style="color: #f95420"><fmt:formatNumber value="${t.getPrice() }" pattern="#,###" />원</td>
						<td>${t.getRegDate() }<input type="hidden" name="trade"
							value="${t.getTradeNo() }"></td>
						<c:choose>
							<c:when test="${t.getTradeStatus() eq 1 }">
								<td class="img-thumbnail"><select
									class="form-select trade-status" name="tradeStatus">
										<option value=1 selected>판매 중</option>
										<option value=2>예약 중</option>
								</select></td>
							</c:when>
							<c:when test="${t.getTradeStatus() eq 2 }">
								<td class="img-thumbnail"><select
									class="form-select trade-status" name="tradeStatus">
										<option value=1>판매 중</option>
										<option value=2 selected>예약 중</option>
								</select></td>
							</c:when>
							<c:otherwise>
								<td class="img-thumbnail"><select
									class="form-select trade-status" name="tradeStatus">
										<option value=3 selected>판매완료</option>
								</select></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<hr>
		<br>
		<div id="pageNavi">${pageNavi }</div>
		<br>
		<br>

	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script>
		
	$(".trade-status").change(function(){
        var status = $(this).val();
        var tradeNo = $(this).parent().prev().children().val();
        console.log(status);
        console.log(tradeNo);
        var data = {status: status, tradeNo: tradeNo};
        $.ajax({
            url : "/changeStatus",
            data : data,
            type : "get",
            success : function() {
            	alert("판매상태가 변경되었습니다.");
            }
         });
     });
		
	</script>
</body>
</html>