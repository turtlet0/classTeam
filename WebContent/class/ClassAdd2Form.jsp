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
	
	<form name="classAddForm" method="post" enctype="multipart/form-data">
	
		<input type="text" name="address" value="${sessionScope.cdto.address }" placeholder="주소작성하세요"><br>
		
		<!-- tip) file 타입 value값 입력(수정) 불가 -->
		<input type="file" name="filedata" id="imgFile" onchange="imgPreview(this);">
		<img id="img" width="150">
		<div>
			<input type="submit" value="이전" onclick='classAddSubmit(1)'>
			<input type="submit" value="다음" onclick='classAddSubmit(3)'>
			
		</div>
	</form>
	
	
	
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=af4b76efafbb11499fecf49b4f1f8f9a&libraries=services"></script>
	<script type="text/javascript" src="../js/addressMapAPI.js"></script>
	
	<script type="text/javascript">
		function imgPreview(input) {
			alert("onchange");
			/* alert(input.files);
			alert(e.target.result);
			alert(document.getElementById('img').src); */
			if (input.files && input.files[0]) {
				/* alert("input O" + e.target.result); */
			   var reader = new FileReader();
			   reader.onload = function(e) {
			     document.getElementById('img').src = e.target.result;
			   };
			   reader.readAsDataURL(input.files[0]);
			 } else {
				/* alert("input X" + e.target.result); */
			   document.getElementById('img').src = "";
			 }
		};
	</script>
	
	
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