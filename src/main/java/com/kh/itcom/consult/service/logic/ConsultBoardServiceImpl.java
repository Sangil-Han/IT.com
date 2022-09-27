package com.kh.itcom.consult.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.consult.domain.ConsultBoard;
import com.kh.itcom.consult.domain.ConsultBoardComment;
import com.kh.itcom.consult.domain.ConsultDownCount;
import com.kh.itcom.consult.domain.ConsultUpCount;
import com.kh.itcom.consult.domain.ConsultViewCount;
import com.kh.itcom.consult.service.ConsultBoardService;
import com.kh.itcom.consult.store.ConsultBoardStore;
import com.kh.itcom.user.domain.User;

@Service
public class ConsultBoardServiceImpl implements ConsultBoardService{
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private ConsultBoardStore cStore;
	
	@Override
	public int registerBoard(ConsultBoard cBoard) {
		int result = cStore.insertBoard(session, cBoard);
		return result;
	}

	@Override
	public int modifyBoard(ConsultBoard cBoard) {
		int result = cStore.updateBoard(session, cBoard);
		return result;
	}

	@Override
	public List<ConsultBoard> printAllBoard(int currentPage, int boardLimit) {
		List<ConsultBoard> cList = cStore.selectAllBoard(session, currentPage, boardLimit);
		return cList;
	}

	@Override
	public int getTotalCount(String searchCondition, String searchValue) {
		int totalCount = cStore.selectTotalCount(session, searchCondition, searchValue);
		return totalCount;
	}

	@Override
	public List<ConsultBoard> printAllByValue(String searchCondition, String searchValue, int currentPage,
			int boardLimit) {
		 List<ConsultBoard> cList = cStore.selectAllByValue(session,searchCondition,searchValue,currentPage,boardLimit);
		return cList;
	}

	@Override
	public ConsultBoard printOneByNo(Integer cBoardNo) {
		ConsultBoard cBoard = cStore.selectOneByNo(session, cBoardNo);
		return cBoard;
	}

	@Override
	public int registerComment(ConsultBoardComment comment) {
		int result = cStore.insertComment(session, comment);
		return result;
	}

	@Override
	public List<ConsultBoardComment> printAllComment(Integer cBoardNo) {
		List<ConsultBoardComment> cList = cStore.selectAllComment(session, cBoardNo);
		return cList;
	}

	@Override
	public int deleteComment(Integer commentNo) {
		int result = cStore.deleteComment(session, commentNo);
		return result;
	}

	@Override
	public int upCountCheck(ConsultUpCount upCount) {
		int result = cStore.selectUpCount(session, upCount);
		return result;
	}

	@Override
	public int registerUpCount(ConsultUpCount upCount) {
		int result = cStore.insertUpCount(session, upCount);
		return result;
	}

	@Override
	public int removeUpCount(ConsultUpCount upCount) {
		int result = cStore.deleteUpCount(session, upCount);
		return result;
	}

	@Override
	public int printTotalUpCount(Integer cBoardNo) {
		int result = cStore.selectTotalUpCount(session, cBoardNo);
		return result;
	}

	@Override
	public int downCountCheck(ConsultDownCount downCount) {
		int result = cStore.selectDownCount(session, downCount);
		return result;
	}

	@Override
	public int registerDownCount(ConsultDownCount downCount) {
		int result = cStore.insertDownCount(session, downCount);
		return result;
	}

	@Override
	public int removeDownCount(ConsultDownCount downCount) {
		int result = cStore.deleteDownCount(session, downCount);
		return result;
	}

	@Override
	public int printTotalDownCount(Integer cBoardNo) {
		int result = cStore.selectTotalDownCount(session,cBoardNo);
		return result;
	}

	@Override
	public int modifyComment(ConsultBoardComment comment) {
		int result = cStore.updateComment(session, comment);
		return result;
	}

	@Override
	public int registBoardViewCount(ConsultViewCount viewCount) {
		int result = cStore.insertViewCount(session, viewCount);
		return result;
	}

	@Override
	public int printViewCountCheck(ConsultViewCount viewCount) {
		int result = cStore.selectViewCountCheck(session, viewCount);
		return result;
	}

	@Override
	public int printTotalViewCount(ConsultViewCount viewCount) {
		int result = cStore.selectTotalViewCount(session, viewCount);
		return result;
	}

	@Override
	public int updateBoardViewCount(Integer cBoardNo) {
		int result = cStore.updateBoardViewCount(session, cBoardNo);
		return result;
	}

	@Override
	public int modifyBoardUp(int consultBoardNo) {
		int result = cStore.updateBoardUp(session, consultBoardNo);
		return result;
	}

	@Override
	public int removeBoard(int cBoardNo) {
		int result = cStore.deleteBoard(session, cBoardNo);
		return result;
	}

	@Override
	public int modifyPoint(User loginUser) {
		int result = cStore.updatePoint(session, loginUser);
		return result;
	}

	@Override
	public void modifyViewable(User loginUser) {
		cStore.updateViewable(session, loginUser);
	}

	@Override
	public User printUser(User loginUser) {
		User printUser = cStore.selectUser(session, loginUser);
		return printUser;
	}

}