<%@page import="report.model.vo.*"%>
<%@page import="report.model.vo.AdminMember"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
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
    	margin: 0px;
    	color: #f57c00;
    	font-weight: 600;
    	font-size: 30px
    }
    #contentright{
    	margin-top: 0px;
    }
     .dropbtn {
      background-color : white;
	  color: black;
	  padding: 0px;
	  font-size: 16px;
	  border: none;
	  cursor: pointer;
	}
	
	#table{
		margin-top:20px;
		border-top: 3px solid #f57c00;
	}
	#btn{
		float: right;
	}
	#btn>button{
		width: 150px;
		height: 50px;
		font-size: 15px;
	}
		#searchBox{
		width:400px;
	}
	select[name=type]{
		display:inline-block;
		width:25%;
	}
	input[name=keyword]{
		display:inline-block;
		width:40%;
	}
	#listselect{
		float: right;
		margin-bottom: 10px;
	}
	#listselect a{
		text-decoration: none;
		color: black;
		
	}
	#listselect li{
		width: 50px;
		margin:0;
	}
	.bold{
		font-weight: 600;
	}
	#mygo{
		text-decoration: none;
	}
	/* The container <div> - needed to position the dropdown content */
	.dropdown {
	  position: relative;
	  display: inline-block;
	}
	
	/* Dropdown Content (Hidden by Default) */
	.dropdown-content {
	  display: none;
	  position: absolute;
	  background-color: #F2F2F2;
	  min-width: 110px;
	  z-index: 1;
	}
	
	/* Links inside the dropdown */
	.dropdown-content a {
	  color: black;
	  padding: 12px 16px;
	  text-decoration: none;
	  display: block;
	}
	
	/* Change color of dropdown links on hover */
	.dropdown-content a:hover {background-color: #ddd; color: black;}
	
	/* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
	.show {display:block;}
</style>
</head>
<body>
		<%-- <%@ include file="/WEB-INF/views/common/header.jsp" %> --%>
		<jsp:include page="/WEB-INF/views/common/header.jsp"/> <!-- 헤더추가 -->
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
				<!------------------- 내용메뉴------------------------ -->
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
					<!------------------- 내용본문------------------------ -->
					<div class="col-lg-10">
						<div class="container " id="contentright">
						  <p id="p">전체회원관리</p>
							 <div class="dropdown" id="listselect">
							 	<c:choose>
								 	<c:when test="${not empty keyword }">
								 		<button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown">${keyword}</button>
										  <ul class="dropdown-menu">
										    <li id="dropdown-menu"><a href="/adminPage?reqPage=1">번호순</a></li>
										  </ul>
									</c:when>
							 		<c:otherwise>
								 		<button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown">번호순</button>
										  <ul class="dropdown-menu">
										    <li id="dropdown-menu"><a href="/adminLevelPage?reqPage=1&keyword=등급순">등급순</a></li>
										  </ul>
									</c:otherwise>
							 	</c:choose>
								</div>
						  <table class="table" id="table">
						    <thead>
						      <tr>
						        <th>선택</th>
						        <th>회원번호</th>
						        <th>아이디</th>
						        <th>이름</th>
						        <th>휴대폰번호</th>
						        <th>이메일</th>
						        <th>가입일</th>
						        <th>회원등급</th>
						      </tr>
						    </thead>
						    <tbody>
						  	  <%-- <%
						  	  	for(AdminMember member: list){
						  	  %> --%>
						  	  <c:forEach items="${list }" var="member">
						      <tr>
						      	<td><input type="checkbox" class="chk" id="chk"></td>
						        <td>${member.memberNo }</td>
						        <td id="memberdetail">
								    <div class="dropdown">
									  <button class="dropbtn bold">${member.memberId }</button>
									  <div id="myDropdown" class="dropdown-content">
									    <a href="/memberDetail?memberNo=${member.memberNo }&memberId=${member.memberId }">회원정보</a>
									  </div>
									</div>
						        </td>
						        <td>${member.memberName }</td>
						        <td>${member.phone }</td>
						        <td>
						        <div class="dropdown">
									  <button class="dropbtn bold">${member.email }</button>
									  <div id="myDropdown" class="dropdown-content">
									    <a href="/sendMailFrm?email=${member.email }">메일보내기</a>
									  </div>
								</div>
								</td>
						        <td>${member.enrollDate }</td>
						        <td>
									<select class="form-control" style="width:80%;display:inline-block;">
										<c:choose>
											<c:when test="${member.memberLevel eq 9 }">
												<option value="9" selected>관리자</option>
												<option value="1">일반회원</option>
												<option value="2">블랙리스트</option>
												<option value="3">차단회원</option>
											</c:when>
											<c:when test="${member.memberLevel eq 1 }">
												<option value="9">관리자</option>
												<option value="1" selected>일반회원</option>
												<option value="2">블랙리스트</option>
												<option value="3">차단회원</option>
											</c:when>
											<c:when test="${member.memberLevel eq 2 }">
												<option value="9">관리자</option>
												<option value="1">일반회원</option>
												<option value="2" selected>블랙리스트</option>
												<option value="3" >차단회원</option>
											</c:when>
											<c:when test="${member.memberLevel eq 3 }">
												<option value="9">관리자</option>
												<option value="1">일반회원</option>
												<option value="2" >블랙리스트</option>
												<option value="3" selected>차단회원</option>
											</c:when>
										</c:choose>
									</select>
						        </td>
						      </tr>
						      </c:forEach>
						    </tbody>
						  </table>
						  <div id="pageNavi">${pageNavi }</div>
						  <div id="btn">
						  	<button class="btn btn-warning btn-block chkChangeLevel">등급변경</button>
						  	<button class="btn btn-danger btn-block chkDeleteMember">회원탈퇴</button>
						  </div>
						</div>
					</div>
				</div>	
			</div>
			<!-------------- 푸터추가 ------------------->
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		<script>
			$(".dropbtn").click(function(){
				  $(this).next().toggle();
				});
			$(".chkChangeLevel").click(function(){
				var inputs=$(".chk:checked");
				var num = new Array(); //회원번호 저장할 배열
				var level = new Array(); //변경할 등급을 저장할 배열
				inputs.each(function(idx,item){
					var memberNo = $(item).parent().next().html();
					num.push(memberNo);
					var memberLevel=$(item).parent().parent().find("select").val(); //후손중에 select를 찾아라
					level.push(memberLevel);
				});
				if(!inputs.length){
					alert("등급을 수정할 회원을 선택해 주세요");
				}else{
				location.href="/chkChangeLevel?num="+num.join("/")+"&level="+level.join("/")+"&page=1";
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
				location.href="/chkDeleteMember?num="+num.join("/")+"&delete=1";
				}
			});
		</script>
</body>
</html>