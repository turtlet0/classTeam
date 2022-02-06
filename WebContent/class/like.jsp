<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<!-- 찜 기능 조문주 -->
	좋아요
	<input type="button" value="♥" onclick="goodCheck()"> ${vo.good }
	
	<script type="text/javascript">
		function goodCheck() {
			var query = {idx : ${vo.idx}}
			
			$.ajax({
				url : "${contextPath}/bGood.me",
				type : "post", // get으로 수정
				data : query,
				success:function(data){
					location.reload();
				}
			});
		}
	</script>
	
</body>
</html>