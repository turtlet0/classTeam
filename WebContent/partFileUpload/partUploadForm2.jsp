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

	<form action="/Model2/partUploadPro2" method="post" enctype="multipart/form-data">
	<!-- action="/프로젝트명/서블릿명" -->
		<label for="writer">작성자 : </label>
		<input type="text" name="writer" id="writer"><br>
		
		<label for="partFile1">업로드 파일1 : </label>
		<input type="file" name="partFile1" id="partFile1"><br>
		<label for="partFile2">업로드 파일2 : </label>
		<input type="file" name="partFile2" id="partFile2"><br>
		
		<input type="submit" value="다중업로드">
</form>
</body>
</html>