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
	height:220px;
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
    p{
    	color: #f57c00;
    	font-weight: 600;
    	font-size: 30px
    }
    #contentright{
    	margin-top: 0px;
    }
    #table{
		border-top: 3px solid #f57c00;
	}
    #textright{
    margin-left: 30px;
    line-height: 27px;
    color: #f57c00;
    font-weight: 600;
    }
    #mygo{
		text-decoration: none;
	}
	#back{
		margin-top:60px;
		text-align: center;
	}
	[name=month]{
	display: none;
	}
	#date{
	display: none;
	}
	#date input{
	display: inline-block;
	}
	
</style>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table, 
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {
		//ajax로 데이터 값 받기
			$.ajax({
					url : "/chartData",
					type : "get",
					data : {num:3},
					success : function(data){
					      // Create the data table.
					      var data = new google.visualization.DataTable(data);
					    /*   data.addColumn('string', '날짜');
					      data.addColumn('number', '가입자수');
					      data.addRows([
					        ['1021', 3],
					        ['1022', 1],
					        ['1023', 1],
					        ['1024', 1],
					        ['1025', 2]
					      ]); */

					      // Set chart options
					      var options = {'title':'최근 30일간 가입자수',
					                     'width':700,
					                     'height':300};

					      // Instantiate and draw our chart, passing in some options.
					      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
					      chart.draw(data, options);
						}
					
				});
    	  

    }
    </script>

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
						<p id="p">가입자수 차트</p>
							<div id="date">
							<span>시작 : </span><input type="date" name="month" id="startdate">
							<span>끝 : </span><input type="date" name="month" id="enddate">
							</div>
						<div id="chart_div" style="width:400; height:300"></div>
						<div id="back">
							<button class="btn btn-warning change">날짜변경</button>
							<button class="btn btn-warning btn-block" onclick="history.go(-1);" >뒤로가기</button>
						</div>
					</div>
				</div>	
			</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		<script type="text/javascript">
		$(".change").click(function(){
			$("[name=month]").show();
			$("#date").show();
			$(".change").html('검색하기');
			$(".change").attr("onclick","searchDate()");
			  });
		function searchDate(){
			var startdate = $("#startdate").val();
			var enddate = $("#enddate").val();
			var startdate1 = startdate.replaceAll("-","");
			var enddate1 = enddate.replaceAll("-","");
			if(startdate>enddate){
				alert("날짜를 확인해주세요");
			}else{
			location.href="/chart?num=2&startdate="+startdate1+"&enddate="+enddate1+"";
				
			}
			console.log(startdate1);
			console.log(enddate1);
		}
		</script>
</body>
</html>
