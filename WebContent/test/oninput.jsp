<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="text" oninput="homzzang(this.value)" value="a">                             
<p id="demo"></p>

<script>
function homzzang(val) {
  document.getElementById("demo").innerHTML = val; 
}
</script>
</body>
</html>