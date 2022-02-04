<%@page import="com.test.tclass.model.ClassDTO" %>
<%@page import="com.test.tclass.model.ClassDAO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<c:if test="${count == 0 }">
	<table border="1">
	 <tr>
	 	<td>
	 		검색결과가 없습니다.<br>
			다른클래스명 또는 필터를 시도
	 	</td>
	 </tr>
	</table>	
	</c:if>	
	<c:if test="${count > 0}">
	<table border="1">
	 <tr>
	  <th id="classCd">강의NO.</th>
	  <th id="className">강의명</th>
	  <th id="classImgFile">강의이미지</th>
	  <th id="classCategory">카테고리</th>
	  <th id="roadAddress">주소</th>
	  <th id="price">가격</th>
	 </tr>
	 
	 <c:forEach var="dto" items="${classList}">
	 <tr>
	  <td>${dto.classCd}</td>
	  <td>${dto.className}</td>
	  <td>${dto.classImgFile}</td>
	  <td>${dto.classCategory}</td>
	  <td>${dto.roadAddress}</td>
	  <td>${dto.price}</td>
	 </tr>
	 </c:forEach>
	</table>
	</c:if>	
	

</body>
</html>