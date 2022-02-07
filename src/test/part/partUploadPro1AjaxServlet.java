package test.part;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
		fileSizeThreshold = 0, // : 파일 업로드 시 임시 디렉토리에 저장되기 시작할 파일의 바이트의 크기 지정
			// 기본값 0 (int 타입). 지정한 크기 넘지않으면 메모리에 저장되었다 크기 넘으면 임시 디렉토리에 저장되기 시작하는 것
		location = "D:\\workspace_jsp\\Model2\\partUpload" // 업로드된 파일이 저장될 디렉토리를 String 타입으로 지정
		)
public class partUploadPro1AjaxServlet extends HttpServlet { // HttpServlet 상속
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public partUploadPro1AjaxServlet (){
		super();
	}
	
	/**
	 * @see HttpServlet#doPost(httpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("sevlet");
		
		request.setCharacterEncoding("UTF-8");
		
		Part part = request.getParameter(arg0); // javax.servlet.http.Part;
			// 파일 파라미터를 Part 객체로 받기
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter(); 
		String contentDisposition = part.getHeader("content-disposition"); // content-disposition 헤더 정보를 얻어오는 코드
			// 이 헤더 정보는 IE) form-data; name="partFile1"; filename="C:\Users\leejiu\Desktop\test.txt" 형태로 반환됨
			// -> 클라이언트가 선택한 파일의 이름 얻을 수 있음
		System.out.println("contentDisposition : " + contentDisposition);
		
		String uploadFileName = getUploadFileName(contentDisposition); // 클라이언트가 업로드한 파일명 반환 메서드 호출
		
		part.write(uploadFileName); // @MultipartConfig의 location 옵션으로 지정한 경로로 클라이언트가 선택한 파일명으로 업로드된 파일을 저장
		
		out.println(uploadFileName+" 파일을 업로드하였습니다."); 
	}
	
	/*파일 이름 반환 메서드 */
	
	// 사용 브라우저가 IE인 경우 
//	private String getUploadFileName(String contentDisposition) {
//		String uploadFileName = null;
//		String[] contentSplitStr = contentDisposition.split(";");
//		int lastPathSeparatorIndex = contentSplitStr[2].lastIndexOf("\\");
//			// filename="C:\Users\leejiu\Desktop\test.txt" 부분 가져오기
//			// 마지막 \가 존재하는 인덱스 값 반환
//		int lastQutosIndex = contentSplitStr[2].lastIndexOf("\"");
//		uploadFileName = contentSplitStr[2].substring(lastPathSeparatorIndex + 1, lastQutosIndex);
//			// -> test.txt 반환
//		
//		return uploadFileName;
//	}
	
	// 사용 브라우저 Chrome인 경우
	private String getUploadFileName(String contentDispostion) {
		String uploadFileName = null;
		
		String[] contentSplitStr = contentDispostion.split(";");
		int firstQutosIndex = contentSplitStr[2].indexOf("\"");
		int lastQutosIndex = contentSplitStr[2].lastIndexOf("\"");
		uploadFileName = contentSplitStr[2].substring(firstQutosIndex + 1, lastQutosIndex);
		
		return uploadFileName;
	}
	
}
