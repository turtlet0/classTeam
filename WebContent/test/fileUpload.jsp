<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" enctype="multipart/form-data" id="form">

	<input type="file" name="filedata" id="imgFile" onchange="imgPreview(this);" ><br>
</form>
	<button type="button" onclick="uploadFunction();">이미지 파일 업로드</button><br>
	<div id="result"></div><br>
	<img id="img" width="150"> 

	<script type="text/javascript">
	function uploadFunction() {
		alert("uploadFunction()");
		var form = $('#form')[0];
		var data = new FormData(form);
		
		$.ajax({
			type: "post",
			enctype: 'multipart/form-data',
			url: "fileUploadPro.jsp",
			data: data,
			processData: false,
			contentType: false,
			cache: false,
			timeout: 600000,
			success: function(data) {
					alert("업로드 성공");
					$('#result').append(data);
			},
			error: function(e) {
				alert("업로드 에러");
			}
			
		});
	}
		
	
		// 이미지 미리보기
		function imgPreview(input) {
		
			if (input.files && input.files[0]) {
			   var reader = new FileReader();
			   reader.onload = function(e) {
			     document.getElementById('img').src = e.target.result;
			   };
			   reader.readAsDataURL(input.files[0]);
			 } else {
			   document.getElementById('img').src = "";
			 }
		};
	</script>
</body>
</html>