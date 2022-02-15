<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=request.getContextPath(); %>
<%
	String member_cd=(String)session.getAttribute("member_cd");
%>


<<jsp:include page="list.jsp"></jsp:include>
		<header>
			<section class="headerWrap">
				<div class="hLogo" ><a class="hLogo" href="./Main.me">(img) Latte Class</a></div>
				<input id ="hsearch" type="search" placeholder="주변의 다양한 클래스를 찾아보세요!">
				<div class="hRight">
					<div class ="hClassOpen"><a href=#>나만의 클래스 오픈하기</a></div>
					<%if(member_cd == null){%>
					<div class ="hLogin"><a href="./MemberLogin.me">로그인</a></div>
					<div class ="hJoin"><a href="./MemberJoin.me">회원가입</a></div>
					<%}%>
					<%if(member_cd !=null){%>
					<div class="hMemberInfo">
						<div class="hMemberInfoTitle">회원정보</div>
						<ul class="dropDownMInfo">
							<li onclick="location.href='./MemberProfile.me'">프로필</li>
							<li onclick="location.href='#'">클래스 결제/예약 내역</li>
							<li onclick="location.href='#'">내가 등록한 클래스</li>
							<li onclick="location.href='#'">내가 찜한 클래스</li>
							<li onclick="location.href='./MemberLogout.me'">로그아웃</li>
						</ul>
						</div>
					</div>
					<%}%>
				</section>

		</header>
    