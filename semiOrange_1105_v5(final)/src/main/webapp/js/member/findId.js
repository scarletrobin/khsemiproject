$(function(){
	var nameChk = false;
	var emailChk = false;

	function checkFindId(){
		var name = $("#memberName").val();
		var email = $("#email").val();
		if(name == "" || nameChk== false){
			alert("이름을 입력해주세요.");
			return false;
		}else if(email == "" || emailChk== false){
			alert("이메일을 입력해주세요.");
			return false;
		}else{
			return true;
		}
	}
	
	//기존 jsp에서 사용했던 onclick을 외부 js로 뻈을시에 사용
	$("#findid").on("click", function(){
		
		return checkFindId();
	});

	$("#memberName").on("keyup", function(){
		var nameReg = /^[가-힣]{2,6}$/;
		var nameVal = $("#memberName").val();
		if(nameReg.test(nameVal)){
			$(".expResult").eq(0).html("올바르게 입력됐습니다.");
			$(".expResult").eq(0).css("color", "orange");
			nameChk=true;
			return true;
		}else{
			$(".expResult").eq(0).html("이름을 올바르게 입력해주세요.");
			$(".expResult").eq(0).css("color", "red");
			return false;
		}
	});

	$("#email").on("keyup", function(){
		var emailReg = /^[a-z]([-_\.]?[0-9a-z])*@[0-9a-z]([-_\.]?[0-9a-z])*\.[a-z]{2,3}$/;
		var emailVal = $("#email").val();
		if(emailReg.test(emailVal)){
			$(".expResult").eq(1).html("올바르게 입력됐습니다.");
			$(".expResult").eq(1).css("color", "orange");
			emailChk=true;
			return true;
		}else{
			$(".expResult").eq(1).html("이메일을 올바르게 입력해주세요.");
			$(".expResult").eq(1).css("color", "red");
			return false;
		}
	});
});