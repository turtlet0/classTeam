<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <%String path=request.getContextPath(); %> <!-- url path를 미리 잡아놓음. -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Main</title>
	<link href="<%=path%>/css/reset.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/main.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/headerFooter.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/headerSearchBar.css" type="text/css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	
</head>

<body>
	<div id="body">
	<jsp:include page="/header.jsp"/>
		<div class="hOffLineClass">오프라인 클래스</div>
		<section class="main">
			<div class="mainBannerImg">banner이미지</div>
			<div class="classSearch">
				<button>지역</button>
				<button>카테고리</button>
			</div>
			<div>Class List</div>
		</section>
		<jsp:include page="/footer.jsp"/>
	</div>
</body>
</html>