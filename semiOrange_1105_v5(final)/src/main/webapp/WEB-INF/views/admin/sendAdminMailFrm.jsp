<%@page import="report.model.vo.AdminCount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

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
	#leftnavi>li:nth-child(1)>a{
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
    	display:block;
    	padding-bottom:10px;
    	border-bottom:3px solid #f57c00;
    	color: #f57c00;
    	font-weight: 600;
    	font-size: 30px
    }
    #contentright{
    	margin-top: 0px;
    }
    #mygo{
		text-decoration: none;
	}
	#back{
		margin-top:60px;
		text-align: center;
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
							<img id="profile" src="/img/orange.png" >
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
					<div class="col-lg-10">
						<p id="p">메일보내기</p>
						<form action="/sendAdminMail" method="post">
							<span >받는 주소 :${mail } </span>
							<input type="hidden" name="email" class="short form-control"
								value="${mail } "> <br>
							<br> <input type="text" name="emailTitle" class="form-control"
								placeholder="이메일 제목"> 
							<br>
							<textarea class="form-control" name="emailContent" placeholder="이메일 내용"></textarea>
							<br>
							<div id="back">
								<button class="btn btn-primary btn-block" type="submit" id="submit">메일전송</button>
								<a class="btn btn-warning btn-block" onclick="history.go(-1);" >뒤로가기</a>
							</div>
						</form>
							
					</div>
				</div>	
			</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>