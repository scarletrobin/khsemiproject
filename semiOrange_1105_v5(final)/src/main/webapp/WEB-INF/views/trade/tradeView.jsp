<%@page import="trade.model.vo.PostFrom"%>
<%@page import="java.util.ArrayList"%>
<%@page import="trade.model.vo.TradeMember"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="trade.model.vo.Trade"%>
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
		<div class="card mb-3" style="max-width: 1100px;">
			<div class="row g-0">
				<div class="col-md-5">
					<img src="/upload/trade/${t.getFilepath() }" class="img-fluid rounded-start trade-img-view">
				</div>
				<div class="col-md-6 img-margin">
					<div class="card-body">
						<h3 class="card-title">${t.getTradeTitle() }</h3>
						<p class="card-text price-text" style="color: #f95420"><fmt:formatNumber value="${t.getPrice() }" pattern="#,###" /><span class="price-text-won">원</span>
						</p>
						<hr>
						<div class="card-text detail-wrap row row-cols-2">
							<div class="col">
								<medium class="text-muted text-detail">· 조회</medium>
								<span class="text-tap">${t.getReadCount() }</span>
							</div>
							<div class="col">
								<medium class="text-muted text-detail">· 작성일</medium>
								<span class="text-tap">${t.getRegDate() }</span>
							</div>
						</div>


						<div class="card-text detail-wrap row row-cols-2">
							<div class="col">
								<medium class="text-muted text-detail">· 상품상태</medium>
								<c:choose>
									<c:when test="${t.getTradeStatus() eq 1}">
										<span class="text-tap blue">판매 중</span>
									</c:when>
									<c:when test="${t.getTradeStatus() eq 2}">
										<span class="text-tap coral">예약 중</span>
									</c:when>
									<c:otherwise>
										<span class="text-tap red">판매 완료</span>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col">
								<medium class="text-muted text-detail">· 카테고리</medium>
								<span class="text-tap">${t.getCategory() }</span>
							</div>
						</div>
						<hr>
						<div class="profile-wrap row">
							<div class="profile-left col-2">
								<div class="profile">
									<c:choose>
										<c:when test="${empty tm.getFilepath() }">
											<img src="/img/unknown.png">
										</c:when>
										<c:otherwise>
											<img src="/upload/member/${tm.getFilepath() }">
										</c:otherwise>
									</c:choose>
								</div>				
							</div>
							<div class="profile-center col-6">
								<p class="text-tap2">${t.getTradeWriter() }</p>
								<c:choose>
									<c:when test="${t.getSigunguDo() eq t.getSigunguName() }">
										<p class="card-text trade-add"><span>거래지역: </span>${t.getSigunguDo() }</p>
									</c:when>
									<c:otherwise>
										<p class="card-text trade-add"><span>거래지역: </span>${t.getSigunguDo() } ${t.getSigunguName() }</p>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="profile-right col-4">
								<p class="text-tap3">매너날씨</p>
								<p class="text-tap">
									<c:choose>
										<c:when test="${tm.getManner() >= 80 }">
											<img src="/img/맑음.png">
										</c:when>
										<c:when test="${tm.getManner() >= 50 }">
											<img src="/img/보통.png">
										</c:when>
										<c:otherwise>
											<img src="/img/흐림.png">
										</c:otherwise>
									</c:choose>
								</p>
							</div>						
						</div>
						<hr>
						
						<div class="button-wrap">
							<c:choose>
								<c:when test="${not empty sessionScope.m && sessionScope.m.getMemberId() eq t.getTradeWriter() }">
									<c:choose>
										<c:when test="${t.getTradeStatus() ne 3 }">
											<button class="btn btn-primary btn-lg col-3" data-toggle="modal" data-target="#modal1">판매완료</button>
											<a href="/tradeUpdateFrm?tradeNo=${t.getTradeNo() }" class="btn btn-primary btn-lg col-3">수정하기</a>
											<a href="/tradeDelete?tradeNo=${t.getTradeNo() }" class="btn btn-primary btn-lg col-3">삭제하기</a>
										</c:when>
										<c:otherwise>
											<a href="/tradeDelete?tradeNo=${t.getTradeNo() }" class="btn btn-primary btn-lg col-3">삭제하기</a>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${not empty sessionScope.m && sessionScope.m.memberLevel eq 9 }">
									<a href="/tradeDelete?tradeNo=${t.getTradeNo() }" class="btn btn-primary btn-lg col-3">삭제하기</a>	
								</c:when>
								<c:when test="${empty sessionScope.m }">
									<c:choose>
										<c:when test="${t.getTradeStatus() ne 3 }">
											<a href="/loginFrm" class="btn btn-primary btn-lg col-3">신고하기</a>
											<a href="/loginFrm" class="btn btn-primary btn-lg col-3">거래하기</a>
										</c:when>
										<c:otherwise>
											<a href="/loginFrm" class="btn btn-primary btn-lg col-3">신고하기</a>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${t.getTradeStatus() ne 3 }">
											<button class="btn btn-primary btn-lg col-3 report-reset" data-toggle="modal" data-target="#modal2">신고하기</button>
											<button type="button" id="tradeSend" class="btn btn-primary btn-lg col-3">거래하기</button>					
											<form action="/tradePost" name="tradeSendFrm" method="post">
												<input type="hidden" name="postFrom" value="${t.getTradeWriter() }">
												<input type="hidden" name="tradeNo" value="${t.getTradeNo() }">
											</form>
										</c:when>
										<c:otherwise>
											<button class="btn btn-primary btn-lg col-3 report-reset" data-toggle="modal" data-target="#modal2">신고하기</button>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="card container2 card-margin-bottom">
		  <div class="card-header trade-info-tap">
		    <p>상품정보</p>
		  </div>
		  <div class="card-body trade-info-content">
		    <blockquote class="blockquote mb-0">
		    <c:choose>
		    	<c:when test="${empty t.getTradeContent() }">
		    		<span></span>
		    	</c:when>
		    	<c:otherwise>
		    		<span>${t.getTradeContentBr() }</span>
		    	</c:otherwise>
		    </c:choose>
		    </blockquote>
		  </div>
		</div>
		
		<!-- 판매완료 버튼 클릭 시 모달 -->
		<div class="modal" id="modal1">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<form action="transaction" name="transactionFrm" method="post">
					<div class="modal-header">
						<h5 class="modal-title" style="font-size: 16px">판매완료로 변경합니다.<span style="font-size: 14px; color: gray"> (구매자가 있다면 선택하세요)</span></h5>
						<button type="button" class="btn-close btn-sell-close" data-dismiss="modal" aria-label="Close"></button>
						<input type="hidden" name="tradeNo" value="${t.getTradeNo() }">
						<input type="hidden" name="trade-seller" value="${t.getTradeWriter() }">
						<input type="hidden" name="transaction-price" value="${t.getPrice() }">
						<input type="hidden" name="trade-status" value="3">
					</div>
					<div class="modal-body">
						<div class="list-group">
							<c:forEach items="${pf }" var="p" varStatus="i">
								<label class="list-group-item">
								    <input class="form-check-input me-1" type="radio" name="postFrom" value="${p.getPostFrom() }">
								    ${p.getPostFrom() }
								</label>
							</c:forEach>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary btn-sell sold-out">확인</button>
						<button type="button" class="btn btn-secondary btn-sell-close" data-dismiss="modal">취소</button>
					</div>
					</form>
				</div>
			</div>
		</div>
		
		<!-- 신고하기 버튼 클릭 시 모달 -->
		<div class="modal" id="modal2">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<form action="/reportMember" name="reportMemberFrm" method="post">
					<div class="modal-header">
						<h5 class="modal-title" style="font-size: 16px">신고 사유를 작성해주세요.</h5>
						<button type="button" class="btn-close btn-sell-close" data-dismiss="modal" aria-label="Close"></button>
					</div>
						<input type="hidden" name="tradeNo" value="${t.getTradeNo() }">
						<input type="hidden" name="reportWriter" value="${sessionScope.m.getMemberId() }">
						<input type="hidden" name="reportedMember" value="${t.getTradeWriter() }">
						<div class="modal-body">
							<div class="form-group">
						      <textarea class="form-control" name="reportContent" id="reportTextarea" rows="3"></textarea>
						    </div>
						</div>
						<div class="modal-footer">
							<div style="width: 70%" class="reportContent"></div>
							<button type="submit" class="btn btn-primary btn-sell sold-out">확인</button>
							<button type="button" class="btn btn-secondary btn-sell-close" data-dismiss="modal">취소</button>
						</div>			
					</form>
				</div>
			</div>
		</div>

	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script>
		$("#tradeSend").click(function() {
			$("[name=postFrom]").val();
			$("[name=tradeNo]").val();
			window.open("", "tradePost", "left=800, top=300, width=550, height=585");
			$("[name=tradeSendFrm]").attr("target", "tradePost");
			$("[name=tradeSendFrm]").submit();
		});
	</script>
</body>
</html>