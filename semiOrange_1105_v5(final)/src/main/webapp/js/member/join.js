$(function(){
var idChk = false;
var pwChk = false;
var pwreChk = false;
var nameChk = false;
var phoneChk = false;
var address2Chk = false;
var emailChk = false;
var emailCodeChk = false;
var local1Chk = false;

var emailChkValidate = false;




function checkJoin(){
	
	var id = $("#memberId").val();
	var pw = $("#memberPw").val();
	var pwre = $("#memberPwre").val();
	var name = $("#memberName").val();
	var phone = $("#phone").val();
	var postcode = $("#postcode").val();
	var address1 = $("#address1").val();
	var address2 = $("#address2").val();
	var email = $("#email").val();
	var emailCode = $("#authMsg").html();
	var local1 = $("#local1").val();
	var priBox = $("#agree").val();
	
	if(id == "" || idChk== false){
		alert("아이디를 올바르게 입력해주세요.");
		return false;
	}else if(pw == "" || pwChk== false){
		alert("비밀번호를 올바르게 입력해주세요.");
		return false;
	}else if(pwre == "" || pwreChk== false){
		alert("비밀번호를 다시 한 번 입력해주세요.");
		return false;
	}else if(name == "" || nameChk== false){
		alert("이름을 올바르게 입력해주세요.");
		return false;
	}else if(phone == "" || phoneChk== false){
		alert("전화번호를 올바르게 입력해주세요.");
		return false;
	}else if(postcode == "" || address1 == "" || address2 == "" || address2Chk== false){
		alert("주소를 올바르게 입력해주세요.");
		return false;
	}else if(email == "" || emailChk== false || emailChkValidate==false){
		alert("이메일을 올바르게 입력해주세요.");
		return false;
	}else if(emailCode != "인증성공" || emailCodeChk == false){
		alert("인증번호를 입력해주세요.")
		return false;
	}else if(local1 < 0 || local1 > 228 || local1Chk== false){
		alert("관심지역을 선택해주세요.");
		return false;
	}else if(priBox == "9"){
		alert("필수 항목에 체크해주세요.");
		return false;
	}else{
		return true;
	}
	
}


$("#joinsubmit").on("click", function(){
	return checkJoin();
});

$("#emailbutton").on("click", function(){
	return sendMail();
});

$("#findpostcode").on("click", function(){
	return findAddr();
});
//아이디
$("#memberId").on("keyup", function(){
	var idReg = /^[a-z][a-z0-9]{3,11}$/;
	var idVal = $("#memberId").val();
	if(idReg.test(idVal)){
		$(".expResult").eq(0).html("중복체크를 해주세요.");
		$(".expResult").eq(0).css("color", "orange");
		idChk=false;
		return false;
	}else{
		$(".expResult").eq(0).html("소문자 숫자 조합 4~12자로 입력해주세요.");
		$(".expResult").eq(0).css("color", "red");
		return false;
	}
});

//아이디 중복 체크
$("#memberIdChk").on("click", function(){
	var memberId = $("#memberId").val();
	var idChkReg = /^[a-z][a-z0-9]{3,11}$/;
	if(memberId == "" || !(idChkReg.test(memberId))){
		$(".expResult").eq(0).html("아이디를 올바르게 입력해주세요.");
		$(".expResult").eq(0).css("color", "red");
		return;
	}
	
	$.ajax({
		url : "/memberIdCheck",
		data : {memberId : memberId},
		type : "post",
		success : function(data){
			if(data != null){
				$(".expResult").eq(0).html("이미 존재하는 아이디 입니다.");
				$(".expResult").eq(0).css("color", "red");
				idChk=false;
			}else{
				$(".expResult").eq(0).html("사용 가능한 아이디 입니다.");
				$(".expResult").eq(0).css("color", "orange");
				idChk=true;
			}
		}
	});
});

//비밀번호
$("#memberPw").on("keyup", function(){
	var pwReg = /^[a-zA-Z0-9]{4,12}$/;
	var pwVal = $("#memberPw").val();
	if(pwReg.test(pwVal)){
		$(".expResult").eq(1).html("사용 가능한 비밀번호 입니다.");
		$(".expResult").eq(1).css("color", "orange");
		pwChk=true;
		return true;
	}else{
		$(".expResult").eq(1).html("대소문자 숫자 조합으로 입력해주세요.");
		$(".expResult").eq(1).css("color", "red");
		return false;
	}
});

//비밀번호 확인
$("#memberPwre").on("keyup", function(){
	var pwVal = $("#memberPw").val();
	var pwreVal = $("#memberPwre").val();
	if(pwVal == pwreVal){
		$(".expResult").eq(2).html("비밀번호가 일치합니다.");
		$(".expResult").eq(2).css("color", "orange");
		pwreChk=true;
		return true;
	}else{
		$(".expResult").eq(2).html("비밀번호가 일치하지 않습니다.");
		$(".expResult").eq(2).css("color", "red");
		return false;
	}
});

//이름
$("#memberName").on("keyup", function(){
	var nameReg = /^[가-힣]{2,6}$/;
	var nameVal = $("#memberName").val();
	if(nameReg.test(nameVal)){
		$(".expResult").eq(3).html("올바르게 입력됐습니다.");
		$(".expResult").eq(3).css("color", "orange");
		nameChk=true;
		return true;
	}else{
		$(".expResult").eq(3).html("이름은 한글 2~6글자로 입력해주세요");
		$(".expResult").eq(3).css("color", "red");
		return false;
	}
});

//전화번호
$("#phone").on("keyup", function(){
	var phoneReg = /^010-([0-9]{4})-([0-9]{4})$/;
	var phoneVal = $("#phone").val();
	if(phoneReg.test(phoneVal)){
		$(".expResult").eq(4).html("올바르게 입력됐습니다.");
		$(".expResult").eq(4).css("color", "orange");
		phoneChk=true;
		return true;
	}else{
		$(".expResult").eq(4).html("전화번호를 올바르게 입력해주세요.");
		$(".expResult").eq(4).css("color", "red");
		return false;
	}
	
});

//상세주소
$("#address2").on("keyup", function(){
	var address2 = $("#address2").val();
	if(address2 != null){
		$(".expResult").eq(5).html("올바르게 입력됐습니다.");
		$(".expResult").eq(5).css("color", "orange");
		address2Chk=true;
		return true;
	}else{
		$(".expResult").eq(5).html("주소를 입력해주세요.");
		$(".expResult").eq(5).css("color", "red");
		return false;
	}
});

//관심지역
$("#local1").on("click", function(){
	var local1Val = $("#local1").val();
	var local1selVal = $("#local1sel").val();
	if(local1selVal!="sel" && local1Val != ""){
		local1Chk=true;
		return true;
	}else{
		$(".expResult").eq(7).html("관심 지역을 선택해주세요");
		$(".expResult").eq(7).css("color", "red");
		return false;
	}
});

//이메일 수정한것(중복체크 추가)
$("#email").on("keyup", function(){
	var emailReg = /^[0-9a-z]([-_\.]?[0-9a-z])*@[0-9a-z]([-_\.]?[0-9a-z])*\.[a-z]{2,3}$/;
	var email = $("#email").val();
	if(emailReg.test(email)){
		$.ajax({
			url : "/memberEmailCheck",
			data : {email : email},
			type : "post",
			success : function(data){
				if(data != null){
					$(".expResult").eq(6).html("이미 사용중인 이메일 입니다.");
					$(".expResult").eq(6).css("color", "green");
					emailChk=false;
					emailChkValidate=false;
				}else{
					$(".expResult").eq(6).html("사용 가능한 이메일 입니다.");
					$(".expResult").eq(6).css("color", "orange");
					emailChk=true;
					emailChkValidate=true;
				}
			}
		});
		
	}else{
		$(".expResult").eq(6).html("이메일을 올바르게 입력해주세요.");
		$(".expResult").eq(6).css("color", "red");
		return false;
	}
});


//체크박스 전체 체크
$('#chkAllBox').on('click',function(){
	if($('#chkAllBox').is(':checked')){
		$('.priBox').prop('checked',true);
		$('#agree').val(1);
	}else{
		$('.priBox').prop('checked',false);
		$('#agree').val(9);
	}
});

$('.priBox').on('click', function(){
    if($('.priBox:checked').length==$('.priBox').length){
        $('#chkAllBox').prop('checked',true);
    }else{
		$('#chkAllBox').prop('checked',false);
    }
});

//체크박스에 따른 값 변경 (1->선택 동의까지 0->필수동의만 9->아무것도 안함)
$(".priBox").on("click", function(){
	if($(".priBox").eq(0).is(":checked") && $(".priBox").eq(1).is(":checked") && $(".priBox").eq(2).is(":checked")){
		$('#agree').val(1);
	}else if($(".priBox").eq(0).is(":checked") && $(".priBox").eq(1).is(":checked")){
		$('#agree').val(0);
	}else if($(".priBox").eq(0).is(":checked") && $(".priBox").eq(2).is(":checked")){
		$('#agree').val(9);
	}else if($(".priBox").eq(1).is(":checked") && $(".priBox").eq(2).is(":checked")){
		$('#agree').val(9);
	}
	
});

//이메일 인증 번호 보냄
var mailCode;
function sendMail(){
	var email = $("#email").val();
	if(email == ""){
		$(".expResult").eq(6).html("이메일을 입력해주세요.");
		$(".expResult").eq(6).css("color", "red");
		return;
	}else if(emailChkValidate == false){
		$(".expResult").eq(6).html("이메일을 올바르게 입력해주세요.");
		$(".expResult").eq(6).css("color", "red");
		return;
	}else if(emailChk == false){
		$(".expResult").eq(6).html("이메일을 올바르게 입력해주세요.");
		$(".expResult").eq(6).css("color", "red");
		return;
	}else{
		$.ajax({
			url : "/sendMailAuthCode",
			data : {email : email},
			type : "post",
			success : function(data){
				mailCode = data;
				$("#auth").slideDown();
				authTime();
			},
		});
	}
}

//이메일 인증시 시간 설정
var intervalId;
function authTime(){
	$("#timeZone").html("<span id='min'>5</span> : <span id='sec'>00</span>");
	intervalId = window.setInterval(function(){
		timeCount();
	}, 1000); //밀리세컨드 단위 
}

//이메일 인증 시간 카운트
function timeCount(){
	var min = Number($("#min").html());
	var sec = $("#sec").html();
	if(sec == "00"){
		if(min==0){
			mailCode = null;
			clearInterval(intervalId);
			$("#authMsg").html("인증시간 만료");
			$("#authMsg").css("color", "red");
		}else{
			$("#min").html(--min);			
			$("#sec").html(59);			
		}
	}else{
		var newSec = Number(sec);
		newSec--;
		if(newSec<10){ //한자리수일때
			$("#sec").html("0"+newSec);
		}else{
			$("#sec").html(newSec);
		}
	}
}

//이메일 인증 시간
$("#authBtn").click(function(){
	if(mailCode == null){
		$("#authMsg").html("인증시간 만료");
		$("#authMsg").css("color", "red");
		emailCodeChk = false;
	}else{
		if($("#authCode").val() == mailCode){
			emailCodeChk = true;
			$("#authMsg").html("인증성공");
			$("#authMsg").css("color", "blue");
			clearInterval(intervalId);
			$("#timeZone").empty();
		}else{
			$("#authMsg").html("인증코드 확인");
			$("#authMsg").css("color", "red");
			emailCodeChk = false;
		}
	}
});

function findAddr() {
	new daum.Postcode({
		oncomplete : function(data) {
			$("#postcode").val(data.zonecode);
			if (data.userSelectedType === 'R') {
				$("#address1").val(data.roadAddress);
			} else {
				$("#address1").val(data.jibunAddress);
			}
			$("#address2").focus();
		}
	}).open();
}


//시, 군, 구 선택
            $(".local1").change(function(){
                $(".result").html("<h1>"+$(this).val()+"</h1>");
            })

            $(".localsel").change(function(){
                var local1city = $(this).val();

                if(local1city == "seoul"){
                    var districts = ["1|종로구", "2|중구", "3|용산구", "4|성동구", "5|광진구", "6|동대문구", "7|중랑구", "8|성북구", "9|강북구", "10|도봉구", "11|노원구", "12|은평구", "13|서대문구", "14|마포구", "15|양천구", "16|강서구", "17|구로구", "18|금천구", "19|영등포구", "20|동작구", "21|관악구", "22|서초구", "23|강남구", "24|송파구", "25|강동구"];
                }else if(local1city == "busan"){
                    var districts = ["26|중구", "27|서구", "28|동구", "29|영도구", "30|부산진구", "31|동래구", "32|남구", "33|북구", "34|해운대구", "35|사하구", "36|금정구", "37|강서구", "38|연제구", "39|수영구", "40|사상구", "41|기장군"];
                }else if(local1city == "daegu"){
                    var districts = ["42|중구", "43|동구", "44|서구", "45|남구", "46|북구", "47|수성구", "48|달서구", "49|달성군"];
                }else if(local1city == "incheon"){
                    var districts = ["50|중구", "51|동구", "52|미추홀구", "53|연수구", "54|남동구", "55|부평구", "56|계양구", "57|서구", "58|강화군", "59|옹진군"];
                }else if(local1city == "kwangju"){
                    var districts = ["60|동구", "61|서구", "62|남구", "63|북구", "64|광산구"];
                }else if(local1city == "deajeon"){
                    var districts = ["65|동구", "66|중구", "67|서구", "68|유성구", "69|대덕구"];
                }else if(local1city == "ulsan"){
                    var districts = ["70|중구", "71|남구", "72|동구", "73|북구", "74|울주군"];
                }else if(local1city == "sejong"){
                    var districts = ["75|세종특별자치시"];
                }else if(local1city == "kyeonggi"){
                    var districts = ["76|수원시", "77|성남시", "78|고양시", "79|용인시", "80|부천시", "81|안산시", "82|안양시", "83|남양주시", "84|화성시", "85|평택시", "86|의정부시", "87|시흥시", "88|파주시", "89|광명시", "90|김포시", "91|군포시", "92|광주시", "93|안산시", "94|양주시", "95|오산시", "96|구리시", "97|안성시", "98|포천시", "99|의왕시", "100|하남시", "101|여주시", "102|양평군", "103|동두천시", "104|과천시", "105|가평군", "106|연천군"];
                }else if(local1city == "kangwon"){
                    var districts = ["107|춘천시", "108|원주시", "109|강릉시", "110|동해시", "111|태백시", "112|속초시", "113|삼척시", "114|홍천군", "115|횡성군", "116|영월군", "117|평창군", "118|정선군", "119|철원군", "120|화천군", "121|양구군", "122|인제군", "123|고성군", "124|양양군"];
                }else if(local1city == "chungbuk"){
                    var districts = ["125|청주시", "126|충주시", "127|제천시", "128|보은군", "129|옥천군", "130|영동군", "131|진천군", "132|괴산군", "133|음성군", "134|단양군", "135|증평군"];
                }else if(local1city == "chungnam"){
                    var districts = ["136|천안시", "137|공주시", "138|보령시", "139|아산시", "140|서산시", "141|논산시", "142|계룡시", "143|당진시", "144|금산군", "145|부여군", "146|서천군", "147|청양군", "148|홍성군", "149|예산군", "150|태안군"];
                }else if(local1city == "jeonbuk"){
                    var districts = ["151|전주시", "152|군산시", "153|익산시", "154|정읍시", "155|남원시", "156|김제시", "157|완주군", "158|진안군", "159|무주군", "160|장수군", "161|임실군", "162|고창군", "163|부안군"];
                }else if(local1city == "jeonnam"){
                    var districts = ["164|목포시", "165|여수시", "166|순천시", "167|나주시", "168|광양시", "169|담양군", "170|곡성군", "172|고흥군", "173|보성군", "174|화순군", "175|장흥군", "176|강진군", "177|해남군", "178|영암군", "179|무안군", "180|함평군", "181|영광군", "182|장성군", "183|완도군", "184|진도군", "185|신안군"];
                }else if(local1city == "kyeongbuk"){
                    var districts = ["186|포항시", "187|경주시", "188|김천시", "189|안동시", "190|구미시", "191|영주시", "192|영천시", "193|상주시", "194|문경시", "195|경산시", "196|군위군", "197|의성군", "198|청송군", "199|영양군", "200|영덕군", "201|청도군", "202|고령군", "203|성주군", "204|칠곡군", "205|예천군", "206|봉화군", "207|울진군", "208|울릉군"];
                }else if(local1city == "kyeongnam"){
                    var districts = ["209|창원시", "210|진주시", "211|통영시", "212|사천시", "213|김해시", "214|밀양시", "215|거제시", "216|양산시", "217|의령군", "218|함안군", "219|창녕군", "220|고성군", "221|남해군", "222|하동군", "223|산청군", "224|함양군", "225|거창군", "226|합천군"];
                }else if(local1city == "jeju"){
                    var districts = ["227|제주시", "228|서귀포시"];
                }

                $(this).next().empty();
                $(this).next().append("<option value=0 selected disabled>시,군,구</option>");
                for (var district in districts) {
                    var pair = districts[district].split("|");
                    $(this).next().append("<option value="+pair[0]+">"+pair[1]+"</option>");
                }                
            });
});