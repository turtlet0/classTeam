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
  <h1> 정산 페이지 </h1>
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
  	List paymentList = (List)request.getAttribute("paymentList");
  %>
  <div>
    <div> 매출 </div>
    <c:forEach var="pdto" items="${paymentList }">
    <div>
      <div>클래스명 ${pdto.class_name }</div>
      <div>결제일 ${pdto.payment_date }</div>
      <div>정산 금액 ${pdto.pay }</div>
      <div>수수료 ${pdto.pay *0.3}</div>
      <div>최종 정산 금액 ${pdto.price }</div>
    </div>
    </c:forEach>
    <button> 정산 </button>
  </div>
</body>
</html>