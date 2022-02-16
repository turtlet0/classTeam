<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!-- url path를 미리 잡아놓음. -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/css/reset.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/headerFooter.css" type="text/css"	rel="stylesheet">
<link href="<%=path%>/css/headerSearchBar.css" type="text/css"	rel="stylesheet">
<link href="<%=path%>/css/reviewList.css" type="text/css"	rel="stylesheet">

<title>리뷰상세page</title>

</head>
<body>
	<jsp:include page="/header.jsp"/>
	<div id="reivewListBody">
			<%
				ArrayList reviewList = (ArrayList) request.getAttribute("reviewList");
			%>
		<%-- ${reviewList } --%>
		<!-- -------------------리뷰작성------------------------------- -->
		<div id="reviewListTitle">
			<div id="reviewListTitleSub">강의리뷰</div>
			<input type="button" id="reviewListTitleButtom" value="리뷰작성" onclick="location.href='../reviewWrite?class_cd=${requestScope.class_cd}';">
		</div>
		<!-- -------------------리뷰작성------------------------------- -->
		<hr>
		<!-- -------------------리뷰리스트----------------------------- -->
			<form action="">
				<table border="1">
					<c:if test="${cnt!=0 }">
						<c:forEach var="dto" items="${cntReviewList }">
							<tr>
								<td><c:if test="${dto.c_lev>0 }">
										<img src="../review/img/level.gif" height="10">
										<img src="../review/img/re.gif">
									</c:if> cno: ${dto.cno }</td>
								<td>${dto.member_cd }</td>
								<td>${dto.rating }</td>
								<td>${dto.reg_date }</td>
								<td><input type='button' value="수정"
									onclick="location.href='../reviewUpdate?cno=${dto.cno}';">
								</td>
								<td><input type="button" value="삭제"
									onclick="location.href='../reviewDelete?class_cd=${requestScope.class_cd }&cno=${dto.cno }&c_ref=${dto.c_ref }&c_lev=${dto.c_lev }&c_seq=${dto.c_seq }'">
								</td>
								<td><c:if test="${dto.c_lev==0 }">
										<input type="button" value="댓글"
											onclick="location.href='../reviewReply?class_cd=${requestScope.class_cd}&cno=${dto.cno}&c_ref=${dto.c_ref }&c_lev=${dto.c_lev }&c_seq=${dto.c_seq }';">
									</c:if></td>
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
			</form>
		<hr>
		<!-- 페이징처리 -->
		<div class="page_wrap">
			<div class="page_nation">
				<c:if test="${endPage>pageCount}">
					<c:set var="endPage" value="${pageCount }" />
				</c:if>
	
				<c:if test="${startPage>pageBlock }">
					<a class="arrow prev" href="?pageNum=${startPage-pageBlock }">이전</a>
				</c:if>
	
				<c:forEach var="i" begin="${startPage }" end="${endPage}">
					<a class="active" href="?pageNum=${i }">${i }</a>
				</c:forEach>
	
				<c:if test="${endPage<pageCount }">
					<a class="arrow next" href="?pageNum=${startPage+pageBlock }">다음</a>
				</c:if>
			</div>
		</div>
		<hr>
		<input type="button" value="메인으로 이동" onclick="location.href='./MemberLogout.me'">
	</div>
	<jsp:include page="/footer.jsp"/>
</body>
</html>

