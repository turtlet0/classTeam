<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리뷰</title>


</head>
<body>
	<h1>reviewList.jsp	-------review.jsp</h1>	
	<%
		ArrayList reviewList = (ArrayList) request.getAttribute("reviewList");
	%>
<hr>


<h2> 클래스코드 : ${requestScope.class_cd}</h2>
<h2> 현재접속중멤버코드: ${sessionScope.member_cd }</h2>
<%-- ${reviewList } --%>
<hr>	
<!-- -------------------리뷰작성------------------------------- -->	
버튼 : <input type="button" value="리뷰작성"  onclick="location.href='../reviewWrite?class_cd=${requestScope.class_cd}';"><br><hr>
<!-- -------------------리뷰작성------------------------------- -->	
<hr>
<!-- -------------------리뷰리스트----------------------------- -->	
	<form action="">
	<table border="1">
		<c:forEach var="dto" items="${reviewList }">
			<tr>
				<td>
				 <c:if test="${dto.c_lev>0 }">
	         		<img src="../review/img/level.gif" height="10">	
					<img src="../review/img/re.gif">
				</c:if>
				cno: ${dto.cno }</td>
				<td>${dto.member_cd }</td>
				<td>${dto.rating }</td>
				<td>${dto.reg_date }</td>
				<td>
						<input type='button' value="수정" onclick="location.href='../reviewUpdate?cno=${dto.cno}';">
<!-- 					<div id='editbtn'><input type='button' value="수정" id='btnUpdate' onclick="../reviewUpdate"></div> -->
				</td>
				<td>
					<input type="button" value="삭제ㅡㅡ" onclick="location.href='../reviewDelete?class_cd=${requestScope.class_cd }&cno=${dto.cno }&c_ref=${dto.c_ref }&c_lev=${dto.c_lev }&c_seq=${dto.c_seq }'">
				</td>
<%-- 				<td><input type="button" value="삭제" id="delOk" onclick="../deleteReview?class_cd=${requestScope.class_cd}&cno=${dto.cno}"></td> --%>
				<td>
					<c:if test="${dto.c_lev==0 }">
						<input type="button" value="댓글"
							onclick="location.href='../reviewReply?class_cd=${requestScope.class_cd}&cno=${dto.cno}&c_ref=${dto.c_ref }&c_lev=${dto.c_lev }&c_seq=${dto.c_seq }';">
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


<!--  	function updateReview() {
		$.ajax({
			async : false, // 비동기 해제 : 모두 호출되고 다음함수 실행		
			method : "POST",
			cache : false,
			url : "reviewUpdate.me",
			data : {"content" : content}
		});

	} -->