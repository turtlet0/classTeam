package file.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import file.db.FileDAO;
import file.db.FileDTO;

public class FileUploadAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("F. FileUploadAction_execute() 호출");
		
		/*파일 업로드*/
		
		//실제 파일 저장 위치 : D:\workspace_mocaclass\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\LatteClass_merge/upload/~
		
		// session 제어
		HttpSession session = request.getSession();
		int member_cd = Integer.parseInt(String.valueOf(session.getAttribute("member_cd")));
		System.out.println("F. member_cd: "+member_cd);
		
		@SuppressWarnings("deprecation")
		String uploadRealPath = request.getRealPath("/upload/"); // 파일 업로드할 서버상의 폴더명
		// getRealPath() : 현재 웹서버의 상대 경로를, 웹 경로(ex) /upload)가 아닌 
		// 실제 서버 시스템상의 디렉토리 경로(ex) c:/upload)를 반환
		
		/* 날짜별 폴더 생성 */
		// 참고 페이지 : https://github.com/yj-park/chyoni/blob/master/06_servletjsp/src/jsp/file/Upload.java
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateStr = sdf.format(date);
		String img_upload_path = dateStr.replace("-", File.separator); // tip) File.seperator : OS마다 구분자 다름 -> 각 OS에 맞는 구분자 반환
		
		uploadRealPath += img_upload_path;
		System.out.println("uploadRealPath: "+uploadRealPath);
		
		System.out.println("img_upload_path: " + img_upload_path);
		
		// File 자바 기본 API 이용 
		File f = new File(uploadRealPath);
		if(!f.exists()){ // 폴더 존재여부 확인
			f.mkdirs(); // 폴더 생성
		}
		
		
		int size = 10 * 1024 * 1024; // 한번의 요청으로 업로드할 수 있는 업로드 파일의 최대 크기 지정 ->ex) 10MB
		// 용량 초과 시 Exception 발생
		
		String img_idx="";
		String img_name="";
		String img_orig_name="";
		
		// 파일 용량 초과 시 Exception 발생하도록 try-catch문으로 작성
		try{
			
		MultipartRequest  multi = new MultipartRequest(request,
				uploadRealPath,
				size,
				"UTF-8",
				/* UUID 이용 파일명 중복 방지 policy 생성해 이용 */
				new MyFileRenamePolicy()); 
		img_idx = multi.getParameter("img_idx"); // 파일이 아닌 파라메터도 multi~의 getP~ 메서드로 받음

		Enumeration files = multi.getFileNames(); // 파일 입력 상자의 이름을 Enumeration 타입으로 반환. 여러개 파일 업로드 시 getFileNames() 사용
		
		String img = (String) files.nextElement(); // Enumeration 객체 내부의 요소 순서대로 꺼내기
		img_name = multi.getFilesystemName(img); // 업로드된 파일 이름
		img_orig_name = multi.getOriginalFileName(img); // 업로드 전 원본 파일 이름
		
		System.out.println(multi.getContentType(img)); // > image/png

		
		
		}catch(Exception e){
		e.printStackTrace();
		} 
		
		System.out.println((String) img_idx);
		System.out.println(img_name);
		System.out.println(img_orig_name);
		System.out.println();
		
		/*DTO 객체에 저장*/
		FileDTO fdto = new FileDTO();
		fdto.setMember_cd(member_cd);
		fdto.setImg_idx(img_idx);
		fdto.setImg_name(img_name);
		fdto.setImg_orig_name(img_orig_name);
		fdto.setImg_upload_path(img_upload_path);
		
		System.out.println("F. fdto: " + fdto);
		
		/*DAO 객체 생성 -> DB 저장*/
		FileDAO fdao = new FileDAO();
		fdao.insertImg(fdto);
		System.out.println("F. 이미지 파일 DB 저장 완료");
		
		
		/*img_idx에 따라 값 저장 및 페이지 이동 상이*/
		ActionForward forward = null;
		if(img_idx.equals("classRep")) {
			/*request 영역에 저장*/
			// /file/classRepImgUploadPro.jsp에 전달 위함
			request.setAttribute("classRepFdto", fdto);
			
			/*session 영역에 저장*/
			// ClassAdd1Form.jsp 페이지에 기본 출력 위함
			session.setAttribute("classRepFdto", fdto);
			
			/*./file/classRepImgUploadPro.jsp 페이지로 정보 가져가 출력*/
			
			forward = new ActionForward();
			forward.setPath("./file/classRepImgUploadPro.jsp");
			forward.setRedirect(false);
			
			return forward;
		} else if(img_idx.equals("tutorRep")) {
			request.setAttribute("tutorRepFdto", fdto);
			session.setAttribute("tutorRepFdto", fdto);
			
			forward = new ActionForward();
			forward.setPath("./file/tutorRepImgUploadPro.jsp");
			forward.setRedirect(false);
			
			return forward;
		}
		
		// img_idx = classResult
		request.setAttribute("classResultFdto", fdto);
		session.setAttribute("classResultFdto", fdto);
		
		forward = new ActionForward();
		forward.setPath("./file/classResultImgUploadPro.jsp");
		forward.setRedirect(false);
		
		return forward;
		
		
		
	}
	

}