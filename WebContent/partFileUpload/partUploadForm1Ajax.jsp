<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<script src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#imgUpload').click(function(){
				alert("sys");
				
				const imgUpload = $("#partFile1")[0];
				console.log("imagUpload: ", imgUpload.files);
				
				const formData = new FormData();
				formData.append("image", imgUpload.files[0]);
				
				$.ajax({
					url:"/Model2/partUploadPro1Ajax",
					type: "post",
					data : FormData,
					enctype: 'multipart/form-data',
					processData: false,
					contentType: false,
					success: function(returnData){
						$('#imgAjax').append(returnData);
						alert("ok");
					}
				});
			});
			
			
			
		});
		/* function imgUploadFunction(){
			alert("syaa");
			
			var formData = new FormData();
			
			
			$.ajax({
				url:"/Model2/partUploadPro1Ajax",
				type: "post",
				data : FormData,
				enctype: 'multipart/form-data',
				processData: false,
				contentType: false,
				success: function(returnData){
					$('#imgAjax').append(returnData);
					alert("ok");
				}
			});
		}; */
	</script>

</head>
<body>
<!-- Part 인터페이스 이용한 단일 업로드 클라이언트 페이지 -->
	<!-- /Model2/partUploadPro1 -->
	<form id="uploadForm" action="/Model2/partUploadPro1Ajax" method="post" enctype="multipart/form-data">
	<!-- action="/프로젝트명/서블릿명" -->
		<label for="writer">작성자 : </label>
		<input type="text" name="writer" id="writer"><br>
		
		<label for="partFile1">업로드 파일 : </label>
		<input type="file" name="partFile1" id="partFile1"><br>
		<button type="button" id="imgUpload" onclick="imgUploadFunction();">이미지 추가</button>
		
		<input type="submit" value="단일업로드"  id="submit">
	</form>
	
	<div id="imgAjax"></div>
</body>
</html>