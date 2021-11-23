<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자유게시판 작성</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>   
	<link rel="stylesheet" href="/summernote/summernote-lite.css">  

	<div class="container">
		<form action="/freeWrite" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend style="font-size: 28px; font-weight: bold">자유게시판 작성</legend>
				<table class="table" style="width: 100%;">
					<tr class="table-active">
						<th style="width:230px;">작성자</th>
						<td colspan="3" style="text-align:left;">${sessionScope.m.memberId } 
						<input type="hidden" name="freeWriter" value="${sessionScope.m.memberId }">
						</td>
					</tr>
					<tr class="table-active">
						<th>제목</th>
						<td colspan="3"><input type="text" name="freeTitle" class="form-control"></td>
					</tr>
					<tr class="table-active">
						<th>내용</th>
						<td colspan="3" style="margin-top:0px; margin-bottom:0px; height:390px; vertical-align: top; text-align: left;">
						<textarea name="freeContent" id="freeContent" placeholder="오렌지마켓 자유게시판 입니다. &#13;&#10;자유롭게 글을 작성해주세요." class="form-control"
						style="margin-top:0px; margin-bottom:0px; height:390px;"></textarea></td>
					</tr>
					<tr class="table-active">
						<th>첨부파일</th>
						<td colspan="3" style="text-align: left;"><input type="file" name="upfile"></td>
					</tr>
					<tr class="table-active">
						<th colspan="4" style="text-align:right;">
						<c:if test="${sessionScope.m.memberLevel == 9}">
							<label>
								<input type="checkbox" name="priority">
								<span>고정글 등록하기</span>&nbsp&nbsp
							</label>
						</c:if>
							<button type="submit" class="btn btn-danger btn-block" style="margin-right:10px;">자유게시판 등록</button>
						</th>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<script>
		$(function(){
			$("#freeContent").summernote({
				height: 400,
				lang: "ko-KR",
				callbacks:{
					onImageUpload : function(files){
						uploadImage(files[0],this);
					}
				}
			});
		});
		
		function uploadImage(file,editor){
			var form = new FormData();
			foram.append("file",file);
			$.ajax({
				url : "/uploadImage",
				type : "post",
				data : form,
				processData : false,
				contentType : false,
				success : function(data){
					$(editor).summernote("insertImage",data);
				}
			});
		}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>