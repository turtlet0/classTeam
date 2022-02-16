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
		String member_cd=(String)session.getAttribute("member_cd");
		if(member_cd == null){
			response.sendRedirect("./MemberLogin.me");
		}
		MemberDTO dto = (MemberDTO) request.getAttribute("dto");
	%>
	<jsp:include page="/header.jsp"/>
	<div class="body">
		<section>
			<div>프로필</div>
			<div>SNS_ID</div>
			<div>${dto.sns_id}</div>
			<div>이메일</div>
			<div>${dto.email}</div>
			<div>이름(닉네임)</div>
			<div>${dto.nick_name}</div>
			<div>전화번호</div>
			<div>${dto.phone_num}</div>
			<button type="button" onclick="location.href='./MemberProfileUpdate.me'">프로필 수정</button>
			<button type="button" onclick="location.href='./Main.me'">Main</button>
		</section>
	</div>
	
</body>
</html>