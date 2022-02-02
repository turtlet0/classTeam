<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" enctype="multipart/form-data">

	<input type="file" name="filedata" id="imgFile" onchange="imgPreview(this);">
	<img id="img" width="150"> 
</form>

	<script type="text/javascript">
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