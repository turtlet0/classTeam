<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>main.jsp</h1>
<!-- 	
	ClassAdd1.cl 
	ClassAdd2.cl 
	ClassAdd3.cl 
-->
		멤버코드1로 움직임<hr><hr><hr>
	<%
		int member_cd=1;
		session.setAttribute("member_cd", member_cd);
	%>

	<a href="./class.cl?class_cd=1">클래스 1</a><br>
	<a href="./class.cl?class_cd=2">클래스 2</a><br>
	<a href="./class.cl?class_cd=3">클래스 3</a><br>
	<a href="./class.cl?class_cd=4">클래스 4</a><br>
	
</body>
</html>