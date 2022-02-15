package com.latteclass.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latteclass.db.ClassDAO;
import com.latteclass.db.ClassDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" writeProAction_execute() 호출");
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		
		
		
		// upload path 프로젝트 upload 폴더에 들어감
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		
		// 파일이 저장될 가상경로
//		String realPath = request.getRealPath("upload");
		
		// 업로드 파일크기 지정
		int maxSize = 10 * 1024 * 1024;	//10MB
		
		MultipartRequest multi = 
				new MultipartRequest(
						request,
						realPath,
						maxSize,
						"UTF-8",
						new DefaultFileRenamePolicy()
						);

		// DTO 객체
		
		
		ClassDTO dto = new ClassDTO();
		
		if(multi.getFilesystemName("class_rep_img") != null) {
			dto.setClass_rep_img(multi.getFilesystemName("class_rep_img"));
		}
		
		
		dto.setTitle(multi.getParameter("title"));
		dto.setCategory(multi.getParameter("category"));
		dto.setSubcategory(multi.getParameter("subcategory"));
		dto.setSido(multi.getParameter("sido"));
		dto.setPrice(multi.getParameter("price"));
		
		System.out.println(dto);
		
		// DAO 객체
		ClassDAO dao = new ClassDAO();
		
		// 전달된 정보를 DB에 저장

		dao.writeClass(dto);
		
		System.out.println("강의등록완료");
		
//		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
//		request.setAttribute("pageNum", pageNum);
		
		// 페이지 이동 -> 이동정보 저장
		// ActionForward 객체를 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./classlist.do");
		forward.setRedirect(true);
		
		return forward;
	}

}
