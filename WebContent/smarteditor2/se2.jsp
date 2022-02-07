<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./js/HuskyEZCreator.js"></script>
<script src="../js/jquery-3.6.0.min.js"></script>
</head>
<body>
<form id="seForm" action="se2Insert.jsp" method="post"> 

	<textarea name="content" id="ir1" rows="10" cols="100">에디터에 기본으로 삽입할 글(수정 모드)이 없다면 이 value 값을 지정하지 않으시면 됩니다.</textarea>
	<input type="button" id="save" value="저장"/>
</form>
<script type="text/javascript">
var oEditors = [];
$(function(){
	nhn.husky.EZCreator.createInIFrame({
		 oAppRef: oEditors,
		 elPlaceHolder: "ir1",
		 sSkinURI: "./SmartEditor2Skin.html",
		 fCreator: "createSEditor2"
		});
		
		
		//저장버튼 클릭시 form 전송
	    $("#save").click(function(){
	    	alert("save");
	        oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
	        $("#seForm").submit();
	    });   
});
	 
</script>
</body>
</html>