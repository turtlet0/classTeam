<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<h3>내가 등록한 클래스</h3>
		</div>
		<p>
		<div>
			<a href="#">정산 페이지</a>
		</div>
	</div>
	<div>
		<div>대표 이미지</div>
		<div>클래스명</div>
		<div>클래스 개설일</div>
	</div>
	<hr>
	<div>
		<div>
			<a href="./class.cl?class_cd=${requestScope.classSimpleInfo[0].class_cd}">
				<div>
					<img src="/LatteClass_merge/upload/${requestScope.classSimpleInfo[1].img_upload_path}/${requestScope.classSimpleInfo[1].img_name}" width="150px">
				</div>
				<div>${requestScope.classSimpleInfo[0].title}</div>
				<div>${requestScope.classSimpleInfo[0].reg_date }</div> <!-- JSTL fmt -->
			</a>
		</div>
		<div>
			<a href="#">관리 및 설정</a>
			<a href="./classModify.cl">내용 수정</a>
		</div>
	</div>
</body>
</html>