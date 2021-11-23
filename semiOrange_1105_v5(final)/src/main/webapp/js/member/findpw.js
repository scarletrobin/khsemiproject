$(function(){
	var idChk = false;
	var emailChk = false;

	function checkFindPw(){
		var id = $("#memberId").val();
		var email = $("#email").val();
		if(id == "" || idChk== false){
			alert("아이디를 입력해주세요");
			return false;
		}else if(email == "" || emailChk== false){
			alert("이메일 주소를 입력해주세요");
			return false;
		}else{
			return true;
		}
	}
	
	//기존 jsp에서 사용했던 onclick을 외부 js로 뻈을시에 사용
	$("#findpw").on("click", function(){
		return checkFindPw();
	});


	$("#memberId").on("keyup", function(){
		var idReg = /^[a-z][a-z0-9]{3,11}$/;
		var idVal = $("#memberId").val();
		if(idReg.test(idVal)){
			$(".expResult").eq(0).html("올바르게 입력됐습니다.");
			$(".expResult").eq(0).css("color", "orange");
			idChk=true;
			return true;
		}else{
			$(".expResult").eq(0).html("아이디를 올바르게 입력해주세요.");
			$(".expResult").eq(0).css("color", "red");
			return false;
		}
	});

	$("#email").on("keyup", function(){
		var emailReg = /^[a-z]([-_\.]?[0-9a-z])*@[0-9a-z]([-_\.]?[0-9a-z])*\.[a-z]{2,3}$/;
		var emailVal = $("#email").val();
		if(emailReg.test(emailVal)){
			$(".expResult").eq(1).html("올바른 이메일 주소입니다.");
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