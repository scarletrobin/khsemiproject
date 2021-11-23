<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자유게시판 상세보기</title>
</head>
<style>
	.w3-border{
		float:right;
	}
	#func{
		text-decoration:none
	}
	.like_result{
		text-decoration:none
	}
	#commentright{
		display: inline-block;
		text-align: right;
	}
</style>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<fieldset>
			<legend style="font-size: 28px; font-weight: bold">자유게시판</legend>
				<table class="table" id="freeView" style="width:100%;">
				<tr class="table-active">
					<th style="width:230px; border-right:1px solid #e0e0e0;">제목</th>
					<th colspan="2" style="text-align:left; padding-left:50px;">${f.freeTitle }</th> 
						<c:choose>
							<c:when test="${not empty sessionScope.m && (sessionScope.m.memberId ne f.freeWriter && f.priority ne 1)}">
								<th colspan="1">
									<form action="/reportFrm" name="reportFrm" method="post">
									<input type="hidden" name="reportwriter" value="${f.freeWriter}">
									</form>							 	
									<i class="fas fa-bullhorn" style="color:red"></i>
									<a style="text-decoration:none;" href="javascript:void(0)" id="report">신고하기</a>
								</th>
							</c:when>
							<c:otherwise>
								<th colspan="1"></th>
							</c:otherwise>
							</c:choose>
						
				</tr>
				<tr class="table-Default">
					<th style="border-right:1px solid #e0e0e0;">작성자</th>
					<th style="text-align:left; padding-left:50px;">${f.freeWriter }</th>
					<th style="text-align:right; padding-left:20px;">작성일</th>
					<th style="width:300px;" >${f.regDate }</th>
				</tr>
				<tr class="table-Default">
					<th style="border-right:1px solid #e0e0e0">첨부파일</th>
					<th>
						<c:if test="${not empty f.filename }">
							<img src="/img/file.png" width="16px">
							<a href="/fileDown?freeNo=${f.freeNo }">${f.filename }</a>
						</c:if>
					</th>
					<th style="text-align:right;">조회수</th>
					<th>${f.readCount }</th>
				</tr>
				<tr class="table-Default">
					<th style="border-right:1px solid #e0e0e0;">내용</th>
					<th colspan="3" style="margin-top:0px; margin-bottom:0px; height:390px; vertical-align: top; text-align: left;">
						${f.freeContent}
					</th>
				</tr>
				<tr class="table-Default">
					<th colspan="4" style="text-align:center;">
						<button class="btn btn-primary" onclick="history.go(-1);">이전화면</button>
							<c:if test="${not empty sessionScope.m && sessionScope.m.memberId eq f.freeWriter }">
									<a href='/freeUpdateFrm?freeNo=${f.freeNo }' class="btn btn-primary">수정하기</a>
									<a href='/freeDelete?freeNo=${f.freeNo }' class="btn btn-primary">삭제하기</a>
							</c:if>
					</th>
				</tr>
			</table>
		<br><br><br>
			
		</fieldset>
		
		<%-- 댓글 --%>
		<c:if test="${not empty sessionScope.m && (sessionScope.m.memberLevel eq 1 || sessionScope.m.memberLevel eq 9) }"> 
			<div class="inputCommentBox">
			<form action="/insertComment" method="post" style="border: 1px solid #9e9e9e;">
			<p>&nbsp&nbsp&nbsp댓글</p>
			<ul>		
				<li>
					<i class="fas fa-user-circle fa-5x"></i>
				</li>
				<li>
					<input type="hidden" name="memberId" value=${sessionScope.m.memberId }>
					<input type="hidden" name="boardNo" value=${f.freeNo }>
					<input type="hidden" name="boardType" value="1">
					<textarea name="commentContent" class="form-control"></textarea>
				</li>
				<li>
					<button type="submit" class="btn btn-primary btn-lg btn-block">등록</button>
				</li>
			</ul>
			</form>
			</div>
		 </c:if> 
		
		
		<%--댓글 출력 --%>
		<div class="commentBox">
		<br>
			<c:forEach items="${list }" var = "fc">
				<ul class="comments">
						<li>
							<i class="far fa-user fa-3x"></i>
							<p>${fc.memberId }</p>
							<p>${fc.regDate }</p>
						</li>
					<li>
						<p>${fc.commentContentBr }</p>
						<textarea name="commentContent" class="form-control" style="display:none;">${fc.commentContent }</textarea>
						
						<p class="commentsBtn">
						 <c:if test="${not empty sessionScope.m}"> 
						 	<c:if test="${sessionScope.m.memberId ne fc.memberId }">
						 		<div id="commentright">
						 			<form action="/reportFrm" name="reportFrm1" method="post">
									<input type="hidden" name="reportwriter" value="${fc.memberId}">
									</form>	 		
							 		<i class="fas fa-bullhorn" style="color:red"></i>
								 	<a id="func" class="report" href="javascript:void(0)">신고</a>&nbsp
								</div>	
							</c:if>
							<c:if test="${sessionScope.m.memberId eq fc.memberId }">	
									<a id="func" href="javascript:void(0)" onclick="modifyComment(this,'${fc.commentNo }','${f.freeNo }');">수정</a>
									<a id="func" href="javascript:void(0)" onclick="deleteComment(this,'${fc.commentNo }','${f.freeNo }');">삭제</a>&nbsp
						 	</c:if>
						 </c:if>
						</p>
						
						 <c:if test="${not empty sessionScope.m }"> 
							<form class="recoment">
								<div>
									<button type="submit" class="btn btn-outline-primary">등록</button>
									<button type="reset" class="btn btn-outline-primary recCancel">취소</button>
								</div>
							</form>
						</c:if> 
					</li>
				</ul>
			</c:forEach>
		</div>
	</div>
	<script>
	$(".recCancel").click(function(){
		var idx = $(".recCancel").index(this);
		$(".recoment").eq(idx).css("display","none");
	});
	function modifyComment(obj,commentNo,freeNo){
		$(obj).parent().prev().show();
		$(obj).parent().prev().prev().hide();
		$(obj).html('수정완료');
		$(obj).attr("onclick","modifyComplete(this,'"+commentNo+"','"+freeNo+"');");
		$(obj).next().html('취소');
		$(obj).next().attr("onclick","modifyCancel(this,'"+commentNo+"','"+freeNo+"');");
		$(obj).next().next().hide()
	}
	function modifyCancel(obj,commentNo,freeNo){
		$(obj).parent().prev().hide();
		$(obj).parent().prev().prev().show();
		$(obj).prev().html("수정");
		$(obj).prev().attr("onclick","modifyComment(this,'"+commentNo+"','"+freeNo+"');");
		$(obj).html("삭제");
		$(obj).attr("onclick","deleteComment(this,'"+commentNo+"','"+freeNo+"');");
		$(obj).next().show();
	}
	function modifyComplete(obj,commentNo,freeNo){
		var form = $("<form action='/updateComment' method='post'></form>");
		form.append($("<input type='text' name='commentNo' value='"+commentNo+"'>"));
		form.append($("<input type='text' name='freeNo' value='"+freeNo+"'>"));
		form.append($(obj).parent().prev());
		$("body").append(form);
		form.submit();
	}
	function deleteComment(obj,commentNo,freeNo){
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/deleteComment?commentNo="+commentNo+"&freeNo="+freeNo;
		}
	}
		$("#report").click(function(){
			window.open("","report","left=500, top=200, width=380, height=400"); 
			$("[name=reportFrm]").attr("target","report");
			$("[name=reportFrm]").submit();
		});
		$(".report").click(function(){
			window.open("","report","left=500, top=200, width=380, height=400"); 
			$("[name=reportFrm1]").attr("target","report");
			$("[name=reportFrm1]").submit();
		});
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>













