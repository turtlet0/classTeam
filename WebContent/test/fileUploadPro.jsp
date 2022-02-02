<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 파일 업로드 처리 페이지 -->
<%
	request.setCharacterEncoding("UTF-8");

	// 프로젝트 내에 만들어진 폴더(내가 직접 만듬)를 저장할 폴더(파일 데이터 저장될) 이름 변수 선언
	String realfolder = "";
	// 실제 만들어질 폴더명 설정(context의 메서드 통해 realfolder 변수에 저장될 예정)
	String savefolder = "/upload";
	// 한글 인코딩 설정
	String encType="UTF-8";
	// 저장될 파일 사이즈 설정
	int maxSize = 1024 * 1024 * 5; // = 5MB 
	
	// 파일이 저장될 경로값을 읽어오는 객체
	ServletContext context = getServletContext();
	realfolder = context.getRealPath(savefolder);
	
	try {
		 // 클라이언트로부터 넘어온 데이터를 저장해주는 객체
		 MultipartRequest multi = new MultipartRequest(request, realfolder, maxSize, encType,
				 							new DefaultFileRenamePolicy()); // request 객체는 파일 데이터 받아올 수 없음
				 								// DefaultFileRenamePolicy() : 파일명 중복 시 파일 이름 자동 변경
%>
		당신의 이름은 <%=multi.getParameter("name") %><br> <!-- 파일 데이터 외 데이터 출력 확인 -->
		전달된 파일의 저장 경로는 <%=realfolder %>
<%
	} catch(Exception e) {
		e.printStackTrace();
	}
%>
</body>
</html>