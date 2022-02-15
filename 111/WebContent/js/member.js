/**
 * 
 */
		function emailDoubleCheck() {
			emailRullCheck();
			const email=document.querySelector("#email").value;
			var edCheck="";
			$.ajax({			
				async:false, // 비동기 해제 : 모두 호출되고 다음함수 실행		
				method:"POST",		
				cache:false,		
				url:"MemberMailDoubleCheck.me",		
				data:{"email":email}		
			}).done(function(result){			
				//console.log(result);	
				edCheck = result;
				if(result=="y"){
					$("#mailDoubleCheckTxt").show().css("color","red");
					return false;
				}else{
					$("#mailDoubleCheckTxt").hide();
				}
			});
			return edCheck;
		}
	
		function emailRullCheck(){
			var emailReg = /^([a-z]+\d*)+(\.?\w+)+@\w+(\.\w{2,3})+$/;
			var email = $("#email").val();
			var eamilRegResult = emailReg.test(email);
			//console.log(eamilRegResult);	
			if(eamilRegResult == false){
				$("#mailCheckTxt").show().css("color","red");
				return false;
			}else{
				$("#mailCheckTxt").hide();
			}
			return eamilRegResult;
		}
	
		function passRullCheck(){
			var passReg = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,12}$/;
	
			var password = $("#password").val();
			var passRegResult = passReg.test(password);
			//console.log(passRegResult);
			if(passRegResult == false){
				$("#passCheckTxt").show().css("color","red");
				return false;
			}else{
				$("#passCheckTxt").hide();		
			}
			return passRegResult;
		} 
		
		function checkJoin(){
			
			var email = document.getElementById("email");
			var pass = document.getElementById("password");
			var pass2 = document.getElementById("password2");
			var term_of_use = document.getElementById("term_of_use");
			var privacy_policy = document.getElementById("privacy_policy");
			var edCheck = emailDoubleCheck();

			if(edCheck == "y"){
				console.log(edCheck);
				return false;
			}
			if(emailRullCheck() == false){
				console.log(emailRullCheck());
			return false;
			}
			if(passRullCheck()== false){
				console.log(passRullCheck());
			return false;
			}
			  
			//이메일 공백 체크
			if(email.value ==""){
				alert("이메일을 입력하세요");
				email.focus();
				return false;
			}
			//비밀번호 password 공백체크
			if(pass.value==""){
				alert("비밀번호를 입력하세요");
				pass.focus();
				return false;
			}
			//비밀번호 password2 공백체크
			if(pass2.value==""){
				alert("비밀번호 확인을 입력하세요");
				pass2.focus();
				return false;
			}
			//비밀번호 & 비밀번호 확인값 같은지 체크
			if(pass.value!=pass2.value){
				alert("비밀번호와 비밀번호확인 일치하지 않습니다.");
				pass2.select();
				return false;
			}
			// term_of_use 공백체크
			if(!term_of_use.checked){
				alert("이용약관 동의에 체크하세요");
				term_of_use.select();
				return false;
			}
			// privacy_policy 공백체크
			if(!privacy_policy.checked){
				alert(" 개인정보수집·이용 동의에 체크하세요");
				privacy_policy.select();
				return false;
			}
		}//checkJoin()