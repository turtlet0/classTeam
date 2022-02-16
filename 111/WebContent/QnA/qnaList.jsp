<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path=request.getContextPath(); %> <!-- url path를 미리 잡아놓음. --> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">

.page_wrap {
	text-align:center;
	font-size:0;
 }
.page_nation {
	display:inline-block;
}
.page_nation .none {
	display:none;
}
.page_nation a {
	display:block;
	margin:0 3px;
	float:left;
	border:1px solid #e6e6e6;
	width:28px;
	height:28px;
	line-height:28px;
	text-align:center;
	background-color:#fff;
	font-size:13px;
	color:#999999;
	text-decoration:none;
}
.page_nation .arrow {
	border:1px solid #ccc;
}
.page_nation .prev {
	background:#f8f8f8 url('../img/page_prev.png') no-repeat center center;
	margin-right:7px;
}
.page_nation .next {
	background:#f8f8f8 url('../img/page_next.png') no-repeat center center;
	margin-left:7px;
}
.page_nation a.active {
	background-color:#42454c;
	color:#fff;
	border:1px solid #42454c;
}

</style>
<title>QnA</title>


</head>
<body>
	<h1>QnaList.jsp	-------qnalist.jsp</h1>	
	<h2>class_cd : ${class_cd }</h2>
	<h2>cnt : ${cnt }</h2>
	<h2>pageCount : ${pageCount } / pageBlock : ${pageBlock } 
	/ startPage : ${startPage } / endPage: ${endPage }</h2>
cntReview : ${cntReviewList }
	<%
		ArrayList qnaList = (ArrayList) request.getAttribute("qnaList");
	%>
<hr>


<h2> 클래스코드 : ${requestScope.class_cd}</h2>
<h2> 현재접속중멤버코드: ${sessionScope.member_cd }</h2>
<%-- ${reviewList } --%>
<hr>	
<!-- -------------------Q작성------------------------------- -->	
버튼 : <input type="button" value="작성"  onclick="location.href='../qnaWrite?class_cd=${requestScope.class_cd}';"><br><hr>
<!-- -------------------Q작성------------------------------- -->	
<hr>
<!-- -------------------QnA리스트----------------------------- -->	
	<form action="">
	<table border="1">
	<c:if test="${cnt!=0 }">
		<c:forEach var="dto" items="${cntQnaList }">
			<tr>
				<td>
				 <c:if test="${dto.q_lev>0 }">
	         		<img src="../img/level.gif" height="10">	
					<img src="../img/re.gif">
				</c:if>
				qno: ${dto.qno }</td>
				<td>classCode : ${dto.class_cd }</td>
				<td>${dto.member_cd }</td>
				<td>${dto.reg_date }</td>
				<td>
						<input type='button' value="수정" onclick="location.href='../qnaUpdate?qno=${dto.qno}';">
				</td>
				<td>
					<input type="button" value="삭제" onclick="location.href='../qnaDelete?class_cd=${requestScope.class_cd }&qno=${dto.qno }&q_ref=${dto.q_ref }&q_lev=${dto.q_lev }&q_seq=${dto.q_seq }'">
				</td>
				<td>
					<c:if test="${dto.q_lev==0 }">
						<input type="button" value="댓글"
							onclick="location.href='../qnaReply?class_cd=${requestScope.class_cd}&qno=${dto.qno}&q_ref=${dto.q_ref }&q_lev=${dto.q_lev }&q_seq=${dto.q_seq }';">
					</c:if>
				</td>
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
			<td colspan="5">등록된 QnA가 없습니다<br>
			</td>
		</tr>
	</c:if>
	</table>
	</form>
<hr><!-- 페이징처리 -->
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
<input type="button" value="메인으로 이동" onclick="location.href='../main';">	
</body>
</html>
