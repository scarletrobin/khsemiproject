<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<style>
 .head{
 background-color: #FFFDC6;
 height: 50px;
 }
 #text{
 width:200px;
 height: 100px;
 resize: none;
 display: none;
 }
 .container>div:first-child{
 	vertical-align: top;
 	overflow: hidden;
 	
 }
 .container img{
 	width: 50px;
 	height: 50px;
 	float: left;
 }
 .container>div:first-child>span {
 	display:block;
 	margin-top:7px;
	font-size: 30px;
	font-weight: 600;
	color: #f57c00;
	float: left;
}
	.container>div:nth-child(2)>span {
	display:block;
 	margin-top:5px;
	font-size: 18px;
}
.container>div:nth-child(3)>span{
	font-size: 15px;
}
.container>div:nth-child(4) {
	text-align: left;
	padding-left: 10px;
}
.container>div:nth-child(5) {
	text-align: center;
}
.container>div:nth-child(5)>button {
	display: inline-block;
	margin: 20px 5px 10px;
}
#mygo{
		text-decoration: none;
	}
</style>
</head>
<body>
	<div class="head">
		<div class="container">
				<div>
					<img src="/img/orange.png"><span>신고하기</span>
				</div>
				<div>
					<span>신고대상 : ${reportwriter }</span>
				</div>
				<div>
					<span><strong>사유선택 </strong>: 여러 사유에 해당될 경우, <span><br>
					</span>대표적인 사유 1개를 선택해주세요</span><br>
				</div>
				<div>
					<input type="hidden" name="reportWriter" value="${sessionScope.m.memberId }">
					<input type="hidden" name="reportedMember" value="${reportwriter }">
					<input type="radio" name="content" value="부적절한 홍보 게시글">부적절한 홍보 게시글<br>
	            	<input type="radio" name="content" value="음란성 또는 청소년에서 부적합한 내용">음란성 또는 청소년에서 부적합한 내용<br>
	            	<input type="radio" name="content" value="명예훼손/사생활침해 및 저작권등 ">명예훼손/사생활침해 및 저작권등 <br>
	            	<input type="radio" name="content" value="불법촬영물 등 신고">불법촬영물 등 신고<br>
	            	<input type="radio" name="content" value="기타" id="other">기타<br>
	            	<textarea id=text placeholder="내용을 입력해 주세요"></textarea>
	            </div>
	            <div>
	            	<button class="btn btn-warning" type="submit" id="submit">신고하기</button><button class="btn btn-warning" id="cancle">취소</button> 
	            </div>
		</div>
	</div>
	<script >

	  $("#other").click(function(){
		  $("#text").toggle();
		  });
	  $("#cancle").click(function(){
		  self.close();
	  });
	  $("#submit").click(function(){
			var reportWriter = $("[name=reportWriter]").val();
			var reportedMember = $("[name=reportedMember]").val();
			var reportContent = $('[name="content"]:checked').val();
			if(reportContent=="기타"){
				var reportContent = $("textarea").val();
			}
			console.log(reportContent);
			//객체생성
			var r = {reportWriter:reportWriter,reportedMember:reportedMember,reportContent:reportContent};
			console.log(r);
				$.ajax({
				url : "/report",
				type : "post",
				data : r,
				success : function(data) {
					if(data==1){
						document.write("신고되었습니다(2초후 닫힘)");
						  function out() {
							setTimeout('close()',2000);								
						}
						  function close() {
							  self.close();
						}
						  out();
						 
					}else{
						document.write("다시시도해주세요(2초후 닫힘)");
						  function out() {
							setTimeout('close()',2000);								
						}
						  function close() {
							  self.close();
						}
						  out();
					}
				}
			});
		});
	</script>
	
</body>
</html>