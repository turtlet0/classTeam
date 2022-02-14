<%@page import="com.latte.db.QnaDAO"%>
<%@page import="com.latte.db.ReviewDTO"%>
<%@page import="com.latte.db.ReviewDAO"%>
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
	<h1>detail.jsp</h1>
	
	<hr>
	클래스코드classcd : ${param.class_cd }<br>
	클래스코드classcdrequest : ${requestScope.class_cd }<br>
	멤버코드membercd : ${sessionScope.member_cd }<br>
	<hr>
	
	
	대<br>충<br>내<br>용<br><hr>
	<!-- ----------------------------------리뷰---------------------------------------------- -->
	<%
		ReviewDAO dao=new ReviewDAO();
		int class_cd=Integer.parseInt(request.getParameter("class_cd"));
		int cnt=dao.getReviewCount(class_cd);
		request.setAttribute("cnt", cnt);
		request.setAttribute("cntReviewList", dao.getCntReviewList(1, 3,class_cd));
	%>
	<table border="1">
	<c:if test="${cnt!=0 }">
		<c:forEach var="dto" items="${cntReviewList }">
			<tr>
				<td>cno: ${dto.cno }</td>
				<td>${dto.member_cd }</td>
				<td>${dto.rating }</td>
				<td>${dto.reg_date }</td>
			</tr>
			<tr>
				<td colspan="7">
					<div id='contentform'>${dto.content }</div>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${cnt==0 }">
		<tr>
			<td colspan="5">등록된 리뷰가 없습니다<br>
			</td>
		</tr>
	</c:if>
	</table>
	<h2><a href="./review/${param.class_cd }">리뷰 더 보기</a></h2><hr>
	<!-- ----------------------------------리뷰---------------------------------------------- -->

	<!-- ----------------------------------QnA---------------------------------------------- -->
		<%
		QnaDAO qdao=new QnaDAO();
		int qclass_cd=Integer.parseInt(request.getParameter("class_cd"));
		int qcnt=qdao.getQnaCount(qclass_cd);
		request.setAttribute("qcnt", qcnt);
		request.setAttribute("cntQnaList", qdao.getCntQnaList(1, 3,qclass_cd));
	%>
	<table border="1">
	<c:if test="${qcnt!=0 }">
		<c:forEach var="dto" items="${cntQnaList }">
			<tr>
				<td>qno: ${dto.qno }</td>
				<td>${dto.member_cd }</td>
				<td>${dto.reg_date }</td>
			</tr>
			<tr>
				<td colspan="7">
					<div id='contentform'>${dto.content }</div>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${qcnt==0 }">
		<tr>
			<td colspan="5">등록된 리뷰가 없습니다<br>
			</td>
		</tr>
	</c:if>
	</table>
	<h2><a href="./QnA/${param.class_cd }">QnA 더 보기</a></h2>
	<!-- ----------------------------------QnA---------------------------------------------- -->
	
</body>
</html>