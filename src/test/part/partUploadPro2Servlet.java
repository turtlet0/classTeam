package test.part;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
		fileSizeThreshold = 0, // : 파일 업로드 시 임시 디렉토리에 저장되기 시작할 파일의 바이트의 크기 지정
			// 기본값 0 (int 타입). 지정한 크기 넘지않으면 메모리에 저장되었다 크기 넘으면 임시 디렉토리에 저장되기 시작하는 것
		location = "D:\\workspace_jsp\\Model2\\partUpload" // 업로드된 파일이 저장될 디렉토리를 String 타입으로 지정
		)
@WebServlet("/partUploadPro2") // web.xml 서블릿매핑 대체 코드 
// tip) 서블릿 생성하고 매핑하지 않으면 404 에러 발생

public class partUploadPro2Servlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public partUploadPro2Servlet (){
		super();
	}
	
	/**
	 * @see HttpServlet#doPost(httpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String writer = request.getParameter("writer");// 파일이 아닌 파라미터 받기
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter(); 
		
		// ----------- 다중 업로드 코드 ----------------
		// 업로드한 파일 이름들을 문자열로 연결하기 위해 선언한 변수. 업로드된 모든 파일 이름들을 문자열로 저장할 변수
		String uploadFileNameList = "";
		
		// 클라이어트에서 전송된 모든 파라미터 값들을 가지고 Part 객체 생성해 파일 업로드 작업하는 코드
		// type 속성 값이 file이 아닌 파라미터 값에 대해서도 Part 객체가 생성되기 때문에, 파라미터 name으로 해당 part 객체는 업로드 작업하지 않도록 처리
		for(Part part : request.getParts()) {
			if(! part.getName().equals("writer")) { // file이 아닌 파라미터 거르기
				String contentDisposition = part.getHeader("content-disposition"); // content-disposition 헤더 정보를 얻어오는 코드
				// 이 헤더 정보는 IE) form-data; name="partFile1"; filename="C:\Users\leejiu\Desktop\test.txt" 형태로 반환됨
				// -> 클라이언트가 선택한 파일의 이름 얻을 수 있음
				System.out.println("contentDisposition : " + contentDisposition);
				
				String uploadFileName = getUploadFileName(contentDisposition); // 클라이언트가 업로드한 파일명 반환 메서드 호출
				
				part.write(uploadFileName); // @MultipartConfig의 location 옵션으로 지정한 경로로 클라이언트가 선택한 파일명으로 업로드된 파일을 저장
				
				uploadFileNameList += " " + uploadFileName;
			}
		}
		// ----------- 다중 업로드 코드 ----------------
		
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
