<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>reviewUpdate.jsp</h1>
	<h2>클래스번호:${dto.class_cd }</h2>
	<h2>현재멤버코드:${dto.member_cd }</h2>
	<h2>qno:${param.qno}</h2>
	${dto }
	<form action="./qnaUpdateProAction"  method="post">
	<input type="hidden" name="qno" value="${dto.qno }">
	<input type="hidden" name="member_cd" value="${dto.member_cd }">
	<input type="hidden" name="class_cd" value="${dto.class_cd }">
	<table border="1">
	<tr>
		<th colspan="1">내용</th>
		<td colspan="3"><input type="text" name="content" value="${dto.content }"><td>
	</tr>
	<tr>
		<td colspan="4"><input type="submit" value="수정"><input type="reset" value="초기화"></td>
	</tr>
	</table>
</form>
</body>
</html>