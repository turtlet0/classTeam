<%@page import="com.latteclass.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String path=request.getContextPath(); %> <!-- url path를 미리 잡아놓음. -->
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<%=path%>/css/member.css" type="text/css" rel="stylesheet">
	
</head>
<body>
	<%
		//세션값제어
		String member_cd=(String)session.getAttribute("member_cd");	
		if(member_cd==null){
			response.sendRedirect("./MemberLogin.me");
		}
		MemberDTO dto = (MemberDTO) request.getAttribute("dto");
	%>
	<div class="body">
		<h2>회원정보수정</h2>
		<form action="./MemberProfileUpdateProAction.me"  method="post">
			<section>
				<div>프로필 수정</div>
				<input type="hidden" name="member_cd"  value="${dto.member_cd}"  >
				<div>SNS_ID</div>
				<div><input type="text" name="sns_id"  value="${dto.sns_id}" readonly="readonly"></div>
				<div>이메일</div>
				<div><input type="text" name = "email" value="${dto.email}" readonly="readonly"></div>
				<div>닉네임</div>
				<div><input type="text" name = "nick_name" value="${dto.nick_name}"></div>
				<div>전화번호</div>
				<div><input type="text" name = "phone_num" value="${dto.phone_num}"></div>
				<button type="submit">프로필 수정</button>
			</section>
		</form>
	</div>
</body>
</html>