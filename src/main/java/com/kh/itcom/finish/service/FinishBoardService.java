package com.kh.itcom.finish.service;

import java.util.List;

import com.kh.itcom.finish.domain.FinishBoard;

public interface FinishBoardService {

	int registerBoard(FinishBoard fBoard);

	int getTotalCount(String searchOption, String searchValue);

	List<FinishBoard> printAllBoard(int currentPage, int boardLimit);

	List<FinishBoard> printAllByValue(String searchOption, String searchValue, int currentPage, int boardLimit);

}
