package com.example.board.service;

import java.util.List;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageDTO;

public interface BoardService {
	public int countProcess();
	public List<BoardDTO> listProcess(PageDTO pv);
	public void insertProcess(BoardDTO dto);
	public BoardDTO contentProcess(int num);
	public void reStepProcess(BoardDTO dtp);
	public BoardDTO updateSelectProcess(int num);
	public void updateProcess(BoardDTO dto, String urlpath);
	public void deleteProcess(int num, String urlpath);
	public String fileSelectProcess(int num);
}
