<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=request.getContextPath(); %> <!-- url path를 미리 잡아놓음. -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
	// 로그인 정보(세션) 초기화
	session.invalidate();
	%>
	<script type="text/javascript">
		alert("로그아웃 되었습니다.");
		location.href='<%=path%>/index.jsp';
	</script>
</body>
</html>