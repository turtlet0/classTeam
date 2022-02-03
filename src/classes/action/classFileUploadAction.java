package classes.action;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import classes.db.FileDAO;

@MultipartConfig(maxFileSize = 1024 * 1024 *100, location="D:\\workspace_jsp_moviepedia\\attaches")
public class classFileUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("classFileUploadAction_execute() 호출!");
		
		request.setCharacterEncoding("UTF-8");
		
		/*Part API 이용(서블릿 3.0이상 지원)한 파일 업로드*/
		Part part = request.getPart("profile");
		
		/*파일 이름*/
		// ex) fileName = "file_(06250000).exe"
		String fileName = new FileDAO().getFilename(part); 
		
		if (fileName.equals("7")) {
			response.getWriter().write("-1");
		} else {

			// 파일 서버에 업로드
			if (fileName != null && !fileName.isEmpty()) {
				// MultipartConfig location에 filePath로 파일 생성
				// "c:\\attaches\\file_(06250000).exe"에 저장
				part.write(fileName);
			}

			
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("./fileUpload.cl");
		forward.setRedirect(true);
		
		return forward;
	}

}
