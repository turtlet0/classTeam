<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QnA</title>


</head>
<body>
	<h1>QnaList.jsp	-------qnalist.jsp</h1>	
	<%
		ArrayList qnaList = (ArrayList) request.getAttribute("qnaList");
	%>
<hr>


<h2> 클래스코드 : ${requestScope.class_cd}</h2>
<h2> 현재접속중멤버코드: ${sessionScope.member_cd }</h2>
<%-- ${reviewList } --%>
<hr>	
<!-- -------------------Q작성------------------------------- -->	
버튼 : <input type="button" value="작성"  onclick="location.href='../qnaWrite?class_cd=${requestScope.class_cd}';"><br><hr>
<!-- -------------------Q작성------------------------------- -->	
<hr>
<!-- -------------------QnA리스트----------------------------- -->	
	<form action="">
	<table border="1">
		<c:forEach var="dto" items="${qnaList }">
			<tr>
				<td>
				 <c:if test="${dto.q_lev>0 }">
	         		<img src="../img/level.gif" height="10">	
					<img src="../img/re.gif">
				</c:if>
				qno: ${dto.qno }</td>
				<td>${dto.member_cd }</td>
				<td>${dto.reg_date }</td>
				<td>
						<input type='button' value="수정" onclick="location.href='../qnaUpdate?qno=${dto.qno}';">
<!-- 					<div id='editbtn'><input type='button' value="수정" id='btnUpdate' onclick="../reviewUpdate"></div> -->
				</td>
				<td>
					<input type="button" value="삭제" onclick="location.href='../qnaDelete?class_cd=${requestScope.class_cd }&qno=${dto.qno }&q_ref=${dto.q_ref }&q_lev=${dto.q_lev }&q_seq=${dto.q_seq }'">
				</td>
<%-- 				<td><input type="button" value="삭제" id="delOk" onclick="../deleteReview?class_cd=${requestScope.class_cd}&cno=${dto.cno}"></td> --%>
				<td>
					<c:if test="${dto.q_lev==0 }">
						<input type="button" value="댓글"
							onclick="location.href='../qnaReply?class_cd=${requestScope.class_cd}&qno=${dto.qno}&q_ref=${dto.q_ref }&q_lev=${dto.q_lev }&q_seq=${dto.q_seq }';">
					</c:if>
				</td>
			</tr>

			<tr>
				<td colspan="7">
					<div id='contentform'>${dto.content }</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	</form>

<hr>
<input type="button" value="메인으로 이동" onclick="location.href='../main';">	
</body>
</html>
