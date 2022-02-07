<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

 <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  <!-- 서머노트를 위해 추가해야할 부분 -->
  <script src="./summernote-lite.js"></script>
  <script src="./summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="./summernote-lite.css">
  
</head>
<body>

	<textarea ><%=request.getParameter("editordata") %></textarea>
	
	<script type="text/javascript">
		var editorContent = CKEDITOR.instances.
		
	</script>
</body>
</html>