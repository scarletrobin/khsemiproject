$(function(){
	//var postTitleChk = false;
	//var postContentChk = false;
	
	$("#postReply").on("click", function(){
		return postReplyCheck();
	});
	
	function postReplyCheck(){
		var postTitle = $("#postTitle").val();
		var postContent = $("#postContent").val();
		
		if(postTitle == ""){
			alert("제목을 입력해주세요");
			$("#postTitle").focus();
			return false;
		}else if(postContent == ""){
			$("#postContent").focus();
			alert("내용을 입력해주세요");
			return false;
		}else{
			return true;
		}
	}	
});