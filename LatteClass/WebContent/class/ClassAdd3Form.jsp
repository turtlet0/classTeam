<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>0
</head>
<body>
	<!-- WebContent/class/ClassAdd3Form.jsp -->
	<!-- 클래스 등록3 페이지 -->
	tdto ${sessionScope.tdto } <hr>
	cdto ${sessionScope.cdto } <hr>
	<p>클래스 등록</p>
	<hr>
	<div>
		<div>
			<span>1 기본 정보 입력</span>
			<span>--</span>
			<span>2 클래스 상세 소개</span>
			<span>--</span>
			<span>3 클래스 위치 등록</span>
		</div>
		<hr>
		tdto ${sessionScope.tdto } <br>
		cdto ${sessionScope.cdto }
		<form name="classAddForm" method="post">
			<input type="text" name="currentIdx" value="3" hidden="hidden"><!-- Action으로 넘어오기 전 현재 페이지 Idx 확인 위해 전달 -->
				
			<div>
				<p><b>*</b>8. 클래스 개설 지역</p>
				<div>
					<label><input name="opening_area" type="radio" value="서울"><span>서울</span></label>
					<label><input name="opening_area" type="radio" value="경기도"><span>경기도</span></label>
					<label><input name="opening_area" type="radio" value="부산"><span>부산</span></label>
					<label><input name="opening_area" type="radio" value="인천"><span>인천</span></label>
					<label><input name="opening_area" type="radio" value="대구"><span>대구</span></label>
					<label><input name="opening_area" type="radio" value="울산"><span>울산</span></label>
					<label><input name="opening_area" type="radio" value="광주"><span>광주</span></label>
					<label><input name="opening_area" type="radio" value="경상남도"><span>경상남도</span></label>
					<label><input name="opening_area" type="radio" value="경상북도"><span>경상북도</span></label>
					<label><input name="opening_area" type="radio" value="전라남도"><span>전라남도</span></label>
					<label><input name="opening_area" type="radio" value="전라북도"><span>전라북도</span></label>
					<label><input name="opening_area" type="radio" value="충청남도"><span>충청남도</span></label>
					<label><input name="opening_area" type="radio" value="충청북도"><span>충청북도</span></label>
					<label><input name="opening_area" type="radio" value="강원도"><span>강원도</span></label>
					<label><input name="opening_area" type="radio" value="제주도"><span>제주도</span></label>
					<label><input name="opening_area" type="radio" value="세종"><span>세종</span></label>
				</div>
			</div>
			<br>
			<div>
				<p><b>*</b>9. 상세 주소 입력(도로명 또는 지번 검색)</p>
				<!-- 우편주소 검색 -->
				<div>
					<input type="text" id="postcode" name="postcode" placeholder="우편번호" value="${sessionScope.cdto.postcode }">
					<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="roadAddress" name="road_address" placeholder="도로명주소"  
						value="${sessionScope.cdto.road_address }" >
					<input type="text" id="jibunAddress" name="jibun_address" placeholder="지번주소" value="${sessionScope.cdto.jibun_address }">
					<span id="guide" style="color:#999;display:none"></span>
					<input type="text" id="detailAddress" name="detail_address" placeholder="상세주소" value="${sessionScope.cdto.detail_address }">
					<input type="text" id="sido" name="sido" hidden="hidden" value="${sessionScope.cdto.sido }"> <!-- 도/시 이름 ex) 경기 -->
					<input type="text" id="sigungu" name="sigungu" hidden="hidden" value="${sessionScope.cdto.sigungu }"> <!-- 시/군/구 이름 ex) 성남시 분당구 -->
					<input type="text" id="bname" name="bname" hidden="hidden" value="${sessionScope.cdto.bname }"> <!-- 법정동/법정리 이름 ex) 삼평동 -->
				</div>
				 
			</div>
			<br>
			<div>
				<p>10. 찾아오는 길 추가 설명</p>
				<input type="text" id="directions">
				<!-- 우편주소 검색 시 지도 display -->
				<div id="map" style="width:300px;height:300px;margin-top:10px;display:none" ></div>
			</div>
			<br>
			<div>
				<p>11. 편의사항 및 제공 사항</p>
				<div>
					<label><input name="convenience_option" type="checkbox" value="wifi"><span>와이파이를 제공합니다</span></label>
					<label><input name="convenience_option" type="checkbox" value="parking"><span>주차공간을 제공합니다</span></label>
					<label><input name="convenience_option" type="checkbox" value="refreshments"><span>간단한 음료 및 다과를 제공합니다</span></label>
					<label><input name="convenience_option" type="checkbox" value="package"><span>포장 용기 및 백을 제공합니다</span></label>
					<label><input name="convenience_option" type="checkbox" value="photoZone"><span>포토존이 있어요</span></label>
					<label><input name="convenience_option" type="checkbox" value="pets"><span>반려 동물 동반 가능해요</span></label>
					<label><input name="convenience_option" type="checkbox" value="protectiveEquipment"><span>앞치마 및 장갑을 제공합니다</span></label>
					<label>
						<input name="convenience_option" type="checkbox" value="etc" >
						<span>기타</span>
						<input type="text" placeholder="기타 분류 입력">
					</label>
				</div>
			</div>
			<br>
			<div>
				<p>12. 건강/안전 유의 사항</p>
				<div>
					<label><input name="health_safety_option" type="checkbox" value="detergent"><span>손세정제가 비치 되어있어요</span></label>
					<label><input name="health_safety_option" type="checkbox" value="disinfection"><span>실내 소독을 실시 합니다</span></label>
					<label><input name="health_safety_option" type="checkbox" value="distance"><span>거리두기를 준수해요</span></label>
					<label>
						<input name="health_safety_option" type="checkbox" value="etc" >
						<span>기타</span>
						<input type="text" placeholder="기타 분류 입력">
					</label>
				</div>
			</div>
			<br>	
			<div>
				<input type="submit" value="이전" onclick='classAddSubmit(2)'>
				<input type="submit" value="최종 등록" onclick='classAddSubmit(4)'>
			</div>
		</form>
	</div>
	
	
	
	
<!-- 스크립트 영역 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=af4b76efafbb11499fecf49b4f1f8f9a&libraries=services"></script>
	<!-- <script type="text/javascript" src="../js/addressMapAPI.js"></script> -->
	<script><%@ include file="../js/addressMapAPI.js" %></script>
	<script><%@ include file="../js/mapAPI.js" %></script>
	
	<script>
		function classAddSubmit(idx){
			if(idx == 1) {
				classAddForm.action = './ClassAddAction.cl?idx=1';
				classAddForm.submit();
			}
			else if(idx == 2) {
				classAddForm.action = './ClassAddAction.cl?idx=2';
				classAddForm.submit();
			}
			else if(idx == 3) {
				classAddForm.action = './ClassAddAction.cl?idx=3';
				classAddForm.submit();
			}
			else if(idx == 4) {
				classAddForm.action = './ClassAddAction.cl?idx=4';
				classAddForm.submit();
			}
		}
	</script>
</body>
</html>