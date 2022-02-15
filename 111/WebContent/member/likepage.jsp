<%@page import="java.util.List"%>
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
	<!-- 조문주 -->
	<h1>좋아요 누른 클래스 확인 페이지</h1>
	
  <%
  	String cd = String.valueOf(session.getAttribute("cd"));
  	
  	if("null".equals(cd)){
  		
  %>
  	
  	<button onclick="location.href='MemberLogin.me'">로그인</button> 
  
  <%
  	} else {
  %>
  

  
   세션 : ${cd }
  <button onclick="location.href='MemberLogout.me'">로그아웃</button>
  <% 
  	}
  %>
  
  <%
  	List likeList = (List)request.getAttribute("likeList");
  %>
	<div>
	  <div> 찜한 클래스 </div>
	  <c:forEach var="ldto" items="${likeList }">
	  <div style="border: 1px solid black;">
	  	<a href="Contents.me?class_cd=${ldto.likes_class_cd }" style="text-decoration: none;">
	  		<div> 클래스 이미지 </div>
	  		<div> 클래스 ${ldto.likes_class_date } </div>
	  		<div> 클래스 이름 ${ldto.likes_class_name }</div>
	  	</a>

	  </div>
	  </c:forEach>
	</div>



</body>
</html>