<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=request.getContextPath(); %> <!-- url path를 미리 잡아놓음. -->
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<%=path%>/css/reset.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/headerFooter.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/member.css" type="text/css" rel="stylesheet">

	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	Kakao.init('16fd7b95f1cda67b70debd2eb6778452');
	Kakao.isInitialized();
	
	function loginWithKakao() {
		// 로그인 창을 띄움.
		Kakao.Auth.login({
		    success: function(authObj) {
	    		console.log(authObj);
		        //로그인 정보 확인 성공 시 API를 호출 시킨다.
		        Kakao.API.request({
					url:'/v2/user/me',
		  			success:function(response){
						console.log(JSON.stringify(response));
						const sns_id = response.id;
						var nick_name = response.properties.nickname;
						//console.log(sns_id);
						//console.log(nick_name);
						$.ajax({			
							async:false, // 비동기 해제 : 모두 호출되고 다음함수 실행		
							method:"POST",		
							cache:false,		
							url:"MemberKakaoLoginAction.me",		
							data:{
								"sns_id":sns_id,
								"nick_name":nick_name
							}	
						}).done(function(result){			
							location.href="./Main.me";
						}); //$.ajax
					} //function(response)
				});//Kakao.API.request
			}, //function(authObj)
			fail: function(err) {
				alert(JSON.stringify(err));
			}
		});//Kakao.Auth.login
	}//loginWithKakao
</script>
</head>
<body>
	<jsp:include page="/header.jsp"/>
	<section>
		<div class="mbodyWrap">
			<h1 id="mTitle">로그인</h1>
			<div class="kakaoLogin" onclick="loginWithKakao()">
				<img src="<%=path%>/img/kakao_login.png" alt="카카오 로그인 버튼"/>
			</div>
			<div id="naverLogin">
				<img src="<%=path%>/img/naverLoginIcon.png" alt="네이버 로그인 아이콘"/>
				<div id="naverLoginTitle">네이버 로그인</div>
				<div id="naver_id_login" style="display: none;"></div>
			</div>
				<script type="text/javascript">
					var naver_id_login = new naver_id_login("RqVBtmWBmvlfrakSTXfy", "http://localhost:8088/LatteClass/NaverCallBack.me");
					var state = naver_id_login.getUniqState();
					naver_id_login.setButton("green", 3, 60);
					//naver_id_login.setDomain("http://localhost:8088/LatteClass/MemberLogin.me");
					naver_id_login.setState(state);
					//naver_id_login.setPopup();
					naver_id_login.init_naver_id_login();
					// 네이버 로그인 소스에서 추가되는 부분 
					$(document).on("click", "#naverLogin", function(){ var naverLogin = document.getElementById("naver_id_login").firstChild; naverLogin.click(); });

				</script>
			
			<div id="emailLogin">이메일 로그인</div>
			<div>
				<form action="./MemberLoginAction.me" method="post">
					<div>
						<input type="email" id="email" name="email" placeholder="이메일">
						<div class="" style="display:none;"> ajex 이메일 형태확인</div>
					</div>
					<div>
						<input type="password" id="password" name="password" placeholder="비밀번호"> 
						<div class="passCheckTxt" style="display:none;"> 영문, 숫자 조합 8~12자</div>
					</div>
					<input class="mJoinButton" type="submit" value="로그인">
				</form>
			</div>
			<div class="mJoinButton">
				<a href="./MemberJoin.me">아직 회원이 아니신가요? 회원가입</a>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"/>
</body>
</html>