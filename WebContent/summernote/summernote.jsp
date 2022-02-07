<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  <!-- 서머노트를 위해 추가해야할 부분 -->
  <script src="./summernote-lite.js"></script>
  <script src="./summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="./summernote-lite.css">
  
  <!--  <script src="./jquery-3.6.0.min.js"></script> -->
  <!--  -->
  
</head>
<body>
<form action="./summernotePro.jsp" method="post">
	<div class="container">
	  <textarea id="summernote" name="editordata"></textarea>    
	</div>

	<input type="submit" value="전송">
</form>
<script>

$('#summernote').summernote({
	height: 300,                 // 에디터 높이
	minHeight: null,             // 최소 높이
	maxHeight: null,             // 최대 높이
	focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
	lang: "ko-KR",					// 한글 설정
	placeholder: '최대 2048자까지 쓸 수 있습니다'	// placeholder 설정
	/* callbacks: {
   		onImageUpload : function(files, editor, welEditable){
      			for (var i = files.length - 1; i >= 0; i--) {
			uploadSummernoteImageFile(files[i], editor, welEditable);
			}
		}
	} */
});
/* //이미지 업로드 할 때 실행되는 함수 정의
function uploadSummernoteImageFile(file, editor, welEditable ) {
	data = new FormData();
	data.append("file", file);
	$.ajax({
		data : data,
		type : "POST",
		url : "uploadImgTemp.do",
		contentType : false,
		enctype : 'multipart/form-data',
		processData : false,
		success : function(result){
			result = JSON.parse(result);
			$("#summernote").summernote('insertImage', result.url);
		}
	});
} */
</script>
</html>