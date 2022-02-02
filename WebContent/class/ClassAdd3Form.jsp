<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- WebContent/class/addClassForm.jsp -->
	<!-- 클래스 등록 페이지 -->
	
	
	
	
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
			<input type="text" id="postcode" placeholder="우편번호">
			<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" id="roadAddress" name="roadAddress" placeholder="도로명주소"  
				value="${sessionScope.cdto.roadAddress }" >
			<input type="text" id="jibunAddress" placeholder="지번주소">
			<span id="guide" style="color:#999;display:none"></span>
			<input type="text" id="detailAddress" placeholder="상세주소">
			<input type="text" id="extraAddress" placeholder="참고항목">
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