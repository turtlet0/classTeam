/**
 * 우편주소 검색해 input 태그에 담고,
 * 해당 주소의 위치를 지도에 표시해 출력
 */
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
            // 도로명 주소의 노출 규칙에 따라 주소를 표시.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝남
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("roadAddress").value = roadAddr;
            document.getElementById("jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
            
            ////////////////////////////////////////////////////////////////////////
            //지도 
            // 도로명주소 받아 해당 위치 지도로 출력
            var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		    mapOption = {
		        center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
		        level: 5 // 지도의 확대 레벨
		    };
		
		  //지도를 미리 생성
		  var map = new daum.maps.Map(mapContainer, mapOption);
		  //주소-좌표 변환 객체를 생성
		  var geocoder = new daum.maps.services.Geocoder();
		  //마커를 미리 생성
		  var marker = new daum.maps.Marker({
		      position: new daum.maps.LatLng(37.537187, 127.005476),
		      map: map
		  });
		  
       	  // 주소로 상세 정보를 검색
          geocoder.addressSearch(data.roadAddress, function(results, status) {
          	// 정상적으로 검색이 완료됐으면
            if (status === daum.maps.services.Status.OK) {

            	var result = results[0]; //첫번째 결과의 값을 활용

                // 해당 주소에 대한 좌표를 받아서
                var coords = new daum.maps.LatLng(result.y, result.x);
                // 지도를 보여줌.
                mapContainer.style.display = "block";
                map.relayout();
                // 지도 중심을 변경한다.
                map.setCenter(coords);
                // 마커를 결과값으로 받은 위치로 옮김.
                marker.setPosition(coords)
                
                //
                // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성
                var mapTypeControl = new kakao.maps.MapTypeControl();

                // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
                // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미
                map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

                // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성
                var zoomControl = new kakao.maps.ZoomControl();
                map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
             }
         });
        }
    }).open();
}