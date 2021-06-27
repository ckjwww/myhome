// �곗씠�� �붿껌
function requestData(url) {

	// ajax �붿껌 �� 洹몃━�쒖뿉 濡쒕뜑 �쒖떆
	AUIGrid.showAjaxLoader(myGridID);
	
	// ajax (XMLHttpRequest) 濡� 洹몃━�� �곗씠�� �붿껌
	ajax({
		url : url,
		onSuccess : function(data) {
			
			//console.log(data);
			
			// 洹몃━�쒖뿉 �곗씠�� �명똿
			// data �� JSON �� �뚯떛�� Array-Object �낅땲��.
			AUIGrid.setGridData(myGridID, data);

			// 濡쒕뜑 �쒓굅
			AUIGrid.removeAjaxLoader(myGridID);
		},
		onError : function(status, e) {
			alert("�곗씠�� �붿껌�� �ㅽ뙣�섏��듬땲��.\r status : " + status);
			// 濡쒕뜑 �쒓굅
			AUIGrid.removeAjaxLoader(myGridID);
		}
	});
};

var timerId = null;

// oreientation �띿꽦�� �덈뒗吏� �щ�.. �덈떎硫� 紐⑤컮�쇱엫.
if(typeof window.orientation !== "undefined") {
	// �ㅻ쭏�명룿 媛�濡�, �몃줈 蹂�寃쎈맂 寃쎌슦 �대깽��
	window.onorientationchange = function() {
		
		// 300ms 蹂대떎 鍮좊Ⅴ寃� oreientation �쒕떎硫�..
		if(timerId) {
			clearTimeout(timerId);
		}
		
		// �ш린媛� 蹂�寃쎈릺�덉쓣 �� AUIGrid.resize() �⑥닔 �몄텧
		// setTimeout �� �ъ슜�� �댁쑀: �ㅻ쭏�명룿 媛�濡�, �몃줈瑜� 蹂�寃쏀뻽�� �� 利됯컖�곸쑝濡� DOM �� �ш린媛� 諛섏쁺�섏� �딆쓬.
		// 利�, �대뒓�뺣룄 �쒓컙�� �먭퀬 �ш린媛� 諛섏쁺��. �� �쒓컙 �꾩뿉 AUIGrid.resize 硫붿냼�� �ㅽ뻾��. (�� �쒓컙�� 湲곌린留덈떎 議곌툑�� 李⑥씠媛� �덉쓬)
		timerId = setTimeout(function() {
			// 洹몃━�� 由ъ궗�댁쭠
			if(typeof myGridID != "undefined") {
				AUIGrid.resize(myGridID);
			}
			
			if(typeof myGridID2 != "undefined") {
				AUIGrid.resize(myGridID2);
			}	
		},300);  // �꾩옱 300ms 誘쇨컧��....�섍꼍�� 留욊쾶 議곗젅�섏꽭��.
	};
} else {
	// 由ъ궗�댁쫰 �대깽��
	window.onresize = function() {
		
		// 200ms 蹂대떎 鍮좊Ⅴ寃� 由ъ궗�댁쭠 �쒕떎硫�..
		if(timerId) {
			clearTimeout(timerId);
		}
		
		timerId = setTimeout(function() {
			
			// 洹몃━�� 由ъ궗�댁쭠
			if(typeof myGridID != "undefined") {
				AUIGrid.resize(myGridID);
			}
			
			if(typeof myGridID2 != "undefined") {
				AUIGrid.resize(myGridID2);
			}	
		}, 200);  // �꾩옱 200ms 誘쇨컧��....�섍꼍�� 留욊쾶 議곗젅�섏꽭��.
	};
}