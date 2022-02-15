<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=request.getContextPath();%>
<!DOCTYPE html>
<html lang="ko">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<title>네이버 로그인 중...</title>
</head>
<body>
<script type="text/javascript">

	var naver_id_login = new naver_id_login("RqVBtmWBmvlfrakSTXfy",	"http://localhost:8088/LatteClass/NaverCallBack.me");
	// 접근 토큰 값 출력
	//alert(naver_id_login.oauthParams.access_token);
	// 네이버 사용자 프로필 조회
	naver_id_login.get_naver_userprofile("naverSignInCallback()");
	// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	function naverSignInCallback() {
		var email = naver_id_login.getProfileData('email');
		var sns_id = naver_id_login.getProfileData('id');
		var nick_name = naver_id_login.getProfileData('name');
		$.ajax({			
			async:false, // 비동기 해제 : 모두 호출되고 다음함수 실행		
			method:"POST",		
			cache:false,		
			url:"MemberNaverLoginAction.me",		
			data:{
				"email":email,
				"sns_id":sns_id,
				"nick_name":nick_name
			}	
		}).done(function(result){			
			location.href="./Main.me";
		}); //$.ajax
	}
</script>









</body>
</html>