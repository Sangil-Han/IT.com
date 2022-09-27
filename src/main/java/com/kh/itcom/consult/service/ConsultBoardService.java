package com.kh.itcom.consult.service;

import java.util.List;

import com.kh.itcom.consult.domain.ConsultBoard;
import com.kh.itcom.consult.domain.ConsultBoardComment;
import com.kh.itcom.consult.domain.ConsultDownCount;
import com.kh.itcom.consult.domain.ConsultUpCount;
import com.kh.itcom.consult.domain.ConsultViewCount;
import com.kh.itcom.user.domain.User;

public interface ConsultBoardService {
	
	public List<ConsultBoard> printAllBoard(int currentPage, int boardLimit);
	
	public int registerBoard(ConsultBoard cBoard);
	
	public int modifyBoard(ConsultBoard cBoard);

	public int getTotalCount(String searchCondition, String searchValue);

	public List<ConsultBoard> printAllByValue(String searchCondition, String searchValue, int currentPage,
			int boardLimit);

	public ConsultBoard printOneByNo(Integer cBoardNo);

	public int registerComment(ConsultBoardComment comment);

	public List<ConsultBoardComment> printAllComment(Integer cBoardNo);

	public int deleteComment(Integer commentNo);

	public int registerUpCount(ConsultUpCount upCount);

	public int upCountCheck(ConsultUpCount upCount);

	public int removeUpCount(ConsultUpCount upCount);

	public int printTotalUpCount(Integer cBoardNo);

	public int downCountCheck(ConsultDownCount downCount);

	public int registerDownCount(ConsultDownCount downCount);

	public int removeDownCount(ConsultDownCount downCount);

	public int printTotalDownCount(Integer cBoardNo);

	public int modifyComment(ConsultBoardComment comment);

	public int registBoardViewCount(ConsultViewCount viewCount);

	public int printViewCountCheck(ConsultViewCount viewCount);

	public int printTotalViewCount(ConsultViewCount viewCount);

	public int updateBoardViewCount(Integer cBoardNo);

	public int modifyBoardUp(int consultBoardNo);

	public int removeBoard(int cBoardNo);

	public int modifyPoint(User loginUser);

	public void modifyViewable(User loginUser);

	public User printUser(User loginUser);

}