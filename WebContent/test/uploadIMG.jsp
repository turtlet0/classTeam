<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.io.File"%>
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
	<%
		String returnValue = "";
		String savePath = application.getRealPath("/") + "/test/downImg/";
		int sizeLimit = 5 * 1024 * 1024; // 5 메가로 제한 
		
		if(request.getContentLength() > sizeLimit) { // 오류 메세지 (5MB 이상을 첨부한 경우) 
			
		} else { 
			MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy()); 
			
			//파라미터 
			String fileName = multi.getFilesystemName("test"); // 파일의 이름 얻기 
			String ext = null; 
			String[] allowExt = {"jpg", "png", "gif"}; 
			int dot = 0; 
			
			File file1 = multi.getFile("test"); 
			
			String name = file1.getName(); 
			dot = name.lastIndexOf("."); 
			
			if(dot != -1) { 
				ext = name.substring(dot); 
			} else { 
					ext = ""; 
			} 
			
			long fixNum = (long)(100+(Math.random()*(long)999999999)); 
			String newName = "sample" + (fixNum) + ext; 
			String newName_small = "sample" + (fixNum) +"_small" + ext; 
			
			// 파일 객체 얻기 
			File newfile = new File(file1.getParent(), newName); 
			file1.renameTo(newfile); 
			
			returnValue = "/upload/downImg/"+newName_small;
		}
			
			
			

			
			
			/* //파일 확장자 제한 
			if(ImageResize.isContainsFileExt(fileName, allowExt)){ 
				long fixNum = (long)(100+(Math.random()*(long)999999999)); 
				String newName = "sample" + (fixNum) + ext; 
				String newName_small = "sample" + (fixNum) +"_small" + ext; 
				
				// 파일 객체 얻기 
				File newfile = new File(file1.getParent(), newName); 
				file1.renameTo(newfile); 
				
				//============= 이미지 리사이즈 시작 ============= 
				String imgOriginalPath= file1.getParent() + "/" + newName; // 원본 이미지 파일명 
				String imgTargetPath= file1.getParent() + "/" + newName_small; // 새 이미지 파일명 
				String imgFormat = "jpg"; // 새 이미지 포맷. jpg, gif 등 
				int newWidth = 680; // 변경 할 넓이 
				int newHeight = 700; // 변경 할 높이 
				String mainPosition = "W"; // W:넓이중심, H:높이중심, X:설정한 수치로(비율무시) 
				
				boolean chkSave = ImageResize.resizeImage(imgOriginalPath, imgTargetPath, imgFormat, newWidth, newHeight, mainPosition); 
				//============= 이미지 리사이즈 끝 ============= 
						
				returnValue = "/upload/downImg/"+newName_small; 
				
				if(chkSave) { 
					out.println("{"); 
					out.println("\"uploaded\": 1,"); 
					out.println("\"fileName\": \""+newName+"\","); 
					out.println("\"url\":\""+returnValue+"\""); out.println("}"); 
				} else { 
					// 첨부 오류 메세지 
				} 
			} else { 
				file1.delete(); 
			}  
		}*/
	%>
</body>
</html>