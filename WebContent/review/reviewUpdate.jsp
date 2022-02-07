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
	<form action=""  method="post">
	<table border="1">
		<tr>
			<th>별점</td><!-- css -->
			<td class="left" colspan="3">
			<select name="rating">
				<option value="0">별점을 선택하시오</option>
				<option value="1">1점</option>
				<option value="2">2점</option>
				<option value="3">3점</option>
				<option value="4">4점</option>
				<option value="5">5점</option>
			</select>
		</td>
		</tr>


	<tr>
		<th colspan="1">내용</th>
		<td colspan="3"><input type="text" name="contents"><td>
	</tr>
	
	<tr>
		<td colspan="4"><input type="submit" value="리뷰수정"></td>
	</tr>
	</table>
</form>
</body>
</html>