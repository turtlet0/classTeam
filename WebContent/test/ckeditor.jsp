<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
</head>
<body>
	<textarea class="form-control" id="p_content"></textarea>
	<script type="text/javascript">
	 CKEDITOR.replace('p_content', {
		 			filebrowserImageUploadUrl: './uploadIMG.jsp',
		 			height: 500                                                  
	                 });
	</script>
	<!-- <form>
	    <textarea name="editor1" id="editor1" rows="10" cols="80">
	        This is my textarea to be replaced with CKEditor 4.
	    </textarea>
	    <script>
	        // Replace the <textarea id="editor1"> with a CKEditor 4
	        // instance, using default configuration.
	        CKEDITOR.replace( 'editor1' );
	    </script>
	</form> -->

</body>
</html>