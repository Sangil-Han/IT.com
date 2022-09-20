package com.kh.itcom.consult.service;

import java.util.List;

import com.kh.itcom.consult.domain.ConsultBoard;

public interface ConsultBoardService {
	
	public List<ConsultBoard> printAllBoard(int currentPage, int boardLimit);
	
	public int registerBoard(ConsultBoard cBoard);
	
	public int getTotalCount(String searchCondition, String searchValue);

	public List<ConsultBoard> printAllByValue(String searchCondition, String searchValue, int currentPage,
			int boardLimit);

	public ConsultBoard printOneByNo(Integer cBoardNo);
	
}
