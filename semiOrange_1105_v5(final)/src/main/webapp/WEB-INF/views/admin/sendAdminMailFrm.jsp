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
								<div>????????? ????????? ????????? ???????????????:)</div>
								<div>????????? [${sessionScope.m.memberName }]???!</div>
								<span><a href="/chart?num=1">??? ????????????</a>:${sessionScope.ac.alljoin } </span>
								<span>?????? ???????????? : ${sessionScope.ac.newjoin}</span>
								<br>
								<span>??? ?????? : ${sessionScope.ac.alltrade+ac.allfree }</span>
								<span>?????? ??? ?????? : ${sessionScope.ac.newfree+ac.newtrade }</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row container" id="row">
					<div class="col-lg-2">
					  <ul class="nav flex-column" id="leftnavi">
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/adminPage?reqPage=1">??????????????????</a>
					    </li>
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/boardAdmin">?????????/????????????</a>
					    </li>
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/reportListMore?reqPage=1">?????????????????????</a>
					    </li>
					    <li class="nav-item leftmenu">
					      <a class="nav-link" href="/myUserInfo?local1=${sessionScope.m.local1 }&local2=${sessionScope.m.local2}&local3=${sessionScope.m.local3}" >????????????</a>
					    </li>
					  </ul>
					</div>
					<div class="col-lg-10">
						<p id="p">???????????????</p>
						<form action="/sendAdminMail" method="post">
							<span >?????? ?????? :${mail } </span>
							<input type="hidden" name="email" class="short form-control"
								value="${mail } "> <br>
							<br> <input type="text" name="emailTitle" class="form-control"
								placeholder="????????? ??????"> 
							<br>
							<textarea class="form-control" name="emailContent" placeholder="????????? ??????"></textarea>
							<br>
							<div id="back">
								<button class="btn btn-primary btn-block" type="submit" id="submit">????????????</button>
								<a class="btn btn-warning btn-block" onclick="history.go(-1);" >????????????</a>
							</div>
						</form>
							
					</div>
				</div>	
			</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>