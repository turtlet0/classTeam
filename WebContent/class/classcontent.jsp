<%@page import="com.teamproject.dto.ClassDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 페이지 로딩시 좋아요 개수 출력 -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
  $(function(){
	  function lcnt(){
		  $.ajax({
			  url: "LikeCount.me",
			  type: "POST",
			  data: {class_cd : ${dto.class_cd}},
			  success: function(ccnt) {
				  $(".like_count").html(ccnt);
			  }
		  
		  })
	  }
	  lcnt();
  });
</script>

</head>
<body>
  <!-- 좋아요/결제 조문주 -->
  
  <!-- 좋아요/결제 버튼 누를 수 있는 클래스 페이지 대충 구현 -->
  <h1> 클래스 상세 페이지 </h1>
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
  
  
  <h3>클래스 이름 : ${dto.class_name }</h3>
  <h3>클래스 날짜 : ${dto.class_date }</h3>
  <h3>튜터 이름 : ${dto.class_tutor_name }</h3>
  <h3>클래스 가격 : ${dto.class_price }</h3>
  
  <!-- 결제 정보 넘겨줌 -->
  <form action="Payment.me" method="post">
    <input type="hidden" name="class_cd" value=${dto.class_cd }>
    <input type="submit" value="결제">
  </form>
  
	
    <input type="button" value="♥" class="like_btn">
    <span class="like_count"></span>

  
  <!-- 좋아요 클릭시 실행 -->
  <script type="text/javascript">
     $(function(){
    	$(".like_btn").click(function(){
    		var class_cd = ${dto.class_cd};
    		
    		$.ajax({
    			url: "LikeUpdate.me",
    			type: "post",
    			data : {class_cd : class_cd},
    			success: function(){
    				$.ajax({
  					  url: "LikeCount.me",
  					  type: "POST",
  					  data: {class_cd : ${dto.class_cd}},
  					  success: function(ccnt) {
  						  $(".like_count").html(ccnt);
  					  }
  				  
  				  })
    	
    				
    			}
    		})
    	})
     });
  </script>






</body>
</html>