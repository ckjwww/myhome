// 데이터 요청
function requestData(url) {

	// ajax 요청 전 그리드에 로더 표시
	AUIGrid.showAjaxLoader(myGridID);
	
	// ajax (XMLHttpRequest) 로 그리드 데이터 요청
	ajax({
		url : url,
		onSuccess : function(data) {
			
			//console.log(data);
			
			// 그리드에 데이터 세팅
			// data 는 JSON 을 파싱한 Array-Object 입니다.
			AUIGrid.setGridData(myGridID, data);

			// 로더 제거
			AUIGrid.removeAjaxLoader(myGridID);
		},
		onError : function(status, e) {
			alert("데이터 요청에 실패하였습니다.\r status : " + status);
			// 로더 제거
			AUIGrid.removeAjaxLoader(myGridID);
		}
	});
};

var timerId = null;

// oreientation 속성이 있는지 여부.. 있다면 모바일임.
if(typeof window.orientation !== "undefined") {
	// 스마트폰 가로, 세로 변경된 경우 이벤트
	window.onorientationchange = function() {
		
		// 300ms 보다 빠르게 oreientation 된다면..
		if(timerId) {
			clearTimeout(timerId);
		}
		
		// 크기가 변경되었을 때 AUIGrid.resize() 함수 호출
		// setTimeout 을 사용한 이유: 스마트폰 가로, 세로를 변경했을 때 즉각적으로 DOM 의 크기가 반영되지 않음.
		// 즉, 어느정도 시간을 두고 크기가 반영됨. 이 시간 후에 AUIGrid.resize 메소드 실행함. (이 시간은 기기마다 조금씩 차이가 있음)
		timerId = setTimeout(function() {
			// 그리드 리사이징
			if(typeof myGridID != "undefined") {
				AUIGrid.resize(myGridID);
			}
			
			if(typeof myGridID2 != "undefined") {
				AUIGrid.resize(myGridID2);
			}	
		},300);  // 현재 300ms 민감도....환경에 맞게 조절하세요.
	};
} else {
	// 리사이즈 이벤트
	window.onresize = function() {
		
		// 200ms 보다 빠르게 리사이징 된다면..
		if(timerId) {
			clearTimeout(timerId);
		}
		
		timerId = setTimeout(function() {
			
			// 그리드 리사이징
			if(typeof myGridID != "undefined") {
				AUIGrid.resize(myGridID);
			}
			
			if(typeof myGridID2 != "undefined") {
				AUIGrid.resize(myGridID2);
			}	
		}, 200);  // 현재 200ms 민감도....환경에 맞게 조절하세요.
	};
}