package com.kh.itcom.finish.service;

import java.util.List;

import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.finish.domain.FinishComment;

public interface FinishBoardService {

	int registerBoard(FinishBoard fBoard);

	int getTotalCount(String searchOption, String searchValue);

	List<FinishBoard> printAllBoard(int currentPage, int boardLimit);

	List<FinishBoard> printAllByValue(String searchOption, String searchValue, int currentPage, int boardLimit);

	FinishBoard printOneByNo(int fBoardNo);

	int modifyBoard(FinishBoard fBoard);

	int registerComment(FinishComment fComment);

	void usePoint(String userId, String point);

}
