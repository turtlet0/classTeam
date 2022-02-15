<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int member_cd = 15;
	session.setAttribute("member_cd", Integer.valueOf(member_cd));
%>

<h1>메인 페이지</h1>
<a href="./ClassAdd.cl" >클래스 등록하기</a>
<a href="./MyClassTutor.cl" >내가 등록한 클래스</a>
</body>
</html>