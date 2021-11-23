var resultArr = [ false, false, false, false, false ];

function checkValue() {
	if (!resultArr[0]) {
		alert("이미지를 첨부하세요.");
		return false;
	}
	if (!(resultArr[1] && resultArr[2] && resultArr[3] && resultArr[4])) {
		alert("입력값을 확인하세요.");
		return false;
	}
}

function loadImg(obj) {
	var files = obj.files;
	var divTag = document.querySelector(".preview-wrap");
	if (files.length != 0) {
		var reader = new FileReader();
		reader.readAsDataURL(files[0]);
		reader.onload = function(e) {
			var imgTag = document.createElement("img");
			imgTag.setAttribute("src", e.target.result);
			imgTag.setAttribute("class", "img-view");
			divTag.innerHTML = "";
			divTag.appendChild(imgTag);
			resultArr[0] = true;
		}
	} else {
		divTag.innerHTML = "";
		resultArr[0] = false;
	}
}

$("input[name=tradeTitle]").on("keyup", function() {
	var tradeTitle = $(this).val();
	var titleReg = /^.{2,30}$/;
	if (titleReg.test(tradeTitle)) {
		$(".titleChk").html("");
		resultArr[1] = true;
	} else {
		$(".titleChk").html("* 상품명을 2자 이상 30자 이하로 입력해주세요.");
		$(".titleChk").css("color", "#f95420");
		resultArr[1] = false;
	}
});

$("#category-select").on("change", function() {
	if ($(this).val() == "") {
		$(".cateChk").html("* 카테고리를 선택해주세요.");
		$(".cateChk").css("color", "#f95420");
		resultArr[2] = false;
	} else {
		$(".cateChk").html("");
		resultArr[2] = true;
	}
});

$("input[name=price]").on("keyup", function() {
	var price = $(this).val();
	var priceReg = /^[0-9]{1,10}$/;
	if (priceReg.test(price)) {
		$(".priceChk").html("");
		resultArr[3] = true;
	} else {
		$(".priceChk").html("* 숫자만 입력해주세요.");
		$(".priceChk").css("color", "#f95420");
		resultArr[3] = false;
	}
});

$(".local1").on("change", function() {
	var local1 = $(".local1").val();
	if (local1 > 0 && local1 < 282) {
		resultArr[4] = true;
	} else {
		resultArr[4] = false;
	}
});

$(".local1").change(function() {
	$(".local-result").val($(this).val());
});

$(".localcity")
		.change(
				function() {
					var local1city = $(this).val();
					if (local1city == "seoul") {
						var districts = [ "1|종로구", "2|중구", "3|용산구", "4|성동구",
								"5|광진구", "6|동대문구", "7|중랑구", "8|성북구", "9|강북구",
								"10|도봉구", "11|노원구", "12|은평구", "13|서대문구",
								"14|마포구", "15|양천구", "16|강서구", "17|구로구",
								"18|금천구", "19|영등포구", "20|동작구", "21|관악구",
								"22|서초구", "23|강남구", "24|송파구", "25|강동구" ];
						console.log(districts);
					} else if (local1city == "busan") {
						var districts = [ "26|중구", "27|서구", "28|동구", "29|영도구",
								"30|부산진구", "31|동래구", "32|남구", "33|북구",
								"34|해운대구", "35|사하구", "36|금정구", "37|강서구",
								"38|연제구", "39|수영구", "40|사상구", "41|기장군" ];
						console.log(districts);
					} else if (local1city == "daegu") {
						var districts = [ "42|중구", "43|동구", "44|서구", "45|남구",
								"46|북구", "47|수성구", "48|달서구", "49|달성군" ];
						console.log(districts);
					} else if (local1city == "incheon") {
						var districts = [ "50|중구", "51|동구", "52|미추홀구",
								"53|연수구", "54|남동구", "55|부평구", "56|계양구",
								"57|서구", "58|강화군", "59|옹진군" ];
						console.log(districts);
					} else if (local1city == "kwangju") {
						var districts = [ "60|동구", "61|서구", "62|남구", "63|북구",
								"64|광산구" ];
						console.log(districts);
					} else if (local1city == "deajeon") {
						var districts = [ "65|동구", "66|중구", "67|서구", "68|유성구",
								"69|대덕구" ];
						console.log(districts);
					} else if (local1city == "ulsan") {
						var districts = [ "70|중구", "71|남구", "72|동구", "73|북구",
								"74|울주군" ];
						console.log(districts);
					} else if (local1city == "sejong") {
						var districts = [ "75|세종특별자치시" ];
						console.log(districts);
					} else if (local1city == "kyeonggi") {
						var districts = [ "76|수원시", "77|성남시", "78|고양시",
								"79|용인시", "80|부천시", "81|안산시", "82|안양시",
								"83|남양주시", "84|화성시", "85|평택시", "86|의정부시",
								"87|시흥시", "88|파주시", "89|광명시", "90|김포시",
								"91|군포시", "92|광주시", "93|안산시", "94|양주시",
								"95|오산시", "96|구리시", "97|안성시", "98|포천시",
								"99|의왕시", "100|하남시", "101|여주시", "102|양평군",
								"103|동두천시", "104|과천시", "105|가평군", "106|연천군" ];
						console.log(districts);
					} else if (local1city == "kangwon") {
						var districts = [ "107|춘천시", "108|원주시", "109|강릉시",
								"110|동해시", "111|태백시", "112|속초시", "113|삼척시",
								"114|홍천군", "115|횡성군", "116|영월군", "117|평창군",
								"118|정선군", "119|철원군", "120|화천군", "121|양구군",
								"122|인제군", "123|고성군", "124|양양군" ];
						console.log(districts);
					} else if (local1city == "chungbuk") {
						var districts = [ "125|청주시", "126|충주시", "127|제천시",
								"128|보은군", "129|옥천군", "130|영동군", "131|진천군",
								"132|괴산군", "133|음성군", "134|단양군", "135|증평군" ];
						console.log(districts);
					} else if (local1city == "chungnam") {
						var districts = [ "136|천안시", "137|공주시", "138|보령시",
								"139|아산시", "140|서산시", "141|논산시", "142|계룡시",
								"143|당진시", "144|금산군", "145|부여군", "146|서천군",
								"147|청양군", "148|홍성군", "149|예산군", "150|태안군" ];
						console.log(districts);
					} else if (local1city == "jeonbuk") {
						var districts = [ "151|전주시", "152|군산시", "153|익산시",
								"154|정읍시", "155|남원시", "156|김제시", "157|완주군",
								"158|진안군", "159|무주군", "160|장수군", "161|임실군",
								"162|고창군", "163|부안군" ];
						console.log(districts);
					} else if (local1city == "jeonnam") {
						var districts = [ "164|목포시", "165|여수시", "166|순천시",
								"167|나주시", "168|광양시", "169|담양군", "170|곡성군",
								"172|고흥군", "173|보성군", "174|화순군", "175|장흥군",
								"176|강진군", "177|해남군", "178|영암군", "179|무안군",
								"180|함평군", "181|영광군", "182|장성군", "183|완도군",
								"184|진도군", "185|신안군" ];
						console.log(districts);
					} else if (local1city == "kyeongbuk") {
						var districts = [ "186|포항시", "187|경주시", "188|김천시",
								"189|안동시", "190|구미시", "191|영주시", "192|영천시",
								"193|상주시", "194|문경시", "195|경산시", "196|군위군",
								"197|의성군", "198|청송군", "199|영양군", "200|영덕군",
								"201|청도군", "202|고령군", "203|성주군", "204|칠곡군",
								"205|예천군", "206|봉화군", "207|울진군", "208|울릉군" ];
						console.log(districts);
					} else if (local1city == "kyeongnam") {
						var districts = [ "209|창원시", "210|진주시", "211|통영시",
								"212|사천시", "213|김해시", "214|밀양시", "215|거제시",
								"216|양산시", "217|의령군", "218|함안군", "219|창녕군",
								"220|고성군", "221|남해군", "222|하동군", "223|산청군",
								"224|함양군", "225|거창군", "226|합천군" ];
						console.log(districts);
					} else if (local1city == "jeju") {
						var districts = [ "227|제주시", "228|서귀포시" ];
						console.log(districts);
					}
					$(this).next().next().next().empty();
					$(this).next().next().next().append(
							"<option value=0 selected disabled>시,군,구</option>");
					for ( var district in districts) {
						var pair = districts[district].split("|");
						$(this).next().next().next().append(
								"<option value=" + pair[0] + ">" + pair[1]
										+ "</option>");
					}
				});
