<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>강의 등록</h2>
	
	<fieldset>
		<legend>강의등록</legend>
		<form action="./writePro.do" method="post" enctype="multi">
			강의명 : <input type="text" name="className">
			강의이미지 : <input type="text">
			카테고리 : <input type="text" name="classCategory">
			주소 : 
			가격 :
			
		</form>
	</fieldset>

</body>
</html>