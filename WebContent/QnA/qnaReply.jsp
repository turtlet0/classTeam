<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>review.jsp</h1>
	<h2> 클래스코드 : ${param.class_cd}</h2>
	<h2> 현재접속중멤버코드: ${sessionScope.member_cd }</h2>
	<h2>${param.q_ref }</h2>
	<h2>${param.q_lev }</h2>
	<h2>${param.q_seq }</h2>
	
	<form action="./qnaReplyAction"  method="post">
	<table border="1">
			<input type="hidden" value="${param.class_cd }" name="class_cd">
			<input type="hidden" value="${sessionScope.member_cd }" name="member_cd" >
			<input type="hidden" value="${param.q_ref }" name="q_ref">
			<input type="hidden" value="${param.q_lev }" name="q_lev">
			<input type="hidden" value="${param.q_seq }" name="q_seq">

			<tr>
		<th colspan="1">내용</th>
		<td colspan="3"><input type="text" name="content"><td>
	</tr>
	
	<tr>
		<td colspan="4"><input type="submit" value="작성"></td>
	</tr>
	</table>
</form>
	
	
	
	
	
	
	
	
</body>
</html>