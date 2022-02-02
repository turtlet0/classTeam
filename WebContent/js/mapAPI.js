/**
 * input 태그의 oninput 속성 이용 -> 값 입력된 경우에 해당 주소 위치 나타내는 지도 표시
 */
//oninput="mapDisplay(this.value)"
console.log(document.getElementById("roadAddress"));
/* alert("mapDisplay 1");
alert(document.getElementById("roadAddress"));
alert(document.getElementById("roadAddress").value); */
/* if(document.getElementById("roadAddress") == null || document.getElementById("roadAddress").value.equals("") 
		|| document.getElementById("roadAddress").value.equals("null")){ */
/* alert("mapDisplay() null"); */
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
  
  	// 도로명 주소로 상세 정보를 검색
geocoder.addressSearch(document.getElementById("roadAddress").value, function(results, status) {
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