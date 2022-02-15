<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>delete.jsp</h1>
	<h2>현재멤버코드 ${sessionScope.member_cd }</h2>
	<h2>현재클래스코드 ${param.class_cd }</h2>
	<h2>글번호 cno ${param.cno }</h2>
	<h2>ref :  ${param.c_ref }</h2>
	<h2>lev :  ${param.c_lev }</h2>
	<h2>seq :  ${param.c_seq }</h2>
	<form action="./reviewDeleteProAction">
	<input type="hidden" value="${sessionScope.member_cd }" name="member_cd">
	<input type="hidden" value="${param.class_cd }" name="class_cd">
	<input type="hidden" value="${param.cno }" name="cno">
	<input type="hidden" value="${param.c_ref }" name="c_ref">
	<input type="hidden" value="${param.c_lev }" name="c_lev">
	<input type="hidden" value="${param.c_seq }" name="c_seq">
	삭제하시겠습니까?
	<hr>
	<input type="submit" value="삭제">
	<input type="button" value="돌아가기" onclick="history.go(-1)">
	</form>
	
	
</body>
</html>