<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico"/>
<link rel="stylesheet" href="/fontawesome/css/all.css">
<script type="text/javascript" src="/fontawesome/js/all.js"></script>
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/font.css">
<link rel="stylesheet" href="/css/default.css">
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="/css/post/posttradewrite.css">
<title>쪽지 보내기</title>
</head>
<body>
	<div class="container">
		<div class="postreply-wrap">
		<form action="/postTradeWrite" method="post">
			<fieldset>
				<legend>거래 하기</legend>
					<table class="table table-bordered" style="width:100%;">

						<tr>
							<th class="table-active">작성자</th>
							<td>
								${sessionScope.m.memberId }
								<input type="hidden" name="postFrom" value="${sessionScope.m.memberId }">
								<input type="hidden" name="tradeNo" value="${tradeNo }">
							</td>
						</tr>
						<tr>
							<th class="table-active">수신자</th>
							<td>${postFrom }
							<input type="hidden" name="postTo" value="${postFrom }">
							</td>
						</tr>
						<tr>
							<th class="table-active">제목</th>
							<td colspan="3">
								<input type="text" name="postTitle" id="postTitle" class="form-control border-0">
							</td>
						</tr>
						<tr>
							<th class="table-active">내용</th>
							<td colspan="3">
								<!-- 한줄밖에 안되니까 textarea로 바꾼다 -->
								<textarea name="postContent" id="postContent" class="form-control border-0" style="height:300px;"></textarea>
							</td>
						</tr>
						<tr>
							<th style="border-right-style:none;"></th>
							<th colspan="3">
								<button type="button" class="btn btn-secondary" onclick="window.close();">이전</button>
								<button type="submit" class="btn btn-primary btn-block" id="postReply">쪽지 보내기</button><!--  onclick="return postReplyCheck();"있었음 -->
							</th>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="/js/post/posttradewrite.js"></script>
</body>
</html>