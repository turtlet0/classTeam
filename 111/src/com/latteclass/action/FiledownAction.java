package com.latteclass.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latteclass.db.ClassDAO;
import com.latteclass.db.ClassDTO;

public class FiledownAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int class_cd = Integer.parseInt(request.getParameter("class_cd"));
		String pageNum = request.getParameter("pageNum");
		
		ClassDAO dao = new ClassDAO();
		ClassDTO classlist = dao.getClass(class_cd);
		
		ServletOutputStream outStream = null;
		FileInputStream inputStream = null;
		
		// DB에 있는 정보 가져오기
		String class_filename = null;
		class_filename = classlist.getClass_rep_img();
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		String uploadFileName = uploadPath+"/"+class_filename;
		
		File downfile = new File(uploadFileName);
		
		if(!downfile.exists()) {
			throw new FileNotFoundException();
		}
		
		try {
			outStream = response.getOutputStream();
			inputStream = new FileInputStream(downfile);
			//Setting response header
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=\""+downfile.getName()+"\"");
			byte[] outByte = new byte[4096];
			while(inputStream.read(outByte,0,4096)!=-1) {
				outStream.write(outByte,0,4096);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			inputStream.close();
			outStream.flush();
			outStream.close();
		}
		
		request.setAttribute("class_cd", new Integer(class_cd));	
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("classlist", classlist);
		
		// 페이지 이동 -> 이동정보 저장
		// ActionForward 객체를 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./classlist.do");
		forward.setRedirect(true);
		
		return forward;
	}

}
