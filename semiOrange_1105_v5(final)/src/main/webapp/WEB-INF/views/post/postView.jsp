<%@page import="post.model.vo.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<link rel="stylesheet" href="/css/post/postview.css">
<title>쪽지 보기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div class="postview-wrap">
			<c:choose>
				<c:when test="${p.postTo != sessionScope.m.memberId || empty sessionScope.m.memberId}">
					<h1>잘못된 접근입니다.</h1>
					<script>
						alert("잘못된 접근입니다.");
						history.go(-1);
					</script>
				</c:when>
			<c:otherwise>
			<fieldset>
				<legend>쪽지 내용</legend>
				<table class="table table-bordered" id="postView" style="width:100%;">
					<tr>
						<th colspan="1" class="table-active">제목</th>
						<td colspan="3">${p.postTitle }</td>
					</tr>
					
					<tr>
						<th colspan="1" class="table-active">작성자</th>
						<c:choose>
							<c:when test="${empty p.postFrom }">
								<th colspan="3">탈퇴 회원</th>
							</c:when>
							<c:otherwise>
								<td colspan="3">${p.postFrom }</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th colspan="1" class="table-active">작성일</th>
						<td colspan="3">${p.postDate }</td>
					</tr>
					<tr class="postcontent">
						<th class="table-active">내용</th>
						<td colspan="3">
							${p.postContentBr }
						</td>
					</tr>
					<tr>
						<td colspan="4">
						<div class="postview-button">
							<a href="/postList?reqPage=1" class="btn btn-secondary">이전</a>
							<c:if test="${not empty sessionScope.m && (sessionScope.m.memberId == p.postTo)}">
							<form action="/postReplyFrm" method="post" class="px-3">
								<input type="hidden" name="postFrom" value="${p.postFrom }">
								<input type="hidden" name="tradeNo" value="${p.tradeNo }">
								<c:if test="${not empty p.postFrom }">
									<input type="submit" value="답장하기" class="btn btn-primary">
								</c:if>
							</form>
							</c:if>
							<a href="/postDelete?postNo=${p.postNo }" class="btn btn-danger">삭제하기</a>
						</div>
						</td>
					</tr>
				</table>
			</fieldset>	
			</c:otherwise>
			</c:choose>
		</div>	
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>