<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div name="classResultImgDiv" style="position:relative; width:300px;">
	<img name="classResultReturnImg" src="/LatteClass_merge/upload/${requestScope.classResultFdto.img_upload_path}/${requestScope.classResultFdto.img_name}" style="width:300px">
	<button type="button" name="classResultImgDeleteBtn" style="position:absolute; top:0px; right:0px; z-index: 1">
		<img name="imgDelete" src="/LatteClass_merge/img/DeleteImageUpload.b84cd73b.svg" >
	</button>
</div>