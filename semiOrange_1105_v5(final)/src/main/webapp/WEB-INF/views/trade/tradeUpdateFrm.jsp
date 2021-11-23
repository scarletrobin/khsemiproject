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
		<h4 class="text-center">중고거래 글쓰기</h4>
		<br>
		<hr>
		<form action="/tradeUpdate" method="post"
			enctype="multipart/form-data" class="trade-form">
			<input type="hidden" name="tradeNo" value="${t.getTradeNo() }">
			<input type="hidden" name="tradeWriter"
				value="${sessionScope.m.memberId }">
			<fieldset>
				<br>
				<div class="row mb-3">
					<label for="inputEmail3" class="col-sm-2 col-form-label">상품이미지<span
						class="must"> *</span></label>

					<div class="mb-3 col-sm-10 trade-img">
						<div>
							<label for="formFileMultiple" class="form-label cursor-img"><img
								src="/img/img-select.png" style="margin-right: 5px;"></label>
						</div>
						<input class="form-control" type="file" name="tradeImg"
							id="formFileMultiple" accept=".jpg, .jpeg, .png, .gif"
							onchange="loadImg(this);" style="display: none">
						<div class="preview-wrap"></div>
						<div class="trade-img-info">
							<p>* 상품 이미지는 640x640에 최적화 되어 있습니다.</p>
							<p>* 이미지는 상품등록 시 정사각형으로 짤려서 등록됩니다.</p>
						</div>
					</div>

				</div>
				<hr>
				<br>

				<div class="row mb-5">
					<label for="trade-write-title" class="col-sm-2 col-form-label"
						name="trade-title">글 제목<span class="must"> *</span></label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="trade-write-title"
							name="tradeTitle" value="${t.getTradeTitle() }"> <span
							class="titleChk span-rag-chk"></span>
					</div>
				</div>
				<hr>
				<br>

				<div class="row mb-5">
					<label for="category-select" class="col-sm-2 col-form-label">카테고리<span
						class="must"> *</span></label>
					<div class="col-sm-3">
						<select class="form-select" name="category" id="category-select">
							<option value="${t.getCategory() }" disabled>${t.getCategory() }</option>
							<option value="디지털/가전">디지털/가전</option>
							<option value="가구/인테리어">가구/인테리어</option>
							<option value="유아동">유아동</option>
							<option value="생활/가공식품">생활/가공식품</option>
							<option value="유아도서">유아도서</option>
							<option value="스포츠/레저">스포츠/레저</option>
							<option value="여성잡화">여성잡화</option>
							<option value="여성의류">여성의류</option>
							<option value="남성패션/잡화">남성패션/잡화</option>
							<option value="게임/취미">게임/취미</option>
							<option value="뷰티/미용">뷰티/미용</option>
							<option value="반려동물용품">반려동물용품</option>
							<option value="도서/티켓/음반">도서/티켓/음반</option>
							<option value="식물">식물</option>
							<option value="기타 중고물품">기타 중고물품</option>
						</select> <span class="cateChk span-rag-chk"></span>
					</div>
				</div>
				<hr>
				<br>

				<div class="row mb-5">
					<label for="trade-write-price" class="col-sm-2 col-form-label">가격<span
						class="must"> *</span></label>
					<div class="col-sm-5 price-wrap">
						<div>
							<input type="text" class="form-control" name="price"
								id="trade-write-price" value="${t.getPrice() }"> <span
								class="priceChk span-rag-chk"></span>
						</div>
						<div id="trade-price">원</div>
						<br>
						<br>
					</div>
				</div>
				<hr>
				<br>
				
				<div class="row mb-4">
					<label for="trade-write-price" class="col-sm-2 col-form-label">거래 지역<span
						class="must"> *</span></label>
					<div class="col-sm-5 price-wrap">		
						<div class="tradelocal-select">	
			                <label for="">시, 도 영역</label>
			                <select id="local1city" class="localcity form-select localcity-margin">
			                    <option value="" selected disabled>시, 도</option>
			                    <option value="seoul">서울특별시</option>
			                    <option value="busan">부산광역시</option>
			                    <option value="daegu">대구광역시</option>
			                    <option value="incheon">인천광역시</option>
			                    <option value="kwangju">광주광역시</option>
			                    <option value="deajeon">대전광역시</option>
			                    <option value="ulsan">울산광역시</option>
			                    <option value="sejong">세종특별자치시</option>
			                    <option value="kyeonggi">경기도</option>
			                    <option value="kangwon">강원도</option>
			                    <option value="chungbuk">충청북도</option>
			                    <option value="chungnam">충청남도</option>
			                    <option value="jeonbuk">전라북도</option>
			                    <option value="jeonnam">전라남도</option>
			                    <option value="kyeongbuk">경상북도</option>
			                    <option value="kyeongnam">경상남도</option>
			                    <option value="jeju">제주특별자치도</option>
			                </select>           
				           <br>
			                <label for="">시, 군, 구 영역</label>
			                <select name="local1" class="local1 form-select localcity-margin">
			                    <option value="0">시, 군, 구</option>
			                </select>
				        <input type="hidden" name="tradeLocal" class="local-result" style="text-align: center;">
						
						<br> <br>
					</div>
					</div>
				</div>
				<hr>
				<br>

				<div class="row mb-3">
					<label for="trade-wirte-textarea" class="col-sm-2 col-form-label">상품정보</label>
					<div class="col-sm-9">
						<c:choose>
							<c:when test="${empty t.getTradeContent() }">
								<textarea class="form-control" name="tradeContent"
									id="trade-wirte-textarea" rows="8"></textarea>
							</c:when>
							<c:otherwise>
								<textarea class="form-control" name="tradeContent"
									id="trade-wirte-textarea" rows="8">${t.getTradeContent() }</textarea>
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="float-end">
					<br>
					<br>
					<br>
					<button type="submit" class="btn btn-primary btn-lg float-end"
						onclick="return checkValue();">등록하기</button>
				</div>
			</fieldset>
		</form>

	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script type="text/javascript" src="/js/trade/tradeUpdate.js"></script>
</body>
</html>