package com.kh.itcom.finish.service;

import java.util.List;
import java.util.Map;

import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.finish.domain.FinishComment;

public interface FinishBoardService {

	int registerBoard(FinishBoard fBoard);

	int registerComment(FinishComment fComment);

	int modifyBoard(FinishBoard fBoard);

	int modifyComment(Map<String, Object> inputMap);

	int usePoint(String userId, String point);

	int addUpDownCount(Integer fBoardNo, String userId, String upOrDown);

	int removeComment(Integer fCommentNo);

	int getTotalCount(String searchOption, String searchValue);

	int getCountUp(int fBoardNo);

	int getCountDown(int fBoardNo);

	int getUserRecordUpCount(String userId, int fBoardNo);

	FinishBoard printOneByNo(int fBoardNo);

	List<FinishBoard> printAllBoard(int currentPage, int boardLimit);

	List<FinishBoard> printAllByValue(String searchOption, String searchValue, int currentPage, int boardLimit);

	List<FinishComment> printAllComment(int fBoardNo);

}
