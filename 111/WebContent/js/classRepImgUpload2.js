/**
 * 
 */

$(document).ready(function(){
	alert("classRepImgUpload - JQuery 정상 동작");
	alert("왜 반영이안돼");
	/* Ajax 이용 이미지 파일 전송 */
	//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
	/* formData에 file 담기 */
	var img_file = document.querySelector('#img_file'); // document.querySelector(selector) : 첫번째 요소 반환
	//var img_idx = document.querySelector('#img_idx'); // 서버단에서 전달받을때 [object HTMLInputElement] 가 반환되어버림..
	var img_idx = document.getElementById('img_idx').value; // 문자열이나 객체가 아닌 실제값(actual value)을 가져와야함
	
	// button 태그 클릭 시 input file 업로드 창 열기
	var imgUploadBtn = document.querySelector('#imgUploadBtn');
	imgUploadBtn.addEventListener('click', function() {
		img_file.click();
	});
	
	
	img_file.addEventListener('change', function() {
		console.log("이미지 업로드 동작");
		
		/* 유효성 체크 */
		// 참고: https://jfbta.tistory.com/25
		var imgFileValue = $('#img_file').val();
		var fileExt = /(.*?)\.(jpg|jpeg|jfif|png|bmp)$/;
		var maxSize = 5 * 1024 * 1024;
		var fileSize;
		
		// null 체크 (파일 업로드 창 연 뒤 파일 미선택하고 취소하는 경우)
		if(imgFileValue == ""){
			return;
		} 
		
		if(imgFileValue != "" && imgFileValue  != null){
			//fileSize = document.getElementById("img_file").files[0].size;
			fileSize = img_file.files[0].size;
			
			// 확장자 체크
			if(! imgFileValue.match(fileExt)){
				alert("jpg, jpeg, jfif, png, bmp 확장자만 업로드 가능");
				return;
			} 
			// 업로드 용량 체크
			else if(fileSize > maxSize){
				alert("5MB 이하 용량의 이미지만 업로드 가능");
				return;
			}
			
		}
		
		var formDataFile = new FormData();
		console.log(img_file.files[0]);
		formDataFile.append('img_file', img_file.files[0]); 
		formDataFile.append('img_idx', img_idx); 
			// input file에 들어간 파일들은 files 라는 리스트로 저장
			// input multiple 선언해 여러 파일 선택한 경우 아니라면 files[0] 으로 저장한 파일 객체 찾을 수 있음
		console.log(formDataFile.has('img_file'));
		console.log(formDataFile.get('img_file'));
		console.log(formDataFile.get('img_idx'));
		
		// Display the key/value pairs
		for (var pair of formDataFile.entries()) {
		    console.log(pair[0]+ ', ' + pair[1]); 
		}
		
		/* Ajax 이용 전송 */
		$.ajax({
			contentType : false,
				// contentType - (default) true : application/x-www-form-urlencoded / false : multipart/form-data
					// 즉, form 태그엔 enctype을 multipart로 지정할 필요없음 (별개의 form 생성하는 개념이므로)
		    processData : false,
		    	// processData - true : Ajax로 multipart 방식을 전송 시 JQuery 내부적으로 query String 형태로 전송
		    	// -> File 전송 시 이를 피해야함 : false
		    	
		    data : formDataFile, // formData가 아닌 다른 변수 함께 보내면 에러 발생
		    	// 보낼거면 formData에 담아서 보내기
		    url : 'FileUploadAction.fi',
		    type : 'post',
		    success : function (data) {
		    	alert("Ajax 이미지 전송 성공");
		    	alert(data);
		    	$('#imgResult').empty(); // 선택된 요소의 자식 요소 전체 삭제
		    	$('#imgResult').append(data);
			}
		});
	}); /* Ajax 이용 이미지 파일 전송 */
	
	/* Ajax 이용 이미지 파일 제거 */
	// tip) 동적 생성 요소(append 등 이용)는 페이지 렌더링 이후
	// 추가되었기 때문에 이벤트 동작되지 않음
	// -> on 태그 이용!
	/*$(document).on('click', '#imgDeleteBtn', function(){
		alert("이미지 삭제");
		var imgUploadPath = $('#returnImg').attr('src');
		alert($('#returnImg').attr('src'));
		
		$.ajax({
			url: 'imgDeletePro.jsp',
			data: {imgUploadPath : imgUploadPath}, // 하나의 데이터라도 {파라메터명 : 변수} 꼴로 해야 에러안남
			success: function(){
				alert("삭제 성공!");
				$('#imgResult').empty();
			}
		});
	});*/
	
	
	
	
	
});
