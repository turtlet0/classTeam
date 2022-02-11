<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width:300px">
		<div style="position:relative;">
			<div>
				<img id="returnImg" src="../upload/${img_upload_path}${File.separator }${img_name}" style="width:300px">
			</div>
			<button type="button" id="imgDeleteBtn" style="position:absolute; top:0px; right:0px; z-index: 1">
					<img src="../img/DeleteImageUpload.b84cd73b.svg" >
			</div>
		</div>
	</div>
</body>
</html>