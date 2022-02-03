<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
index.jsp -> web.xml (./MemberJoin.me) 
	-> MemberFrontController : doGet/doPost -> doProcess - 1.가상주소 계산, 2.페이지 매핑, 3.페이지이동
		* MemberFrontController에서
			-. view 페이지 이동 : 1. ->  2. ActionForward 객체 생성 해 주소, 방식 저장 -> 3. 페이지(JSP) 이동 : 보통 forward 방식
			-. action 페이지 이동 : 1. -> 2. 해당 동작 수행하는 Action 객체 생성해 객체의 메서드 호출(메서드 내에서 DB처리 동작 실행) -> 3. 반환받은 forward 객체 정보에 따라 페이지 이동
			
	<%
		// response.sendRedirect("./ClassAdd1.cl");
		response.sendRedirect("./fileUpload.cl");
	%>		
</body>
</html>