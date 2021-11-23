<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<title>공지사항 업데이트</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<script>
		$(function(){
			$("#noticeContent").summernote({
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
			//form과 같은 효과를 내는 객체생성
			var form = new FormData();
			form.append("file",file);
			$.ajax({
				url : "/uploadImageNotice",
				type : "post",
				data : form,
				processData : false,
				contentType : false,
				success : function(data){
					//결과로받은 업로드 경로를 이용해서 에디터에 이미지 추가
					$(editor).summernote("insertImage",data);
				}
			});
			//processDate : 기본값이 true
			//            -> 파일전송시 String이 아닌 파일 형태로 전송하기위해서 기본설정을 제거
			//contentType : 기본값 "application/x-wwww.form-urlencoded;charset=UTF-8"
			//			  -> 파일전송시 enctype : "multipart/form-data"로 변환하기 위해 기본값 제거
		}
	</script>
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="container">
		<form action="/noticeUpdate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="noticeNo" value="${n.noticeNo }">
			<fieldset>
				<legend>Notice 수정</legend>
				<table class="table" style="width: 100;">
					<tr class="table-primary">
						<th>제목</th>
						<td colspan="3">
							<input type="text" name="noticeTitle" class="form-control" value="${n.noticeTitle }">
						</td>					
					</tr>
					<tr>
						<th>작성자</th>
						<td>${n.memberId }</td>
						<th>첨부파일</th>
						<td style="text-align: left;">
							<input type="hidden" name="status" value="1">
							<c:choose>
								<c:when test="${not empty n.filename }">
								<i class="bi bi-trash-fill"></i>
								<span class="delFile">${n.filename }</span>
								<button type="button" id="delBtn" class="btn btn-primary btn-sm delFile">삭제</button>
								<input type="file" name="upfile" style="display:none;">
								<input type="hidden" name="oldFilename" value="${n.filename }">
								<input type="hidden" name="oldFilepath" value="${n.filePath }">									
								</c:when>
								<c:otherwise>
									<input type="file" name="upfile">
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3" style="textalign:left;">
							<textarea id="noticeContent" name="noticeContent" class="form-control">${n.noticeContent }</textarea>
						</td>
					</tr>
					<tr>
						<th colspan="4">
							<button type="submit" class="btn btn-danger btn-block">공지사항등록</button>
						</th>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<script>
		$("#delBtn").click(function(){
			$(".delFile").hide();
			$(this).next().show();
			$("[name=status]").val(2);
		});
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>