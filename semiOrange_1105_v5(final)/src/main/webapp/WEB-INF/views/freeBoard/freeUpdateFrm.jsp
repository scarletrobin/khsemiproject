<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자유게시판 수정</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">

	<div class="container">
	<form action="/freeUpdate" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend style="font-size: 28px; font-weight: bold">자유게시판 수정</legend>
			<table class="table" style="width: 100%;">
				<tr class="table-active">
					<th style="width: 230px; border-right: 1px solid #e0e0e0;">제목</th>
					<td colspan="4" style="text-align: left; padding-left: 50px;">
						<input type="text" name="freeTitle" class="form-control"
						value="${f.freeTitle }">
					</td>
				</tr>
				<tr class="table-Default">
					<th style="border-right: 1px solid #e0e0e0">작성자</th>
					<th>${f.freeWriter }</th>
					<th style="text-align: right; padding-left: 20px;">작성일</th>
					<th style="width: 300px;">${f.regDate }
				</tr>
				<tr class="table-Default">
					<th style="border-right: 1px solid #e0e0e0">첨부파일</th>
					<th style="text-align: left;">
						<input type="hidden" name="freeNo" value="${f.freeNo }"> 
						<input type="hidden" name="status" value="1"> <!-- 삭제 유무 확인 --> 
						<c:choose>
							<c:when test="${not empty f.filename }">
								<img src="/img/file.png" width="16px" class="delFile">
								<span class="delFile">${f.filename }</span>
								<button type="button" id="delBtn"
									class="btn btn-primary btn-sm delFile">삭제</button>
								<input type="file" name="upfile" style="display: none;">
								<input type="hidden" name="oldFilename" value="${f.filename }">
								<input type="hidden" name="oldFilepath" value="${f.filepath }">
							</c:when>
							<c:otherwise>
								<!-- 첨부파일이 없으면 -->
								<input type="file" name="upfile">
							</c:otherwise>
						</c:choose></th>
					<th style="text-align: right;">조회수</th>
					<th>${f.readCount }</th>
				</tr>
				<tr class="table-Default">
					<th style="border-right: 1px solid #e0e0e0;">내용</th>
					<td colspan="3"
						style="height: 390px; vertical-align: top; text-align: left;">
						<textarea id="freeContent" name="freeContent" class="form-control">${f.freeContent}</textarea>
					</td>
				</tr>
				<tr class="table-Default">
					<th colspan="4" style="text-align:right;">
						<c:choose>
							<c:when test="${m.memberLevel eq 9}">
								<input type="checkBox" name="priority" checked>
								<span>고정글 등록하기</span>&nbsp&nbsp
							</c:when>
						</c:choose>
						<button type="submit" class="btn btn-primary" style="margin-right:10px;">수정완료</button>
					</th>
				</tr>
			</table>
		</fieldset>
	</div>
	<script>
		$(function() {
			$("#delBtn").click(function() {
				$(".delFile").hide();
				$(this).next().show();
				$("[name=status]").val(2);
			});

			$("#freeContent").summernote({
				height : 400,
				lang : "ko-KR",
				callbacks : {
					onImageUpload : function(files) {
						uploadImage(files[0], this);
					}
				}
			});
		});

		function uploadImage(file, editor) {
			var form = new FormData();
			foram.append("file", file);
			$.ajax({
				url : "/uploadImage",
				type : "post",
				data : form,
				processData : false,
				contentType : false,
				success : function(data) {
					$(editor).summernote("insertImage", data);
				}
			});
		}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>












