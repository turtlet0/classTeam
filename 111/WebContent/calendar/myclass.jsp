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

  	List myClassList = (List)request.getAttribute("myClassList");
  	
  	if(myClassList.isEmpty()){
  		
  	
  
  %>
  
  업성
  
  <%
  	} else{
  %>
<br>
<c:forEach var="cdto" items="${myClassList }">
  클래스 이름: ${cdto.class_name} 
  <button onclick="location.href='ScheduleSet.me?class_cd=${cdto.class_cd }'"> 관리 </button> <br>
</c:forEach>

<%

}
%>




</body>
</html>