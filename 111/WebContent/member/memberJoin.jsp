<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=request.getContextPath(); %> <!-- url path를 미리 잡아놓음. -->
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<%=path%>/css/reset.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/headerFooter.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/member.css" type="text/css" rel="stylesheet">

<!-- JQuery 연동 --> 
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>

	<script type="text/javascript" src="<%=path%>/js/member.js"></script>

</head>
<body>
	<jsp:include page="/header.jsp"/>
	<div>
		<section class="mbodyWrap">
			<form action="./MemberJoinAction.me" method="post" onsubmit="return checkJoin();">
				<h1 id="mTitle">회원가입</h1>

				<div class="memberJoinWrap">
					<input type="text" id="email" name="email" placeholder="이메일" onkeyup="emailDoubleCheck()">
					<div class="mInfoCheck">
						<p id="mailDoubleCheckTxt" style="display:none;">중복된 메일입니다.</p>
						<p id="mailCheckTxt" style="display:none;">메일형식을 체크해 주세요.</p>
					</div>
					<input type="password" id="password" name="password" placeholder="비밀번호(영문, 숫자 조합 8~12자)" onkeyup="passRullCheck()"> 
					<input type="password" id="password2"  name="password2" placeholder="비밀번호 확인">
					<div class="mInfoCheck">
						<p id="passCheckTxt" style="display:none;">영문, 숫자 조합 8~12자로 설정해 주세요</p>
					</div>
					<div class="mJoinTerm">
						<input type="checkbox" id="term_of_use" name="term_of_use">
						<label>
							<a href="./TermOfUse.me" onclick="window.open(this.href,'_black', 'width=600, height=400'); return false">&nbsp;이용약관</a>에 동의합니다.
						</label>
					</div>
					<div class="mJoinTerm">
						<input type="checkbox" id="privacy_policy" name="privacy_policy">
						<label>
							<a href="./PrivacyPolicy.me" onclick="window.open(this.href,'_black', 'width=600, height=400'); return false">&nbsp;개인정보수집·이용</a>에 동의합니다.
						</label>
					</div>
				</div>
				<button type="submit" class="mJoinButton">회원가입</button>
			</form>
			<div class="mJoinButton" onclick="location.href='./MemberLogin.me'" >SNS로 로그인</div>
		</section>
	</div>
	<jsp:include page="/footer.jsp"/>
/body>
</html>