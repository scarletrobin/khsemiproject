<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Member m = (Member)session.getAttribute("m");
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- 부트스트랩 CSS -->
<link rel="stylesheet" href="/css/bootstrap.css">
<!-- 글꼴적용(NotoSans 폰트) -->
<link rel="stylesheet" href="/css/font.css">
<!-- 기본 CSS -->
<link rel="stylesheet" href="/css/default.css">
<!-- jQuery라이브러리 추가(2개) -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<!-- 부트스트랩용 jQuery -->
<script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>지역검색하기</title>
</head>
<body>
					<div class="container">
					    <div class="searchlocal-content">
					      <div class="searchlocal-header">
					        <h3 class="searchlocal-title" style="color:#e95420;">지역 검색하기</h3>
					      </div><br>
					      <div class="searchlocal-body">
					        <div class="row">
					        	<div class="col-sm-4">시,도를 선택하세요 : </div>
					        	<div class="col-sm-4">
					        		<select id="provinceSel">
					        			<option value="">시/군/구</option>
					        	    	<option value="서울">서울특별시</option>
					            		<option value="부산">부산광역시</option>
						            	<option value="대구">대구광역시</option>
						            	<option value="인천">인천광역시</option>
					    	        	<option value="광주">광주광역시</option>
						        	    <option value="대전">대전광역시</option>
						            	<option value="울산">울산광역시</option>
							            <option value="세종">세종특별자치시</option>
						    	        <option value="경기">경기도</option>
						        	    <option value="강원">강원도</option>
						            	<option value="충청북">충청북도</option>
							            <option value="충청남">충청남도</option>
						    	        <option value="전라북">전라북도</option>
						        	    <option value="전라남">전라남도</option>
						            	<option value="경상북">경상북도</option>
							            <option value="경상남">경상남도</option>
						    	        <option value="제주">제주특별자치도</option>
					        		</select>
					        	</div>
					        </div>
					         <div class="row">
					        	<div class="col-sm-4">시/군/구를 선택하세요 : </div>
					        	<div class="col-sm-4">
					        		<select id="sigunguSel">
					        		</select>
					        	</div>
					        </div>
					      </div>
					      <div class="footer"><hr>
					        <button type="button" class="save btn btn-primary">등록 완료</button>
					        <button type="button" class="close btn btn-secondary">취소</button>
					      </div>
					    </div>
					</div>
</body>
<script>
	$(function(){
		//팝업창 닫기
		$(".close").click(function(){
			window.close();
		});
		$(".save").click(function(){
			var province = $("#provinceSel").children("option:selected").html();
			var sigungu = $("#sigunguSel").children("option:selected").html();
			var sigunguNo = $("#sigunguSel").children("option:selected").val();
			//opener.document: 현재 오픈창을 연 부모 브라우저!
			//현재 창을 오픈한 부모 브라우저의 #idChk을 찾아서 처리
			$(".localchange2", opener.document).html("<input type='hidden' value="+sigunguNo+"><span>"+province+"</span> <span>"+sigungu+"</span>");
			//현재 본인 창 닫기
			self.close();
		});
		$("#provinceSel").change(function(){
			var province = $(this).children("option:selected").val();
			console.log(province);
			var sigungu = $("#sigunguSel");
			$.ajax({
				url : "/provinceSearch",
				type : "get",
				data : {province : province},
				success : function(data){
					console.log(data);
					console.log(data[0]);
					sigungu.empty();
					for(var i = 0;i<data.length;i++){
						var html = "<option value="+data[i].sigunguNo+">"+data[i].sigunguName+"</option>";
						sigungu.append(html);
					}
				}
			});
		});
	});
</script>
<style>
</style>
</html>