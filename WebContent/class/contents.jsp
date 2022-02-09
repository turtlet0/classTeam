<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- a링크 클릭시 post로 param 전송 -->
<script src="http://code.jquery.com/jquery-latest.js"></script>


</head>
<body>

<!-- 메인 페이지 조문주 -->



  <h1> 클래스 목록 페이지</h1>
  <button onclick="location.href='Likes.me'">좋아요</button>
  <br>
  
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
  <button onclick="location.href='PayCalculate.me'">정산</button>
  <% 
  	}
  %>

  <!-- 본문 -->
  
  <h2>메인</h2>
  <%
  	List classList
  		= (List)request.getAttribute("classList");
  
  %>
  
  <hr>
  <c:forEach var="dto" items="${classList }">
   <a href="./Contents.me?class_cd=${dto.class_cd }">
      클래스 ${dto.class_name } </a> <br>
      가격 ${dto.class_price } <br>
      날짜 ${dto.class_date } <br>
      이름 ${dto.class_tutor_name }  
   
   <hr>
    
  </c:forEach>
  

  	
  
</body>
</html>