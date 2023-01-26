package com.example.board.view;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.example.board.dao.BoardDAO;

//다운로드 창을 띄우기 위한 뷰 페이지
public class BoardDownLoadView extends AbstractView{
	
	private BoardDAO dao;

	public BoardDownLoadView() {
	
	}
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root + "temp" + File.separator;
		
		String upload = dao.getFile(num);
		String fileName = upload.substring(upload.indexOf("_") + 1);
		
		//파일명이 한글일때 인코딩 작업을 한다.
		String str = URLEncoder.encode(fileName, "UTF-8");
		
		//원본 파일명에서 공백이 있을 때, +표시가 되므로 공백으로 처리해주는 작업
		str = str.replaceAll("\\+", "%20");
		
		//컨텐츠 타입
		response.setContentType("application/octet-stream");
		
		//다운로드 창에 보여줄 파일명을 지정한다. ""안에는 띄어쓰기 X
		response.setHeader("Content-Disposition", "attachment;filename="+str+";");
		
		//서버에 저장된 파일을 읽어와서 클라이언트에 출력
		FileCopyUtils.copy(new FileInputStream(new File(saveDirectory,upload)), response.getOutputStream());
	}
	
}//end class
