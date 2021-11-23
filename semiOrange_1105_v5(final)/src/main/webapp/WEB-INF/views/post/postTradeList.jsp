<%@page import="post.model.vo.Post" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 쪽지함</title>
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<link rel="stylesheet" href="/css/post/postlist.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="container">
			<div class="postList-wrap">
				<fieldset>
					<h1>쪽지함</h1>
						<table class="table-hover" style="width:100%;">
							<tr class="table-primary">
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
							<c:forEach items="${list }" var="p" varStatus="i">
							<tr class="table-light">
								<td>${start + i.index }</td>
								<td style="text-align:left;width:60%;">
									<a href="/postView?postNo=${p.postNo }" style="text-decoration:none; color:black;">${p.postTitle}</a>
								</td>
								<c:choose>
									<c:when test="${empty p.postFrom }">
										<td>탈퇴회원</td>
									</c:when>
									<c:otherwise>
										<td>
											${p.postFrom }
										</td>
									</c:otherwise>
								</c:choose>
								<td>${p.postDate.substring(0, 10) }</td>
							</tr>
							</c:forEach>
						</table>
						<c:choose>
							<c:when test="${not empty sessionScope.m }">
								<div>
									<a class="btn btn-secondary writeBtn" href="/postWriteFrm">쪽지 작성하기</a>
								</div>
							</c:when>
						<c:otherwise>
							<h1>비로그인</h1>
						</c:otherwise>
					</c:choose>
				</fieldset>
				<br>
			<div id="pageNavi">${pageNavi }</div>
			</div>
		</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>