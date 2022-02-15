package file.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import file.db.FileDAO;
import file.db.FileDTO;

public class FileDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		System.out.println("F. FileDeleteAction_execute() 호출");
		
		/*파일 제거*/
		
		// session 제어
		HttpSession session = request.getSession();
		int member_cd = Integer.parseInt(String.valueOf(session.getAttribute("member_cd")));
		System.out.println("F. member_cd: "+member_cd);
		
		String imgUploadPath = request.getParameter("imgUploadPath");
		System.out.println(imgUploadPath);
		System.out.println(imgUploadPath.indexOf("/upload"));
		System.out.println(imgUploadPath.lastIndexOf("/upload"));
		imgUploadPath = imgUploadPath.substring(imgUploadPath.indexOf("/upload")).replaceFirst("/upload/", "");
		System.out.println(imgUploadPath);

		@SuppressWarnings("deprecation")
		String uploadPath = request.getRealPath("/upload/") + imgUploadPath;
		System.out.println(uploadPath);
		
		
		File deleteImgFile = new File(uploadPath);
		
		if(deleteImgFile.exists() && deleteImgFile.isFile()){
			deleteImgFile.delete(); // 이미지 파일 삭제
			System.out.println("기존 이미지 제거: "+uploadPath);
		}
		
		/*DTO 객체에 저장*/
		int seperatorIdx = imgUploadPath.lastIndexOf("/");
		System.out.println("seperatorIdx: "+seperatorIdx);
		String img_upload_path = imgUploadPath.substring(0, seperatorIdx);
		System.out.println(img_upload_path);
		String img_name = imgUploadPath.substring(seperatorIdx+1);
		System.out.println(img_name);
		
		FileDTO fdto = new FileDTO();
		fdto.setMember_cd(member_cd);
		fdto.setImg_upload_path(img_upload_path);
		fdto.setImg_name(img_name);
		
		System.out.println("F. fdto: "+fdto);
		
		/*DAO 객체 생성 -> DB 제거*/
		FileDAO fdao = new FileDAO();
		fdao.deleteImg(fdto);
		
		return null; // 페이지 이동 없음
	}

}
