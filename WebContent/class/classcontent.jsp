<%@page import="com.teamproject.db.ClassDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

  <h1> 클래스 목록 페이지</h1>
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
  <% 
  	}
  %>

  <!-- 본문 -->
  
  <h2>클래스 본문</h2>
  
  <%
  	ClassDTO dto = (ClassDTO) request.getAttribute("dto");
  %>
  
  <h4>${dto }</h4>
  
  <h3>클래스 이름 : ${dto.class_name }</h3>
  <h3>클래스 날짜 : ${dto.class_date }</h3>
  <h3>튜터 이름 : ${dto.class_tutor_name }</h3>
  <h3>클래스 가격 : ${dto.class_price }</h3>
  
  <input type="button" value="결제" onclick="location.href='Payment.me'">
  <input type="button" value="좋아요" onclick="location.href='Like.me'">


</body>
</html>