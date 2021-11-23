<%@page import="report.model.vo.AdminCount"%>
<%@page import="report.model.vo.AllWrite"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.top{
	background-color :#FFFDC6;
	margin : 0px;
	height:210px;
	}
	#profile{
	width:120px;
	heigh:120px;
	border-radius:50px;
	margin-left: 180px;
	background: white;
	}
	.leftmenu>a{
		height:70px;
		font-size: 20px;
		font-weight: 600;
		color: gray;
	}
	#leftnavi>li:nth-child(2)>a{
		color: #f57c00;
		font-size: 22px;
	}
	.mypage{
	color: #f57c00;
	font-size: 30px;
	font-weight:600;
	padding-top:10px;
	margin-left: 40px;
	}
	.head>div{
	display: inline-block;
	vertical-align: middle;
	}
    .content{
       flex-grow: 1;
    }
    .top>.container{
    	margin-top: 0px;
    	
    }
    #row{
    	margin: 30px auto 100px;
    }
    #leftnavi{
    	text-align:left;
    	margin: 0;
    }
    .headtext{
    	margin-left: 20px;
    }
    .headtext>div:first-child{
    	color: #f57c00;
    	font-weight: 600;
    }
    .headtext>div:nth-child(2){
    	color: #e95420;
    	font-weight: 600;
    	font-size: 37px;
    }
    .headtext>span{
    	font-weight: 600;
    	line-height: 25px;
    }
    .headtext>span a{
    	text-decoration: none;
    	color: green;
    }
    #p{
    	float:left;
    	color: #f57c00;
    	font-weight: 600;
    	font-size: 30px;
    	display: inline-block;
    }
    
    #contentright{
    	margin-top: 0px;
    }
    #table{
		margin-top:30px;
		border-top: 3px solid #f57c00;
	}
    #more{
    	float:right;
    	margin-bottom:8px;
    	display: inline-block;
    }
    #morebtn{
    	margin-left: 30px;
    }
    #p1{
		margin-top: 40px;
		float:left;
    	color: #f57c00;
    	font-weight: 600;
    	font-size: 30px;
    	display: inline-block;
	}
	#mygo{
		text-decoration: none;
	}
	#a{
		text-decoration: none;
	}
	
</style>
</head>
<body>
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<div class="content" >
				<div class="top" style="back">
					<div class="container">
						<div class="mypage">MY PAGE</div>
						<div class="head">
							<div>
							<c:choose>
							<c:when test="${empty sessionScope.m.filePath }">
							<img id="profile" src="/img/orange.png">
							</c:when>
							<c:otherwise>
							<img id="profile" src="/upload/member/${sessionScope.m.filePath }" >
							</c:otherwise>
							</c:choose>
							</div>
							<div class="headtext">
								<div>오늘도 즐거운 오렌지 생활되세요:)</div>
								<div>관리자 [${sessionScope.m.memberName }]님!</div>
								<span><a href="/chart?num=1">총 가입자수</a>:${sessionScope.ac.alljoin } </span>
								<span>오늘 가입자수 : ${sessionScope.ac.newjoin}</span>
								<br>
								<span>총 글수 : ${sessionScope.ac.alltrade+ac.allfree }</span>
								<span>오늘 새 글수 : ${sessionScope.ac.newfree+ac.newtrade }</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row container" id="row">
					<div class="col-lg-2">
					  <ul class="nav flex-column" id="leftnavi">
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/adminPage?reqPage=1">전체회원관리</a>
					    </li>
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/boardAdmin">게시판/문의관리</a>
					    </li>
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/reportListMore?reqPage=1">신고리스트관리</a>
					    </li>
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/myUserInfo?local1=${sessionScope.m.local1 }&local2=${sessionScope.m.local2}&local3=${sessionScope.m.local3}" >나의정보</a>
					    </li>
					  </ul>
					</div>
					<div class="col-lg-9">
					  <div class="container" id="contentright">
					  	  <p id="p">문의게시판<a href="/qnaList?reqPage=1" class="btn btn-danger" id="morebtn">관리하기</a></p>
						  <table class="table table-hover" id="table">
						    <thead>
						      <tr>
						        <th>글번호</th>
						        <th>글쓴이</th>
						        <th>글제목</th>
						        <th>작성일</th>
						      </tr>
						    </thead>
						    <tbody>
						     	<c:forEach items="${qnalist }" var="a">
							      <tr>
							        <td>${a.qnaNo }</td>
							        <td>${a.memberId }</td>
							        <td><a href='/qnaView?qnaNo=${a.qnaNo }' id="a">${a.qnaTitle }</a></td>
							        <td>${a.qnaRegDate }</td>
							      </tr>
							     </c:forEach>
						    </tbody>
						  </table>
						  <p id="p1">중고거래 새 인기글<a href="/tradeList?reqPage=1" class="btn btn-danger" id="morebtn">관리하기</a></p>
						  <table class="table table-hover" id="table">
						    <thead>
						      <tr>
						        <th>글번호</th>
						        <th>글쓴이</th>
						        <th>글제목</th>
						        <th>조회수</th>
						        <th>작성일</th>
						      </tr>
						    </thead>
						    <tbody>
								 <c:forEach items="${tradelist }" var="a" begin="0" end="3">
							      <tr>
							        <td>${a.tradeNo }</td>
							        <td>${a.memberId }</td>
							        <td><a id="a" href="/tradeView?tradeNo=${a.tradeNo }">${a.tradeTitle }</a></td>
							        <td>${a.tradeReadCount }</td>
							        <td>${a.tradeRegDate }</td>
							      </tr>
							      </c:forEach>
						    </tbody>
						  </table>
						  <p id="p1">동네생활 새 인기글<a href="/freeBoard?reqPage=1" class="btn btn-danger" id="morebtn">관리하기</a></p>
						  <table class="table table-hover" id="table">
						    <thead>
						      <tr>
						        <th>글번호</th>
						        <th>글쓴이</th>
						        <th>글제목</th>
						        <th>조회수</th>
						        <th>작성일</th>
						      </tr>
						    </thead>
						    <tbody>
						        <c:forEach items="${freelist }" var="a" begin="0" end="3">
							      <tr>
							        <td>${a.freeNo }</td>
							        <td>${a.memberId }</td>
							        <td><a href="/freeView?freeNo=${a.freeNo }" id="a">${a.freeTitle }</a></td>
							        <td>${a.freeReadCount }</td>
							        <td>${a.freeRegDate }</td>
							      </tr>
							     </c:forEach>
						    </tbody>
						  </table>
						 
						</div>
					</div>
				</div>	
			</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>