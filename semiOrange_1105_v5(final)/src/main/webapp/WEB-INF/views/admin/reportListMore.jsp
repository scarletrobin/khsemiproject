<%@page import="report.model.vo.AdminCount"%>
<%@page import="report.model.vo.Report"%>
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
	#leftnavi>li:nth-child(3)>a{
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
    p{
    	color: #f57c00;
    	font-weight: 600;
    	font-size: 30px;
    	
    }
    #p{
   		 margin-top: 70px
    }
    #contentright{
    	margin-top: 0px;
    }
    #table{
		border-top: 3px solid #f57c00;
		--bs-table-hover-color: #333;
    	--bs-table-hover-bg: #FEE8DB;
	}
    #btn{
		float: right;
	}
	#btn>button{
		width: 130px;
		height: 50px;
		font-size: 15px;
	}
	#mygo{
		text-decoration: none;
	}
	#rmember>a{
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
					<div class="col-lg-9">
						<div class="container" id="contentright">
						  <p>신고리스트</p>
						  <table class="table table-hover" id="table">
						    <thead>
						      <tr>
						        <th>선택</th>
						        <th>신고번호</th>
						        <th>신고자</th>
						        <th>피신고자</th>
						        <th>신고내용</th>
						        <th>신고날짜</th>
						        <th>승인여부</th>
						      </tr>
						    </thead>
						    <tbody>
							  <c:forEach items="${list }" var="r">
							   <tr>
						     	<td><input type="checkbox" class="chkY"></td>
						        <td>${r.reportNo }</td>
						        <td>${r.reportWriter }</td>
						        <td>${r.reportedMember }</td>
						        <td>${r.reportContent }</td>
						        <td>${r.reportDate }</td>
						        <td id="rResult">${r.reportResult }</td>
						      </tr>
							  </c:forEach>
						    </tbody>
						  </table>
						  <div id="pageNavi">${pageNavi }</div>
						  <div id="btn">
						  <button class="btn btn-warning btn-block chkYes">신고승인</button>
						  <button class="btn btn-warning btn-block chkDelete">신고삭제</button>
						  </div>
						  <p id="p">블랙리스트 관리</p>
							<table class="table table-hover" id="table">
							    <thead>
							      <tr>
							      	<th>선택</th>
							      	<th>회원번호</th>
							        <th>아이디</th>
							        <th>신고횟수</th>
							        <th>멤버레벨</th>
							      </tr>
							    </thead>
							    <tbody>
							     <c:forEach items="${cntlist }" var="r">
							     	<tr>
								      	<td><input type="checkbox" class="chk"></td>
								      	<td>${r.mNo }</td>
								        <td id="rmember">
								        <a href="/memberDetail?memberNo=${r.mNo }&memberId=${r.reportedMember }">${r.reportedMember }</a>
								        </td>
								        <td>${r.rCount }</td>
								        <td>
									        <select class="form-control" style="width:80%;display:inline-block;">
												<c:choose>
													<c:when test="${r.mLevel eq 1 }">
													<option value="1" selected>일반회원</option>
													<option value="2">블랙리스트</option>
													<option value="3">차단회원</option>
													</c:when>
													<c:when test="${r.mLevel eq 2 }">
													<option value="1">일반회원</option>
													<option value="2" selected>블랙리스트</option>
													<option value="3">차단회원</option>
													</c:when>
													<c:when test="${r.mLevel eq 3 }">
													<option value="1">일반회원</option>
													<option value="2">블랙리스트</option>
													<option value="3" selected>차단회원</option>
													</c:when>
													<c:when test="${r.mLevel eq 9 }">
													<option value="1">일반회원</option>
													<option value="2">블랙리스트</option>
													<option value="3">차단회원</option>
													<option value="4" selected>관리자</option>
													</c:when>
												</c:choose>
											</select>
								        </td>
							      	</tr>
							     </c:forEach>
							    </tbody>
							  </table>
							   <div id="btn">
							   <button class="btn btn-danger btn-block chkChangeLevel">등급변경</button>
							   <button class="btn btn-danger btn-block chkDeleteMember">회원탈퇴</button>
							   </div>
						</div>
					</div>
				</div>	
			</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		<script>
		$(".chkChangeLevel").click(function(){
			var inputs=$(".chk:checked");
			var num = new Array(); 
			var level = new Array(); 
			inputs.each(function(idx,item){
				var memberNo = $(item).parent().next().html();
				num.push(memberNo);
				var memberLevel=$(item).parent().parent().find("select").val(); //후손중에 select를 찾아라
				level.push(memberLevel);
			});
			if(!inputs.length){
				alert("회원을 선택해 주세요");
			}else{
			location.href="/chkChangeLevel?num="+num.join("/")+"&level="+level.join("/")+"&page=2";
			}
		});
		$(".chkYes").click(function(){
			var inputs=$(".chkY:checked");
			var num = new Array(); 
			inputs.each(function(idx,item){
				var reportNo = $(item).parent().next().html();
				num.push(reportNo);
			});
			if(!inputs.length){
				alert("승인할 목록을 선택해 주세요");
			}else{
			location.href="/chkChangeResult?num="+num.join("/");
			}
		});
		$(".chkDelete").click(function(){
			var inputs=$(".chkY:checked");
			var num = new Array(); 
			inputs.each(function(idx,item){
				var memberNo = $(item).parent().next().html();
				num.push(memberNo);
			});
			if(!inputs.length){
				alert("삭제할 목록를 선택해 주세요");
			}else{
			location.href="/chkDeleteReport?num="+num.join("/");
			}
		});
		$(".chkDeleteMember").click(function(){
			var inputs=$(".chk:checked");
			var num = new Array(); 
			inputs.each(function(idx,item){
				var memberNo = $(item).parent().next().html();
				num.push(memberNo);
			});
			if(!inputs.length){
				alert("탈퇴시킬 회원을 선택해 주세요");
			}else{
			location.href="/chkDeleteMember?num="+num.join("/")+"&delete=2";
			}
		});
		</script>
</body>
</html>