<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자유게시판</title>
<style>
.btn-secondary {
	float: right;
}

.form-check {
	display: block;
	float: left;
	padding-top: 5px;
}

.freeSearch {
	display: flex;
	float: right;
	float: left;
	margin-bottom: 10px;
}

#free {
	text-decoration: none;
	color: #333;
}
.lg_box{
	border : 1px solid #d4d4d4;
	padding: 15px;
	background : #f9f9f9;
}
.lg_box>.b_box {
    border: 1px solid #d4d4d4;
}
ul.comp-lst_squ {
    padding-left: 15px;
	text-align: left;
}
.b_box {
    background: #E9F7FE;
    padding: 10px 15px;
    margin-right: 10px;
    border: 1px solid #eee;
}
ul.b_box > li {
    position: relative;
    padding-left: 12px;
    font-size: 16px;
    font-weight: 400;
    color: #252525;
    letter-spacing: -0.03em;
    line-height: 26px;
}

ul.b_box > li:before {
    width: 4px;
    height: 4px;
    background: #252525;
    position: Absolute;
    left: 0;
    top: 10px;
    display: block;
    content: "";
}
.fbold{
	 list-style:none;
}
.total-length{
	color:#f15922;
	margin-top:5px;
}
.totalbold{
	font-weight:bold;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<fieldset>
			<legend style="font-size: 28px; font-weight: bold">자유게시판</legend>
				<div class="lg_box">
					<ul>
						<li>
							자유게시판은 누구나 자유롭게 의견을 제시할 수 있으며, <span style="color:red;">게시된 의견에 대하여는 답변을 하지 않습니다.</span>
						</li>
						
						<li>
							답변을 원하시면 위에메뉴 <span style="font-weight :bolder; color:blue;">"QNA"</span>를 이용해주시기 바랍니다.
						</li><br>
					<ul class="b_box">
						<li class="fbold">게시판의 건전한 운영을 위하여 정치적 목적, 상업적 광고, 특정인에 대한 비방(명예훼손), 저작권 침해, 반복적 게시글, 욕설 및 음란물, 개인사생활 등 게시판의 취지에 맞지 않는 게시물은 사전 예고없이 삭제되오니 양지하시기 바랍니다. 의무사항 위반 시 관련 법률에 따라 처벌될 수 있으며, 위반 경중에 따라 글쓰기 제한 등의 조치가 있을 수 있습니다.</li>
						<li class="fbold">게시글에는 개인정보의 등록을 금하며<span style="color:red"> 개인정보무단 이용시 민·형사상 책임이 있습니다. <br> 글쓰기 시 개인을 식별할 수 있는 정보(주민등록번호, 여권번호, 휴대전화번호 등)가 없는지 확인하시기 바랍니다.</span></li>
					</ul>
					</ul>
				</div><br>
			<c:if test="${not empty sessionScope.m && (sessionScope.m.memberLevel eq 1 || sessionScope.m.memberLevel eq 9)}">
				<a class="btn btn-secondary" href="/freeWriteFrm">글쓰기</a>
			</c:if>
			
			<div class="freeSearch">
				<form style="position: relative;" action="/searchFree" method="post">
					<div class="form-check">
					<label class="form-check-label"> 
						<input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="option1" checked>내용 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					</label> 
					<label class="form-check-label"> 
						<input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios2" value="option2">작성자 &nbsp&nbsp&nbsp&nbsp
					</label>		
			</div>
				<input type="text" class="form-control me-sm-2" name="freeSearch" style="width: 350px;"> 
					<input type="submit" id="fsearch" style="display: none;"></input> 
					<label for="fsearch"> 
						<img src="/img/search_icon.png" style="width: 30px; height: 30px; top: 5px; right: 10px; position: absolute; margin-right: 10px;">
					</label>
				</form>
			</div>
			
			<p class="total-length">
				&nbsp전체게시물 <em class="totalbold">${totalCount-fixPage}</em> 건
			</p>
			<table class="table table-hover" style="width: 100%;">
				<tr class="table-active">
					<th style="width:100px">번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th style="width:100px">조회수</th>
				</tr>
					<c:forEach items="${list  }" var="f" varStatus="i">
					<c:choose>
						<c:when test="${f.priority == 0}">
							<tr class="table-default" id="freeDefault">
								<td>${start + i.index-fixPage}</td> 
								<td style="text-align: left; width: 60%;">
									<a href='/freeView?freeNo=${f.freeNo}' id="free"> 
										${f.freeTitle }
									</a> [${f.ncCount }]</td>
								<td>${f.freeWriter }</td>
								<td>${f.regDate }</td>
								<td>${f.readCount }</td>
							</tr>
						</c:when>
						<c:otherwise>
						<tr class="table-default" id="freeNotice" style="background-color: #bdbdbd;">
								<td><img src="/img/bell2.png" style="width:20px; height:20px;"></td> 
								<td style="text-align: left; width: 60%;">
									<a href='/freeView?freeNo=${f.freeNo }' id="free"> 
										${f.freeTitle }
									</a></td>
								<td>${f.freeWriter }</td>
								<td>${f.regDate }</td>
								<td>${f.readCount }</td>
							</tr>
						</c:otherwise>
					</c:choose>
					</c:forEach>
			</table>
			<div id="pageNavi">${pageNavi }</div>
		</fieldset>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>