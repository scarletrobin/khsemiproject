<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>
		<style>
		table a{
			text-decoration: none;
			color : black;
		}
		.table{
			margin-top: 50px;
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
		<h3 style="color: #e95420; font-weight:bold;">공지사항</h3>
		<fieldset>
			<c:if test="${not empty sessionScope.m and sessionScope.m.memberLevel eq 9}">
				<div>
					<a class="btn btn-success writeBtn" href="/noticeWriteFrm" style="float:right; margin-left: 9px;">글쓰기</a>
				</div>
			</c:if>
					<table class="table table-hover" style="width:100%;">
						<tr class="table-primary">
						<th>번호</th><th style="text-align:left; padding-left: 50px;">제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
					    </tr>
						<c:forEach items="${list }" var="n" varStatus="i">
							<tr>
								<td>${start+i.index }</td>
								<td style="text-align: left;width:60%">
										<a href='/noticeView?noticeNo=${n.noticeNo }'>
											${n.noticeTitle }
										</a>						
									[${n.ncCount }]
								</td>
								<td class="text-primary" style="font-weight: bold;">관리자</td>
								<td>${n.regDate }</td>
								<td>${n.readCount }</td>
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