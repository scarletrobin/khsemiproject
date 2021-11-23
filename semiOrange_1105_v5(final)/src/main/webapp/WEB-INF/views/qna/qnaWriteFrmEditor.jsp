<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<meta charset="UTF-8">
<title>QnA작성</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="container">
		<form action="/qnaWrite" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>QnA작성</legend>
				<table class="table" style="width:100;">
					<tr class="table-active">
						<th>제목</th>
						<td colspan="3">
							<input type="text" name="qnaTitle" class="form-control">
						</td>
					</tr>
					<tr class="table-active">
						<th>작성자</th>
						<td>
							${sessionScope.m.memberId }
							<input type="hidden" name="member_id" value="${sessionScope.m.memberId }">
						</td>
						<th>첨부파일</th>
						<td style="text-align:left">
							<input type="file" name="upfile">
						</td>
					</tr>
					<tr>
						<th class="table-active">내용</th>
						<td colspan="3" style="text-align:left; border-right:3px solid rgba(0, 0, 0, 0.1); ">
							<textarea id="qnaContent" name="qnaContent" class="form-control"></textarea>
						</td>
					</tr>
					<tr class="table-active">
						<th colspan="4">
							<button type="submit" class="btn btn-primary btn-block">QnA등록</button>
						</th>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<script>
		$(function(){
			$("#qnaContent").summernote({
				height : 400,
				lang : "ko-KR",
				callbacks : {
					onImageUpload : function(files){
						uploadImage(files[0],this);
					}
				}
			});
		});
		function uploadImage(file,editor){
			var form = new FormData();
			form.append("file",file);
			$.ajax({
				url : "/uploadImageQna",
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
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>