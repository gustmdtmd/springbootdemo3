package com.example.board.service;

import java.io.File;
import java.util.List;

import com.example.board.dao.BoardDAO;
import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageDTO;

public class BoardServiceImp implements BoardService {
	private BoardDAO dao;

	public BoardServiceImp() {

	}

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	@Override
	public int countProcess() {
		return dao.count();
	}

	@Override
	public List<BoardDTO> listProcess(PageDTO pv) {
		return dao.list(pv);
	}

	@Override
	public void insertProcess(BoardDTO dto) {
		// 답변글이면
		if (dto.getRef() != 0) {
			dao.reStepCount(dto);
			dto.setRe_step(dto.getRe_step() + 1);
			dto.setRe_level(dto.getRe_level() + 1);
		}

		dao.save(dto);
	}

	@Override
	public BoardDTO contentProcess(int num) {
		dao.readCount(num);
		return dao.content(num);
	}

	@Override
	public void reStepProcess(BoardDTO dto) {

	}

	@Override
	public BoardDTO updateSelectProcess(int num) {
		return dao.content(num);
	}

	@Override
	public void updateProcess(BoardDTO dto, String urlpath) {
		String filename = dto.getUpload();
		// 수정한 파일 있으면
		if (filename != null) {
			String path = dao.getFile(dto.getNum());
			// 기존 첨부파일이 있으면
			if (path != null) {
				File file = new File(urlpath, path);
				file.delete();
			}
		}

		dao.update(dto);
	}

	@Override
	public void deleteProcess(int num, String urlpath) {
		String path = dao.getFile(num);

		// num컬럼에 해당하는 첨부파일이 있으면
		if (path != null) {
			File fe = new File(urlpath, path);
			fe.delete();
		}

		dao.delete(num);
	}

	@Override
	public String fileSelectProcess(int num) {
		return dao.getFile(num);
	}

}// end class
