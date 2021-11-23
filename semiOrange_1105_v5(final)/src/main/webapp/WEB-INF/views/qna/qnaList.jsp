<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<meta charset="UTF-8">
<title>Qna목록</title>
	<style>
		table a{
			text-decoration: none;
			color : black;
		}
		table a:hover{
			color : #e95420;
		}
		#searchBox{
			width:400px;
			margin: 10px;
			float: right;
			margin-right: 0;
		}
		select[name=type]{
			display:inline-block;
			width: 30%;
		}
		input[name=keyword]{
			display:inline-block;
			width: 50%;
		}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="container">
			<h3 style="color: #e95420; font-weight:bold;">QnA게시판</h3>
			<p>* 작성자만 조회 가능합니다.</p>
			<fieldset>
				<c:if test="${not empty sessionScope.m }">
					<div>
						<a class="btn btn-success writeBtn" href="/qnaWriteFrm" style="float:left; margin-top: 10px; margin-left: 9px">글쓰기</a>
					</div>					
				</c:if>
			<div id="searchBox">
				<form action="/searchQna" method="post">
				<c:choose>
					<c:when test="${not empty type }">
						<select class="form-control" name="type" disabled>
							<option value="writer" selected>작성자</option>							
						</select>
					</c:when>
					<c:otherwise>
						<select class="form-control" name="type" disabled>
							<option value="writer">작성자</option>
						</select>
					</c:otherwise>
				</c:choose>
				<input type="text" name="keyword" class="form-control" value="${keyword }">
				<button type="submit" class="btn btn-primary" style="margin-bottom: 5px;">검색</button>
				</form>
			</div>
				<table class="table table-hover" style="width:100%;">
					<tr class="table-primary">
					<th>번호</th><th style="text-align:left; padding-left: 50px;">제목</th><th>작성자</th><th>작성일</th>
				    </tr>
					<c:forEach items="${list }" var="q" varStatus="i">
						<tr>
							<td>${start+i.index }</td>
							<td style="text-align: left;width:60%">
								<i class="bi bi-file-lock2"></i>
								<!-- 작성자 본인만 조회할수 있게  -->
								<c:if test="${q.memberId eq sessionScope.m.memberId or sessionScope.m.memberLevel eq 9}">
									<a href='/qnaView?qnaNo=${q.qnaNo }'>
										${q.qnaTitle }
									</a>								
								</c:if>
								<c:if test="${q.memberId ne sessionScope.m.memberId and sessionScope.m.memberLevel ne 9}">
									<a href='/msg'>
										${q.qnaTitle }
									</a>													
								</c:if>
								<c:choose>
									<c:when test="${q.qnaCount >0}">
										<span style="color: red">[답변완료]</span>
									</c:when>
									<c:otherwise>
										<span>[답변대기]</span>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:if test="${q.memberLevel eq 2 }">
	                	  		<span class="btn btn-danger"><i class="bi bi-emoji-frown-fill"></i>블랙리스트</span>
	                	  		</c:if>
							${q.memberId }</td>
							<td>${q.regDate }</td>
						</tr>
					</c:forEach>
				</table>
				<div id="pageNavi">
				${pageNavi }
				</div>
			</fieldset>
		</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>