$(function(){
	var postIdChk = false;
	function postWriteCheck(){
		var postVal = $("#postTo").val();
		var postTitle = $("#postTitle").val();
		var postContent = $("#postContent").val();
		if(postTitle == ""){
			alert("쪽지 제목을 입력해주세요.");
			$("#postTitle").focus();
			return false;
		}else if(postVal == "" || postIdChk == false){
			$("#chkResult").html("보내실 회원 아이디를 입력해주세요.");
			$("#chkResult").css("color", "red");
			$("#postTo").focus();
			return false;	
		}else if(postContent == ""){
			alert("쪽지 내용을 입력해주세요.");
			$("#postContent").focus();
			return false;
		}else{
			return true;
		}
	}	
	
	$("#postWrite").on("click", function(){
		return postWriteCheck();
	});
	
	$("#postIdChk").click(function(){
		var memberId = $(this).prev().val();
		if(memberId == ""){
			$("#chkResult").html("보내실 회원 아이디를 입력해주세요.");
			$("#chkResult").css("color", "blue");
			return;
		}
		$.ajax({
			url : "/postIdCheck",
			data : {memberId:memberId},
			type : "post",
			success : function(data){
				if(data != null){
					console.log(data);
					$("#chkResult").html("가능합니다.");
					$("#chkResult").css("color", "orange");
					postIdChk = true;	
				}else {
					console.log(data);
					$("#chkResult").html("존재하지 않는 회원 입니다.");
					$("#chkResult").css("color", "red");
					console.log(data);
					postIdChk = false;	
				}
			}
		});
	});
});