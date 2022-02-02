<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
클래스 등록 페이지 동작 방식
- 총 3단계로 구성
- 각 단계간 이동 (이전, 다음 버튼 클릭 시) 시 idx 파라메터를 ClassAddAction.cl로 넘김
- ClassAddAction.java 페이지에서 Session 객체 생성 및 ClassDTO 객체 생성 후 정보 cdto에 저장 -> session 객체에 저장
- idx 파라메터 값에 따라 해당 페이지로 redirect 방식으로 페이지 이동
- 최종 등록 시 DB 저장되고 session의 cdto 객체는 무효화(제거)
 -->

<%
	request.setCharacterEncoding("UTF-8");
%>
	<!-- WebContent/class/ClassAddForm1.jsp -->
	<!-- 클래스 등록1 페이지 -->
	
	<h1>WebContent/class/ClassAdd1Form.jsp</h1>
	
	<h2> session ${sessionScope.cdto }</h2> 
	<h2> session address ${sessionScope.cdto.address }</h2> 
	<h2> session content ${sessionScope.cdto.content }</h2> 
	
		<form name="classAddForm" method="post">
	
	
		<div>
			<input type="text" name="content" value="${sessionScope.cdto.content }" placeholder="내용 작성">
			
		</div>
		
	
		<div>
			<input type="submit" value="이전(클릭X)" >
			<input type="submit" value="다음" onclick='classAddSubmit(2)'>
			
		</div>
	</form>

	<script>
		function classAddSubmit(idx){
			if(idx == 1) {
				classAddForm.action = './ClassAddAction.cl?idx=1';
				classAddForm.submit();
			}
			else if(idx == 2) {
				classAddForm.action = './ClassAddAction.cl?idx=2';
				classAddForm.submit();
			}
			else if(idx == 3) {
				classAddForm.action = './ClassAddAction.cl?idx=3';
				classAddForm.submit();
			}
		}
	</script>
</body>
</html>