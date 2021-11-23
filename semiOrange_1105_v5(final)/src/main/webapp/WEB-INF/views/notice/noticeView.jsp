<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<style>
	#noticeViewTable{
		width: 800px;
		margin : 0 auto;
		margin-bottom: 100px;
	}
	#noticeViewTable img{
		max-width: 500px;
	}
	.container fieldset>table>tbody>tr>td,.container fieldset>table>tbody>tr>th{
	height:50px;
	text-align: center;
	vertical-align: middle;
	text-overflow: ellipsis;
	overflow: hidden;
	}
	.commentsBtn{
		float : right;
		padding-right : 10px;
	}
	#commentright{
		display: inline-block;
		text-align: right;
	}
	#report{
		margin-right: 10px;	
		}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<fieldset>
			<legend style="color: #e95420; font-weight: bold; padding-left: 200px; margin-bottom: 40px;">공지사항</legend>
			<table class="table" id="noticeViewTable">
				<tr class="table-primary">
					<th colspan="4" style="text-align: left;padding-left: 50px">${n.noticeTitle }</th>			
				</tr>
				<tr>
					<th>작성자</th>
					<th>${n.memberId }</th>
					<th>작성일</th>
					<th>${n.regDate }</th>
				</tr>
				<tr>
					<th colspan="2" style="text-align: left; padding-left: 55px;">첨부파일</th>
					<th colspan="2">
						<c:choose>
							<c:when test="${not empty n.filename }">
							<i class="bi bi-file-arrow-down"></i>
							<a href="/fileDownNotice?noticeNo=${n.noticeNo }">${n.filename }</a>
							</c:when>
							<c:otherwise>
							<i class="bi bi-x-square-fill"></i>
							<span>첨부파일이 없습니다.</span>
							</c:otherwise>
						</c:choose>					
					</th>
				</tr>
				<tr>
					<th>내용</th>
					<th colspan="3" style="text-align: left">
						${n.noticeContentBr }
					</th>
				</tr>
				<tr>
					<th colspan="4" style="text-align:center;">
						<button class="btn btn-info" onclick="history.go(-1)">이전화면</button>
						<c:if test="${not empty sessionScope.m && sessionScope.m.memberId eq n.memberId }">
							<a href="/noticeUpdateFrm?noticeNo=${n.noticeNo }" class="btn btn-info">수정하기</a>
							<a href="/noticeDelete?noticeNo=${n.noticeNo }" class="btn btn-danger">삭제하기</a>
						</c:if>
					</th>
				</tr>
			</table>
		</fieldset>
		<!-- 회원만 댓글달 수 있게 제약조건 달아줄곳 댓글달기버튼 -->
		<c:if test="${not empty sessionScope.m }">
			<div class="inputCommentBox">
				<form action="/insertCommentNotice" method="post">
					<ul style="width: 800px;margin: 0 auto;margin-bottom: 30px;">
						<li>
							<i class="bi bi-person-circle" style="font-size: 3rem;"></i>
						</li>
						<li>
							<!-- 작성자 세션에서 바꿔주어야함.. -->
							<input type="hidden" name="memberId" value="${sessionScope.m.memberId }">
							<input type="hidden" name="boardNo" value="${n.noticeNo }">
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
			<c:forEach items="${list }" var="nc" varStatus="i">
					<ul class="comments" style="width:800px; margin: 0 auto; ">
						<li>
							<i class="far fa-user fa-3x"></i>
							<c:choose>
								<c:when test="${nc.memberId eq 'orangemarket' }">
								<p class="text-primary" style="font-weight: bold;">관리자</p>	
								</c:when>
								<c:otherwise>
								<p>${nc.memberId }</p>								
								</c:otherwise>
							</c:choose>
							<p>${nc.regDate }</p>
						</li>
						<li>
							<p>${nc.commentContent }</p>
							<textarea name="commentContent" class="form-control" style="display:none;">${nc.commentContent }</textarea>
							<p class="commentsBtn">
									<c:if test="${not empty sessionScope.m }">	
										<c:if test="${sessionScope.m.memberId eq nc.memberId }">
											<a href="javascript:void(0)" class="btn btn-success" style="font-size: 14px;" onclick="modifyComment(this,'${nc.commentNo }','${n.noticeNo }')">수정</a>
											<a href="javascript:void(0)" class="btn btn-danger" style="font-size: 14px;" onclick="deleteComment(this,'${nc.commentNo }','${n.noticeNo }')">삭제</a>
										</c:if>
										<c:if test="${sessionScope.m.memberId ne nc.memberId and nc.memberId ne 'orangemarket'}">
											<div id="commentright">
												<form action="/reportFrm" name="reportFrm" method="post">
													<input type="hidden" name="reportwriter" value="${nc.memberId}">
												</form>	 		
												<i class="bi bi-megaphone-fill" style="color: red;"></i>
											 	<a style="text-decoration: none" id="report" href="javascript:void(0)">신고</a>									    
											</div> 	
										</c:if>	
									</c:if>
							</p>
						</li>
					</ul>							
			</c:forEach><%//일반댓글 반복문%>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script type="text/javascript">
	function modifyComment(obj,commentNo,noticeNo){
		//textarea를 화면에 표현
		$(obj).parent().prev().show();
		//기존 본문 내용을 숨김
		$(obj).parent().prev().prev().hide();
		//수정 -> 수정완료
		$(obj).html('수정완료');
		$(obj).attr("onclick","modifyComplete(this,'"+commentNo+"','"+noticeNo+"');");
		//삭제 -> 취소
		$(obj).next().html('취소');
		$(obj).next().attr("onclick","modifyCancel(this,'"+commentNo+"','"+noticeNo+"');");
	}
	
	function modifyCancel(obj,commentNo,noticeNo){
		//textarea 숨김 (this) 가 지금은 취소버튼임 위치 잘 생각하기!
		$(obj).parent().prev().hide();
		//기존 본문내용을 화면에 다시 표현
		$(obj).parent().prev().prev().show();
		//수정완료 -> 수정
		$(obj).prev().html("수정");
		$(obj).prev().attr("onclick","modifyComment(this,'"+commentNo+"','"+noticeNo+"');");
		//취소 -> 삭제
		$(obj).html("삭제");
		$(obj).attr("onclick","deleteComment(this,'"+commentNo+"','"+noticeNo+"');");
		//답글달기버튼 화면에 표현
		$(obj).next().show();
	}
	function modifyComplete(obj,commentNo,noticeNo){
		//새로운 form태그를 생성
		var form = $("<form action='/updateCommentNotice' method='post'></form>");
		//form안에 수정댓글번호 설정
		form.append($("<input type='text' name='commentNo' value='"+commentNo+"'>"));
		//form안에 공지사항번호 설정
		form.append($("<input type='text' name='noticeNo' value='"+noticeNo+"'>"));
		//수정한 댓글 내용을 설정
		form.append($(obj).parent().prev());
		//전송할form태그를 현재 페이지에 추가
		$("body").append(form);
		form.submit();
	}
	function deleteComment(obj,commentNo,noticeNo){
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href="/deleteCommentNotice?noticeNo="+noticeNo+"&commentNo="+commentNo;
		}
	}
	$("#report").click(function(){
		window.open("","report","left=500, top=200, width=380, height=400"); 
		$("[name=reportFrm]").attr("target","report");
		$("[name=reportFrm]").submit();
	});
	</script>
</body>
</html>