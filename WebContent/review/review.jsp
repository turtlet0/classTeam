<%@page import="com.mocha.review.db.ReviewDAO"%>
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
	<h1>review.jsp</h1>
	
<!-- -------------------리뷰작성------------------------------- -->	
<input type="button" value="리뷰작성" onclick="location.href='./reviewWrite.me';"><br>
<!-- -------------------리뷰작성------------------------------- -->	


<!-- -------------------리뷰리스트----------------------------- -->	
<%
		
	<table border="1">
     <tr>
       <!-- <td>사진</td> -->
       <td>이름</td>
       <td>날짜</td>
       <td>평점</td>
       <td>내용</td>
     </tr>
	<%
        ArrayList reviewList= (ArrayList)request.getAttribute("reviewList");
    %>
<%--      ${reviewList }<br> --%>
 	<c:forEach var="dto" items="${reviewList }">
         <tr>
	       <td>${dto.name }</td>
	       <td>${dto.regdate }</td>
	       <td>${dto.rating }</td>
	       <td>${dto.content }</td>
	     </tr>
     </c:forEach>
     
   </table>
<!-- -------------------리뷰리스트----------------------------- -->	

<!-- -------------------페이지버튼----------------------------- -->	
페이지버튼출력
<!-- -------------------페이지버튼----------------------------- -->	


<hr>
<input type="button" value="contents로 이동" onclick="location.href='./contents.me';">	
	
</body>
</html>