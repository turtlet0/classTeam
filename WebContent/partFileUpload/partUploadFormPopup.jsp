<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Part 인터페이스 이용한 단일 업로드 클라이언트 페이지 -->

	<form action="/mocaclass/partUploadPro1Popup" method="post" enctype="multipart/form-data">
	<!-- action="/프로젝트명/서블릿명" -->
		
		<label for="partFile1">업로드 파일 : </label>
		<input type="file" name="partFile1" id="partFile1"><br>
		
		<input type="submit" value="단일업로드">
</form>
</body>
</html>