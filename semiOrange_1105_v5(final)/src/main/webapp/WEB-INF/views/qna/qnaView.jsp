<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<meta charset="UTF-8">
<title>Qna상세보기</title>
<style>
	#qnaView{
		width: 800px;
		margin : 0 auto;
		margin-bottom: 100px;
	}
	
	#qnaView img{
		max-width: 600px;
	}
	
	.container fieldset>table>tbody>tr>td,.container fieldset>table>tbody>tr>th{
	height:50px;
	text-align: center;
	vertical-align: middle;
	text-overflow: ellipsis;
	overflow: hidden;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<fieldset>
			<legend style="color: #e95420; font-weight: bold; padding-left: 200px; margin-bottom: 40px;">QnA</legend>
			<table class="table" id="qnaView">
				<tr class="table-primary">
					<th colspan="3" style="text-align: left;padding-left: 50px">${q.qnaTitle }</th>
					<td>
						<i class="bi bi-megaphone-fill" style="color: red;"></i>
						<a style="text-decoration: none" href="#">신고하기</a>
					</td>
				</tr>
				<tr class="table-light">
					<th>작성자</th>
					<th>${q.memberId }</th>
					<th>작성일</th>
					<th>${q.regDate }</th>
				</tr>
				<tr class="table-light">
					<th colspan="2" style="text-align: left; padding-left: 55px;">첨부파일</th>
					<th colspan="2">
						<c:choose>
							<c:when test="${not empty q.fileName }">
							<i class="bi bi-file-arrow-down"></i>
							<a href="/fileDownQna?qnaNo=${q.qnaNo }">${q.fileName }</a>
							</c:when>
							<c:otherwise>
							<i class="bi bi-x-square-fill"></i>
							<span>첨부파일이 없습니다.</span>
							</c:otherwise>
						</c:choose>
					</th>
				</tr>
				<tr class="table-light">
					<th>내용</th>
					<th colspan="3" style="text-align: left">
						${q.qnaContentBr }
					</th>
				</tr>
				<tr class="table-light">
					<th colspan="4" style="text-align:center;">
						<button class="btn btn-info" onclick="history.go(-1)">이전화면</button>
						<c:if test="${not empty sessionScope.m && sessionScope.m.memberId eq q.memberId }">
							<a href="/qnaUpdateFrm?qnaNo=${q.qnaNo }" class="btn btn-info">수정하기</a>
							<a href="/qnaDelete?qnaNo=${q.qnaNo }" class="btn btn-danger">삭제하기</a>
						</c:if>
					</th>
				</tr>
			</table>
		</fieldset>
		<!-- 회원만 댓글달 수 있게 제약조건 달아줄곳 댓글달기버튼 -->
		<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel eq 9}">
			<div class="inputCommentBox">
				<form action="/insertCommentQna" method="post">
					<ul style="width: 800px;margin: 0 auto;margin-bottom: 30px;">
						<li>
							<i class="bi bi-person-circle" style="font-size: 3rem;"></i>
						</li>
						<li>
							<!-- 작성자 세션에서 바꿔주어야함.. -->
							<input type="hidden" name="memberId" value="${sessionScope.m.memberId }">
							<input type="hidden" name="boardNo" value="${q.qnaNo }">
							<textarea name="commentContent" class="form-control"></textarea>
						</li>
						<li>
							<button type="submit" class="btn btn-primary btn-lg btn-block">등록</button>
						</li>
					</ul>
				</form>
			</div>		
		</c:if>
		<!-- 댓글출력 -->
		<div class="commentBox">
			<c:forEach items="${list }" var="qc" varStatus="i">
					<ul class="comments" style="width:800px; margin: 0 auto; ">
						<li>
							<i class="far fa-user fa-3x"></i>
							<c:choose>
								<c:when test="${qc.memberId eq 'orangemarket' }">
								<p class="text-primary" style="font-weight: bold;">관리자</p>	
								</c:when>
								<c:otherwise>
								<p>${qc.memberId }</p>								
								</c:otherwise>
							</c:choose>
							<p>${qc.regDate }</p>
						</li>
						<li>
							<p>${qc.commentContent }</p>
							<textarea name="commentContent" class="form-control" style="display:none;">${qc.commentContent }</textarea>
							<p class="commentsBtn">
							<c:if test="${not empty sessionScope.m }">
								<!-- 세션들어오면 수정할곳 -->
								<c:if test="${sessionScope.m.memberId eq qc.memberId }">
									<a href="javascript:void(0)" class="btn btn-success" style="font-size: 14px;" onclick="modifyComment(this,'${qc.commentNo }','${q.qnaNo }')">수정</a>
									<a href="javascript:void(0)" class="btn btn-danger" style="font-size: 14px;" onclick="deleteComment(this,'${qc.commentNo }','${q.qnaNo }')">삭제</a>
									<!-- 로그인한 회원과 출력되는 댓글 작성자가 같은지 체크 -->								
								</c:if>
								<!-- <a href="javascript:void(0)" class="recShow">답글달기</a> -->
								<!-- 댓글수정,삭제,대댓글달기 버튼용 로그인 체크  -->					
							</c:if>
							<!-- if문 종료 -->
							</p>
							<!-- 대댓글구현할시 필요 -->
							<c:if test="${not empty sessionScope.m }">
							<form action="/insertComment" class="recoment">
								<input type="hidden" name="ncLevel" value="2">
								<input type="hidden" name="ncWriter" value="${sessionScope.m.memberId }">
								<input type="hidden" name="noticeRef" value="${n.noticeNo }">
								<input type="hidden" name="ncRef" value="${nc.ncNo }">
								<textarea name="ncContent" class="form-control"></textarea>
								<div>
									<button type="submit" class="btn btn-outline-primary">등록</button>
									<button type="reset" class="btn btn-outline-primary recCancel">취소</button>
								</div>
							</form>							
							</c:if>
							<%//로그인이 되어있는 경우 대댓글 작성용 form태그 미리 생성 %>
						</li>
					</ul>							
			</c:forEach><%//일반댓글 반복문%>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script type="text/javascript">
	
	function modifyComment(obj,commentNo,qnaNo){
		//textarea를 화면에 표현
		$(obj).parent().prev().show();
		//기존 본문 내용을 숨김
		$(obj).parent().prev().prev().hide();
		//수정 -> 수정완료
		$(obj).html('수정완료');
		$(obj).attr("onclick","modifyComplete(this,'"+commentNo+"','"+qnaNo+"');");
		//삭제 -> 취소
		$(obj).next().html('취소');
		$(obj).next().attr("onclick","modifyCancel(this,'"+commentNo+"','"+qnaNo+"');");
		//답글달기버튼 숨기기
		$(obj).next().next().hide();
	}
	
	function modifyCancel(obj,commentNo,qnaNo){
		//textarea 숨김 (this) 가 지금은 취소버튼임 위치 잘 생각하기!
		$(obj).parent().prev().hide();
		//기존 본문내용을 화면에 다시 표현
		$(obj).parent().prev().prev().show();
		//수정완료 -> 수정
		$(obj).prev().html("수정");
		$(obj).prev().attr("onclick","modifyComment(this,'"+commentNo+"','"+qnaNo+"');");
		//취소 -> 삭제
		$(obj).html("삭제");
		$(obj).attr("onclick","deleteComment(this,'"+commentNo+"','"+qnaNo+"');");
		//답글달기버튼 화면에 표현
		$(obj).next().show();
	}
	function modifyComplete(obj,commentNo,qnaNo){
		//새로운 form태그를 생성
		var form = $("<form action='/updateCommentQna' method='post'></form>");
		//form안에 수정댓글번호 설정
		form.append($("<input type='text' name='commentNo' value='"+commentNo+"'>"));
		//form안에 공지사항번호 설정
		form.append($("<input type='text' name='qnaNo' value='"+qnaNo+"'>"));
		//수정한 댓글 내용을 설정
		form.append($(obj).parent().prev());
		//전송할form태그를 현재 페이지에 추가
		$("body").append(form);
		form.submit();
	}
	function deleteComment(obj,commentNo,qnaNo){
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/deleteCommentQna?qnaNo="+qnaNo+"&commentNo="+commentNo;
		}
	}	
	</script>
</body>
</html>