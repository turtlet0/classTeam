<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>detail.jsp</h1>
	
	<hr>
	클래스코드classcd : ${param.class_cd }<br>
	클래스코드classcdrequest : ${requestScope.class_cd }<br>
	멤버코드membercd : ${sessionScope.member_cd }<br>
	<hr>
	
	
	대<br>충<br>내<br>용<br><hr>
	<h2><a href="./review/${param.class_cd }">리뷰</a></h2>
	<h2><a href="./QnA/${param.class_cd }">QnA</a></h2>
	
</body>
</html>