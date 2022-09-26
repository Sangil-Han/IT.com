package com.kh.itcom.finish.service;

import java.util.List;
import java.util.Map;

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

	List<FinishComment> printAllComment(int fBoardNo);

	int removeComment(Integer fCommentNo);
	
	int addUpDownCount(Integer fBoardNo, String userId, String upOrDown);

	int getCountUp(int fBoardNo);

	int getCountDown(int fBoardNo);

	int getUserRecordUpCount(String userId, int fBoardNo);

	void modifyComment(Map<String, Object> inputMap);

}
