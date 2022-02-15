<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path=request.getContextPath(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=path%>/css/scheduleset.css" type="text/css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <script>
  $(function(){
	$(".multich").hide();
	 
	$(".multi").click(function(){
		$(".onech").hide();
		$(".multich").show();
		 
	});
	 
	$(".one").click(function(){ 
		$(".multich").hide();
		$(".onech").show();
		 
	});
		
	$(".subm").on("click",function(){
		var formData = $(".fr1").serialize();
			$.ajax({
				url: "SetOneday.me",
				data: formData
			});
	});
		
	$(".submt").on("click",function(){
		var formData = $(".fr2").serialize();
			$.ajax({
				url: "SetMultiple.me",
				data: formData
			});
	});
  });
	
	

  </script>
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
  %>
  
  <br>
  <button onclick="location.href='Main.me'"> 홈 </button> <br>
  클래스 코드 : ${param.class_cd }
  <br>  
 
  <div class="cal_main">
    <aside class="aside">메뉴</aside>
  <div class="cal">
    <button class="one"> 하나만 </button>
    <button class="multi"> 다중 </button>
      <div class="onech">
	    <jsp:include page="calendar.jsp"/>
      </div>
      <div class="multich">	
        <jsp:include page="calendar2.jsp"/>
      </div>
      
  </div>

    <div class="clview">
      <%
		List scheduleList = (List)request.getAttribute("scheduleList");
	  			
	  %>
	  
	  
	  <c:forEach var="sdto" items="${scheduleList }">
	  	<div>
	  	  1 클래스 날짜 ${sdto.schedule_startDate } <br>
	  	  2 ${sdto.schedule_startTime } ~  ${sdto.schedule_endTime }
	  	  <input type="button" onclick="location.href='deleteSchedule.me?schedule_cd=${sdto.schedule_cd}&class_cd=${param.class_cd }'" value="삭제">
	  	</div>
	  </c:forEach>
	  
	  

    </div>
  </div>
</body>
</html>