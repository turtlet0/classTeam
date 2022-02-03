<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="../js/jquery-3.6.0.min.js"></script>

</head>
<body>


	<form method="post" enctype="multipart/form-data" id="form">
		<input type="file" name="profile"/>
		<button type="button" onclick="uploadFunction();">이미지 파일 업로드</button>
	</form>
	
		<script type="text/javascript">
		alert("dsfs");
		function uploadFunction() {
			alert("uploadFunction()");
			var data = new FormData(form);
			
			$.ajax({
				type: "post",
				enctype: 'multipart/form-data',
				url: "fileUploadAction.cl",
				data: data,
				processData: false,
				contentType: false,
				cache: false,
				timeout: 600000,
				success: function(data) {
					if (data == 1) {
						alert("업로드 성공");
						$('#statusMessage').html('파일업로드 성공.');
						$('#statusMessage').css("color", "green");
					}
					else {
						alert("업로드 실패");
						$('#statusMessage').html('파일업로드 실패.');
						$('#statusMessage').css("color", "red");
					}
				},
				error: function(e) {
					alert("업로드 에러");
					$('#statusMessage').html('파일업로드 에러.');
					$('#statusMessage').css("color", "red");
				}
				
			});
		}
	</script>
</body>
</html>