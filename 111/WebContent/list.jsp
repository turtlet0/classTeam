<%@page import="com.latteclass.db.ClassDTO" %>
<%@page import="com.latteclass.db.ClassDAO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%String path=request.getContextPath(); %> <!-- url path를 미리 잡아놓음. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="<%=path%>/css/reset.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/headerFooter.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/headerSearchBar.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/list.css" type="text/css" rel="stylesheet">

</head>
<body>
	<jsp:include page="/header.jsp"/>
	<div id="listBody">
		<c:if test="${count == 0 }">
			<section class="classlist">
	       		<div class="container">	
		        	<p class="noresult">
			 		검색결과가 없습니다.<br>
					다른클래스명 또는 필터를 시도
		        	</p>
		        </div>
		     </section>		
		</c:if>	
	 	<c:if test="${count > 0}">
	 	
	 	<section class="classlist">
	        <div class="container">
	            <!-- <div class="title">
	                <h1>SERVICE LIST</h1>
	                <ul>
	                    <li><a href="">Ai</a></li>
	                    <li><a href="">IoT</a></li>
	                    <li><a href="">Information Security</a></li>
	                    <li><a href="">Network</a></li>
	                </ul>
	            </div> -->
	            <div class="item_list">
	            <c:forEach var="classlist" items="${classlist}" >
	                <div class="card" onclick="location.href='./classcontent.do?class_cd=${classlist.class_cd}&pageNum=${currentPage}'">
	                	<c:choose>
	                		<c:when test="${classlist.class_rep_img eq null }">
			                    <div class="img">
			                        <img src="./No_Image.png" alt="이미지없음">
			                    </div>
	                		</c:when>
	                		<c:otherwise>
			                    <div class="img">
			                        <img src="./upload/${classlist.class_rep_img}" alt="강의이미지">
			                    </div>
	                		</c:otherwise>
	                	</c:choose>
	                    <div class="text">
	                        <button><i class="fas fa-check"></i>${classlist.category}</button>
	                        <h4>${classlist.title }</h4>
	                        <p class="green">${classlist.sido }  </p>
	                        
							<c:choose>
								<c:when test="${classlist.price eq '문의'}" >
									<p>${classlist.price}</p>
								</c:when>
								<c:otherwise>
									<p>${classlist.price} 원</p>
								</c:otherwise>
							</c:choose>
	                    </div>
	                </div>
	             </c:forEach>   
	            </div>
	        </div>
	    </section>
		
		</c:if>
		<div id="page_control">
			 <c:if test="${count > 0 }">
				 <!-- 페이징 처리 -->
				 <!-- 전체 페이지 수 계산 -->
				 <c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1 }"/>
				 <c:set var="pageCount" value="${ count / pageSize + imsi }"/>
				 
				 <!-- 한 페이지에 보여줄 페이지 블럭 -->
				 <c:set var="pageBlock" value="${10}"/>
				 <fmt:parseNumber var="result" value="${(currentPage-1) / pageBlock}" integerOnly="true"/>
				 <!-- 한 페이지에 보여줄 시작번호 계산 -->
				 <c:set var="startPage" value="${result*pageBlock+1}"/>
				 
				 <!-- 한 페이지에 보여줄 페이지 블럭 끝번호 계산 -->
				 <c:set var="endPage" value="${startPage + pageBlock-1}"/>
				 <c:if test="${endPage > pageCount}">
				 	<c:set var="endPage" value="${pageCount}"/>
				 </c:if>
				 <c:if test="${startPage > pageBlock}">
					<a href="./classlist.do?pageNum=${startPage - pageBlock}&keyWord=${keyWord}&sido=${sido}&category=${category}&subcategory=${subcategory}">이전</a>
				 </c:if>
				 <c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="./classlist.do?pageNum=${i}&keyWord=${keyWord}&sido=${sido}&category=${category}&subcategory=${subcategory}">[${i}]</a>
				 </c:forEach>
				 <c:if test="${endPage<pageCount}">
					<a href="./classlist.do?pageNum=${startPage + pagBlock}&keyWord=${keyWord}&sido=${sido}&category=${category}&subcategory=${subcategory}">다음</a>
				 </c:if>
			</c:if>
		</div>
	</div>
	<jsp:include page="/footer.jsp"/>
</body>
</html>