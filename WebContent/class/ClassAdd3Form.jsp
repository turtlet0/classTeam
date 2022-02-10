<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- WebContent/class/ClassAdd3Form.jsp -->
	<!-- 클래스 등록3 페이지 -->
	
	<p>클래스 등록</p>
	<div>
		<div>
			<span>1 기본 정보 입력</span>
			<span>--</span>
			<span>2 클래스 상세 소개</span>
			<span>--</span>
			<span>3 클래스 위치 등록</span>
		</div>
		
		<form  method="post">	
			<div>
				<p><b>*</b>8. 클래스 개설 지역</p>
				<div>
					<label><input name="openArea" type="radio" value="서울"><span>서울</span></label>
					<label><input name="openArea" type="radio" value="경기도"><span>경기도</span></label>
					<label><input name="openArea" type="radio" value="부산"><span>부산</span></label>
					<label><input name="openArea" type="radio" value="인천"><span>인천</span></label>
					<label><input name="openArea" type="radio" value="대구"><span>대구</span></label>
					<label><input name="openArea" type="radio" value="울산"><span>울산</span></label>
					<label><input name="openArea" type="radio" value="광주"><span>광주</span></label>
					<label><input name="openArea" type="radio" value="경상남도"><span>경상남도</span></label>
					<label><input name="openArea" type="radio" value="경상북도"><span>경상북도</span></label>
					<label><input name="openArea" type="radio" value="전라남도"><span>전라남도</span></label>
					<label><input name="openArea" type="radio" value="전라북도"><span>전라북도</span></label>
					<label><input name="openArea" type="radio" value="충청남도"><span>충청남도</span></label>
					<label><input name="openArea" type="radio" value="충청북도"><span>충청북도</span></label>
					<label><input name="openArea" type="radio" value="강원도"><span>강원도</span></label>
					<label><input name="openArea" type="radio" value="제주도"><span>제주도</span></label>
					<label><input name="openArea" type="radio" value="세종"><span>세종</span></label>
				</div>
			</div>
			
			<div>
				<p><b>*</b>9. 상세 주소 입력(도로명 또는 지번 검색)</p>
				<!-- 우편주소 검색 -->
				
				<div>
					<input type="text" id="postcode2" placeholder="우편번호">
					<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="roadAddress2" name="roadAddress" placeholder="도로명주소"  
						value="${sessionScope.cdto.roadAddress }" >
					<input type="text" id="jibunAddress2" placeholder="지번주소">
					<span id="guide2" style="color:#999;display:none"></span>
					<input type="text" id="detailAddress2" placeholder="상세주소">
				</div>
				 
			</div>
			
			<div>
				<p><b>*</b>10. 찾아오는 길 추가 설명</p>
				<input type="text" id="directions">
					<!-- 우편주소 검색 시 display -->
				<div id="map" style="width:300px;height:300px;margin-top:10px;display:none" ></div>
			</div>
			
			<div>
				<p><b>*</b>11. 편의사항 및 제공 사항</p>
				<div>
					<label><input name="" type="checkbox" value="wifi"><span>와이파이를 제공합니다</span></label>
					<label><input name="" type="checkbox" value="parking"><span>주차공간을 제공합니다</span></label>
					<label><input name="" type="checkbox" value="refreshments"><span>간단한 음료 및 다과를 제공합니다</span></label>
					<label><input name="" type="checkbox" value="package"><span>포장 용기 및 백을 제공합니다</span></label>
					<label><input name="" type="checkbox" value="photoZone"><span>포토존이 있어요</span></label>
					<label><input name="" type="checkbox" value="pets"><span>반려 동물 동반 가능해요</span></label>
					<label><input name="" type="checkbox" value="protectiveEquipment"><span>앞치마 및 장갑을 제공합니다</span></label>
					<label>
						<input name="" type="checkbox" value="etc" >
						<span>기타</span>
						<input type="text" placeholder="기타 분류 입력">
					</label>
				</div>
			</div>
			
			<div>
				<p><b>*</b>12. 건강/안전 유의 사항</p>
				<div>
					<label><input name="" type="checkbox" value="detergent"><span>손세정제가 비치 되어있어요</span></label>
					<label><input name="" type="checkbox" value="disinfection"><span>실내 소독을 실시 합니다</span></label>
					<label><input name="" type="checkbox" value="distance"><span>거리두기를 준수해요</span></label>
					<label>
						<input name="" type="checkbox" value="etc" >
						<span>기타</span>
						<input type="text" placeholder="기타 분류 입력">
					</label>
				</div>
			</div>

			<div>
				<input type="submit" value="이전" onclick='classAddSubmit(2)'>
				<input type="submit" value="최종 등록" name="">
			</div>
		</form>
	</div>
	
	
<!-- ///////////////////////// -->
<!-- 이하 연습 코드 -->			
	<h1>WebContent/class/ClassAdd2Form.jsp</h1>
	
	<!-- 클래스 등록1 cdto 객체 정보 -->
	<h2> request ${requestScope.cdto }</h2> 
	1. ${cdto }
	2. ${requestScope.cdto.content }
	<h2> session ${sessionScope.cdto }</h2> 
	<h2> session address ${sessionScope.cdto.address }</h2> 
	<h2> session content ${sessionScope.cdto.content }</h2> 
	
	<form name="classAddForm" method="post">
	
		<input type="text" name="className" value="${sessionScope.cdto.className }" placeholder="클래스명 입력하세요">
	
		<!-- 우편주소 검색 -->
		<div>
			<input type="text" id="postcode" placeholder="우편번호" value="${sessionScope.cdto.postcode }">
			<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" id="roadAddress" name="roadAddress" placeholder="도로명주소"  
				value="${sessionScope.cdto.roadAddress }" >
			<input type="text" id="jibunAddress" placeholder="지번주소" value="${sessionScope.cdto.jibunAddress }">
			<span id="guide" style="color:#999;display:none"></span>
			<input type="text" id="detailAddress" placeholder="상세주소" value="${sessionScope.cdto.detailAddress }">
			<input type="text" id="extraAddress" placeholder="참고항목" value="${sessionScope.cdto.extraAddress }">
		</div>
		
		<!-- 지도 -->
			<!-- 우편주소 검색 시 display -->
		<div id="map" style="width:300px;height:300px;margin-top:10px;display:none" ></div>
		<!-- <div id="map2" style="width:500px;height:500px;margin-top:10px;display:none" oninput="mapDisplay()"></div> -->
	 
		<div>
			<input type="submit" value="이전" onclick='classAddSubmit(2)'>
			<input type="submit" value="최종 등록" name="">
			
		</div>
	</form>
	
	
	
	
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
		}
	</script>
</body>
</html>